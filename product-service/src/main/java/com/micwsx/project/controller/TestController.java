package com.micwsx.project.controller;

import com.micwsx.project.service.ProductServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 使用了@SentinelResource注解，监控接口
    @Autowired
    private ProductServiceTest productServiceTest;

    @GetMapping("/test/sayhello")
    public String sayHello(String name){
        return productServiceTest.sayHello(name);
    }

    @GetMapping("/test/saybye")
    public String saybye(String name){
        return productServiceTest.sayBye(name);
    }

    @GetMapping("/test/sayHelloException")
    public String sayHelloException(String name){
        return productServiceTest.sayHelloException(name);
    }
    @GetMapping("/test/sayByeException")
    public String sayByeException(String name){
        return productServiceTest.sayByeException(name);
    }


    // http://192.168.1.12:9000/log
    // 这种写法可以被直接添加到资源下
    @GetMapping("/log")
    public String log(){
        return "log";
    }
}
