package com.cssl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cssl.pojo.Dept;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept>{
    public List<Dept> selectxlk();
}
