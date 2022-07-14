package com.cssl.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cssl.pojo.Dept;
import com.cssl.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper extends BaseMapper<Emp> {

    public List<Emp> select(IPage<Emp> page,@Param("ew") QueryWrapper<Emp> qw);

    public List<Dept> selectDept(@Param("ew") QueryWrapper<Emp> qw);

}
