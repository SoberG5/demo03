package com.cssl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.cssl.dao")
@SpringBootApplication
public class Demo03Application  {

    public static void main(String[] args) {
        SpringApplication.run(Demo03Application.class, args);
    }



}
