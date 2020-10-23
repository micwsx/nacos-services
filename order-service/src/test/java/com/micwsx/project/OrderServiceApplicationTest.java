package com.micwsx.project;

import com.micwsx.project.service.OrderService;
import model.Order;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@MapperScan("com.micwsx.project.dao")
public class OrderServiceApplicationTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void orderOper() {


//        Order order=new Order("","TestOrderNum1","test order",0d);
//        OrderDetail orderDetail=new OrderDetail(order.getId(),1,"old and sea",2,40.34d);
//        OrderDetail orderDetail2=new OrderDetail(order.getId(),2,"west journey",1,20.80d);
//
//        List<OrderDetail> orderDetails = order.getOrderDetails();
//        orderDetails.add(orderDetail);
//        orderDetails.add(orderDetail2);
//        Double totalPrice = orderDetails.stream().collect(Collectors.summingDouble((d) -> {
//            return d.getQuantity() * d.getPrice();
//        }));
//        order.setTotal_price(totalPrice);
//
//        int delteCount = orderService.deleteOrder(order);
//        System.out.println(delteCount);

//        int i = orderService.insertOrder(order);
//        System.out.println(i);

        Order order1 = orderService.getOrder("4a3febcdb4814b73ab7652bad45f1822");
        System.out.println(order1);

    }
}
