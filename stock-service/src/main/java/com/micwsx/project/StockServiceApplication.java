package com.micwsx.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Michael
 * @create 10/15/2020 3:46 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.micwsx.project.dao")
public class StockServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class,args);
    }
}
