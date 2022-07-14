package com.cssl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cssl.pojo.Dept;

import java.util.List;

public interface DeptService extends IService<Dept>{
    public List<Dept> selectxlk();
}
