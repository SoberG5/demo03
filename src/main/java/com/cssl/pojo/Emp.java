package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;


@EqualsAndHashCode(of = "empno")
@Data
@TableName
public class Emp implements Serializable {

    public Emp(int empno, String ename,  double sal ,Date hiredate) {
        super();
        this.empno = empno;
        this.ename = ename;
        this.hiredate = hiredate;
        this.sal = sal;
    }
    public Emp() {
        super();
    }


    @TableId(type= IdType.AUTO)
    @MongoId
    private int empno;
    private String ename;
    private Date hiredate;
    private double sal;
    private int deptno;

    @TableField(exist = false)
    private Dept dept;
}
