package com.cssl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cssl.pojo.Dept;
import com.cssl.pojo.Emp;
import org.springframework.data.domain.Page;


import java.util.List;


public interface EmpService extends IService<Emp> {

    public List<Dept> findDept(QueryWrapper ew);

    public List<Emp> findByPage(IPage<Emp> page, QueryWrapper ew);

    public List<Emp> findByEname(String ename);

    public Page<Emp> findByEmp(int pageIndex, int pageSize);

    public List<Emp> saveEmp(List<Emp> list);

    public Emp saveOne(Emp emp);

    public void removeEmp();

}
