package com.micwsx.project.controller;

import com.micwsx.project.service.OrderService;
import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderService orderService;

    @Autowired
    public RestTemplate restTemplate;


    /**
     * //http://192.168.1.20:8888/order/detail/oldman
     * 通过ribbon直接负载均衡调用product-service服务
     *
     * @param productName
     * @return
     */
    @RequestMapping("/order/detail/{productName}")
    public String detail(@PathVariable(name = "productName") String productName) {
        //9001  9002
        return restTemplate.getForObject("http://product-service/product/" + productName, String.class);
    }

    @RequestMapping("/order/{orderId}")
    public String echo(@PathVariable(name = "orderId") Integer orderId) {
        return "Hello Nacos Discovery " + orderId;
    }

    // 下单操作
    @RequestMapping(value = "/order/make",method = RequestMethod.POST)
    public int placeAnOrder(@RequestBody Order order) {
        logger.info("收到订单：" + order);
        return orderService.insertOrder(order);
    }

    // 获取所有订单数据
    @RequestMapping("/order/list")
    public List<Order> list() {
        return orderService.getAll();
    }


}
