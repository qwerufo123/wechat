package com.fxj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan("com.fxj")
@EnableFeignClients
@MapperScan("com.fxj.mapper")
public class FxjApplication {

    public static void main(String[] args) {
        SpringApplication.run(FxjApplication.class, args);
    }

}
