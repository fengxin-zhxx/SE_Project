package com.example.se_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.se_project.mapper")
public class SeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeProjectApplication.class, args);
    }

}
