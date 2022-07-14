package com.cssl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.dao.DeptMapper;
import com.cssl.pojo.Dept;
import com.cssl.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper mapper;

    @Override
    public List<Dept> selectxlk() {
        return mapper.selectxlk();
    }
}
