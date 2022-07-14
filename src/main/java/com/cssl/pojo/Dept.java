package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Set;

@Data
@TableName
@Document("dept")
public class Dept implements Serializable {
    @MongoId
    @TableId
    private int deptno;
    private String dname;
    private String loc;

    @TableField(exist = false)
    private Set<Emp> emps;
}
