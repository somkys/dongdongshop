package com.dongdongshop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AdminWebApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApp.class,args);
    }
}
