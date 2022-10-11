package com.dongdongshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dongdongshop.mapper")
public class SellerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SellerServiceApp.class,args);
    }
}
