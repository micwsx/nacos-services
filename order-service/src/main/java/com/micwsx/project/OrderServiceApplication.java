package com.micwsx.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.micwsx.project.dao")
public class OrderServiceApplication
{

    // 使用ribbon
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main( String[] args ){
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
