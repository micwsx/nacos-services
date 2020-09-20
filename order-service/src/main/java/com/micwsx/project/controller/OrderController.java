package com.micwsx.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/{orderId}")
    public String echo(@PathVariable(name = "orderId") String orderId){
        return "Hello Nacos Discovery " + orderId;
    }

    @Autowired
    public RestTemplate restTemplate;

    //http://192.168.1.20:8888/order/detail/oldman
    @RequestMapping("/detail/{productName}")
    public String detail(@PathVariable(name = "productName") String productName){
        /**
         * 9001  9002
         */
        return  restTemplate.getForObject("http://product-service/product/"+productName, String.class);

    }

}
