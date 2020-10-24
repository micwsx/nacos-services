package com.micwsx.project.controller;

import io.seata.spring.annotation.GlobalTransactional;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.StockProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Michael
 * @create 10/9/2020 1:13 PM
 */
@Controller
@RequestMapping("/store")
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/home")
    public String portal(Model model) {
        List<Order> orders = restTemplate.getForObject("http://order-service/order/list", List.class);
        List<Product> products = restTemplate.getForObject("http://product-service/product/list", List.class);
        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/placeOrder/{productId}/{quantity}")
    @ResponseBody
    public String placeOrder(@PathVariable Integer productId, @PathVariable Integer quantity) {
        try {
            // 查询商品数据
            Product product = restTemplate.getForObject("http://product-service/product/" + productId, Product.class);
            String orderId = UUID.randomUUID().toString().replace("-", "");
            Order order = new Order(orderId, "TestOrderNum" + productId, "测试下单", 0D);
            List<OrderDetail> orderDetails = order.getOrderDetails();
            OrderDetail item = new OrderDetail(orderId, product.getId(), product.getProductName(), quantity, product.getPrice());
            orderDetails.add(item);
            Double totalPrice = orderDetails.stream().collect(Collectors.summingDouble((d) -> {
                return d.getQuantity() * d.getPrice();
            }));
            order.setTotal_price(totalPrice);
            // 创建订单order
            Integer integer = restTemplate.postForObject("http://order-service/order/make", order, Integer.class);

            // 查商品库存
            StockProduct stockProduct = new StockProduct(2, productId);
            stockProduct = restTemplate.postForObject("http://stock-service/stock/get", stockProduct, StockProduct.class);
            // 扣减库存2号
            stockProduct.setQuantity(stockProduct.getQuantity() - quantity);
            // 更新库存
            Integer count = restTemplate.postForObject("http://stock-service/stock/update", stockProduct, Integer.class);
        } catch (Exception e) {
            return "下单失败: " + e.getMessage();
        }
        return "下单成功";
    }


    /**
     * 模拟seata分布工事务执行
     * @param productId
     * @param quantity
     * @return
     */
    @RequestMapping("/placeOrderTran/{productId}/{quantity}")
    @ResponseBody
    @GlobalTransactional
    public String placeOrderTransaction(@PathVariable Integer productId, @PathVariable Integer quantity) {
        // 查询商品数据
        Product product = restTemplate.getForObject("http://product-service/product/" + productId, Product.class);
        String orderId = UUID.randomUUID().toString().replace("-", "");
        Order order = new Order(orderId, "TestOrderNum" + productId, "测试下单", 0D);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        OrderDetail item = new OrderDetail(orderId, product.getId(), product.getProductName(), quantity, product.getPrice());
        orderDetails.add(item);
        Double totalPrice = orderDetails.stream().collect(Collectors.summingDouble((d) -> {
            return d.getQuantity() * d.getPrice();
        }));
        order.setTotal_price(totalPrice);
        // 创建订单order-执行插入数据库操作
        Integer integer = restTemplate.postForObject("http://order-service/order/make", order, Integer.class);
        // 查商品库存
        StockProduct stockProduct = new StockProduct(2, productId);
        stockProduct = restTemplate.postForObject("http://stock-service/stock/get", stockProduct, StockProduct.class);
        // 扣减库存2号
        stockProduct.setQuantity(stockProduct.getQuantity() - quantity);
        // 更新库存-执行修改数据库操作
        Integer count = restTemplate.postForObject("http://stock-service/stock/update", stockProduct, Integer.class);
        return "下单成功";
    }


}
