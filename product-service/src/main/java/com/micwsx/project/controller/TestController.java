package com.micwsx.project.controller;

import com.micwsx.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 使用了@SentinelResource注解，监控接口
    @Autowired
    private ProductService productService;

    @GetMapping("/test/sayhello")
    public String say(){
        return productService.sayHello();
    }
    // http://192.168.1.12:9000/log
    // 这种写法可以被直接添加到资源下
    @GetMapping("/log")
    public String log(){
        return "log";
    }
}
