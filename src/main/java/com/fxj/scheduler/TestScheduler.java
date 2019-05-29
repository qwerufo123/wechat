package com.fxj.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

//    @Scheduled(fixedRate = 1000l)
    private void test() {
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        System.out.println(driver);
    }

}
