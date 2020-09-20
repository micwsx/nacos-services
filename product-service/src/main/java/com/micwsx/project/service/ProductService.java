package com.micwsx.project.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @SentinelResource(value = "sayHello",fallback = "sayHellofail")
    public String sayHello(){
        return "Hello,World";
    }

    public  String sayHellofail(){
        return "I'am sorry";
    }
}
