package com.micwsx.project.service;

import com.micwsx.project.dao.OrderDetailMapper;
import com.micwsx.project.dao.OrderMapper;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 3:29 PM
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public Order getOrder(String orderId){
        return orderMapper.getById(orderId);
    }

    public List<Order> getAll(){
        return orderMapper.getAll();
    }

    public int insertOrder(Order order){
        int i = orderMapper.insert(order);
        if (order.getOrderDetails().size()>0){
            int detailCount = orderDetailMapper.insertBatch(order.getOrderDetails());
        }
        return i;
    }


    public int deleteOrder(Order order){
        int i = orderMapper.delete(order.getId());
        int detailCount = orderDetailMapper.deleteByOrderId(order.getId());
        return i;
    }

}
