package com.cssl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpVo {

    private int empno;
    private String ename;
    private Double sal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;

}
