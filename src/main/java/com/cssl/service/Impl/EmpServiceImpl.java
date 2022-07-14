package com.cssl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.dao.EmpMapper;
import com.cssl.dao.EmpRepository;
import com.cssl.pojo.Emp;
import com.cssl.pojo.Dept;
import com.cssl.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper,Emp> implements EmpService {


    @Resource
    private EmpRepository empRepository;

    public List<Dept> findDept(QueryWrapper ew){
        return super.baseMapper.selectDept(ew);
    }

    @Override
    public List<Emp> findByPage(IPage<Emp> page, QueryWrapper ew){
        return super.baseMapper.select(page,ew);
    }


    @Override
    public List<Emp> findByEname(String ename) {
        return empRepository.findByEname(ename);
    }

    @Override
    public Page<Emp> findByEmp(int pageIndex, int pageSize){
        return empRepository.findAll(
                PageRequest.of(pageIndex,pageSize, Sort.by(Sort.Direction.DESC,"empno")));
    }

    @Override
    public List<Emp> saveEmp(List<Emp> list){
        //return empRepository.saveAll(list);
        return empRepository.insert(list);
    }

    @Override
    public Emp saveOne(Emp emp) {
        //return empRepository.save(emp);
        return empRepository.insert(emp);
    }

    @Override
    public void removeEmp(){
        empRepository.deleteAll();
    }
}
