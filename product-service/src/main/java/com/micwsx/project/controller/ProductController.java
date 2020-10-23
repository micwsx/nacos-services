package com.micwsx.project.controller;

import com.micwsx.project.service.ProductService;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
public class ProductController {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping("/product/{id}")
    public Product showProduct(@PathVariable Integer id) {
        Product product = productService.getProduct(id);
        logger.info("查询商品数据："+product);
        return product;
    }


    @RequestMapping("/product/list")
    public List<Product> listProduct() {
        List<Product> products = productService.getAll();
        logger.info("查询商户列表："+products);
        return products;
    }

    @Autowired
    public ServerProperties serverProperties;

    //    @Value("${redis.username}")
    private String redisUsername;

    // http://192.168.1.12:9000/product/redis
    @GetMapping("/redis")
    public String redis() {
        return "服务器[" + serverProperties.getPort() + "]" + redisUsername;
    }

    @RequestMapping("/product/test")
    public String detail() {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        Random random = new Random();
        return "服务器[" + serverProperties.getPort() + "]->返回商品价格price: $" + decimalFormat.format(100 * random.nextDouble());
    }

}
