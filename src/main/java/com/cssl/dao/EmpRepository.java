package com.cssl.dao;

import com.cssl.pojo.Emp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmpRepository extends MongoRepository<Emp,Integer> {
    public List<Emp> findByEname(String ename);

}
