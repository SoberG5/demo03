package com.cssl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cssl.dto.EmpVo;
import com.cssl.pojo.Dept;
import com.cssl.pojo.Emp;
import com.cssl.service.DeptService;
import com.cssl.service.EmpService;

import com.cssl.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


@Slf4j
@Validated
@RestController
public class EmpController {
    @Autowired
    private EmpService service;

    @Autowired
    private DeptService deptService;

    @Autowired
    private MongoTemplate Template;


    @GetMapping("/deleteByid")
    public Long deleteUserById(int empno,Emp emp) {
        Query query=new Query(Criteria.where("empno").is(empno));
        return Template.remove(query,Emp.class)
                .getDeletedCount();
    }

    @GetMapping("/select2")
    public List<Dept> page2(int pageIndex, int pageSize){
        List<Dept> list = service.findDept(new QueryWrapper());
        System.out.println("list"+list);
        Template.insert(list,Dept.class);
        return list;
    }



    @ResponseBody
    @GetMapping("/insertOne")
    public Emp insert(Emp emp){
        return Template.save(emp);
    }

    @GetMapping("/insert")
    public List<Emp> insert(){
        List<Emp> list = service.list();
        return (List<Emp>) Template.insertAll(list);
    }

    @GetMapping("/insertdept")
    public List<Dept> insertdept(){
        List<Dept> list = deptService.list();
        return (List<Dept>) Template.insertAll(list);
    }

    @GetMapping("/findbyname")
    public List<Emp> findbyname(String ename){
        return service.findByEname(ename);
    }

    @GetMapping("/findEmp")
    public List<Emp> find(String ename,int pageIndex,int pageSize){
        Query query = new Query();
        Pattern pattern = Pattern.compile("^.*" + ename + ".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = Criteria.where("ename").regex(pattern);
        query.addCriteria(criteria);
        long count = Template.count(query,Emp.class);
        System.out.println("count:"+count);
        query.with(Sort.by(Sort.Direction.DESC,"empno"));
        query.skip((pageIndex-1)*pageSize);
        query.limit(pageSize);
        return Template.find(query,Emp.class);
    }

    @GetMapping("/page")
    public List<Emp> page(int pageIndex, int pageSize, String ename, Date hiredate){
        return service.findByPage(
                new Page<Emp>(pageIndex,pageSize),
                new QueryWrapper<Emp>()
                        .like("ename",ename)
                        .or()
                        .le("hiredate",hiredate));
    }

    @GetMapping("/removeEmp")
    public void remove(){
        service.removeEmp();
    }

    //一对多
   @GetMapping("/updateemp")
   public Dept updatedept(Dept dept){
       Query query = new Query();
       System.out.println("dept"+dept);
       Emp emp=new Emp();
       List<Emp> list=service.getBaseMapper().selectList(new QueryWrapper<Emp>().eq("deptno",dept.getDeptno()));
//       List<Emp> listemp=Template.find(new Query(Criteria.where("emp.deptno").is(dept.getDeptno())),Emp.class);
//       query.addCriteria(Criteria.where("dept.deptno").is(dept.getDeptno()));
//       List<Emp> list=Template.find(query,Emp.class);
       System.out.println("list"+list);
       for (Emp e:list) {
           e.setDept(dept);
           System.out.println("修改"+e);
           Template.save(e);
       }
       return dept;
   }
    @GetMapping("/updateDept")
    public Dept updatemp(Emp emp){

        Query query = new Query();
        query.addCriteria(Criteria.where("emp.deptno").is(emp.getDeptno()));
        System.out.println("list"+Template.find(query,Dept.class));
        Dept dept = Template.find(query,Dept.class).get(0);
        Set<Emp> emps = dept.getEmps();
        if(emps == null){
            emps = new HashSet<>();
            dept.setEmps(emps); //设置关联关系
        }
        //修改先删除
//        emps.remove(emp);
        //新增员工
        emps.add(emp);
        Template.save(dept); //级联更新

        return dept;
    }

}
