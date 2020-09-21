package com.micwsx.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

@RestController
@RequestMapping("/product")
@RefreshScope
public class ProductController {

    @Autowired
    public ServerProperties serverProperties;

//    @Value("${redis.username}")
    private String redisUsername;

    // http://192.168.1.12:9000/product/redis
    @GetMapping("/redis")
    public String redis(){
        return "服务器["+serverProperties.getPort()+"]"+redisUsername;
    }


    @RequestMapping("/{productName}")
    public String detail(@PathVariable(name = "productName") String productName){
        DecimalFormat decimalFormat=new DecimalFormat();
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        Random random=new Random();
        return "服务器["+serverProperties.getPort()+"]->查询商品"+productName+" price: $"+decimalFormat.format(100*random.nextDouble());
    }

}
