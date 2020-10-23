package com.micwsx.project.dao;

import model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 3:29 PM
 */
public interface OrderMapper {

    @Select({
            "select * from `order` where id=#{id}"
    })
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "orderDetails",column = "id",javaType = List.class,
            many = @Many(select = "com.micwsx.project.dao.OrderDetailMapper.getByOrderId"))
    })
    Order getById(@Param("id") String orderId);

    @Select("select * from `order`")
    @Results({
            @Result(property = "orderDetails",column = "id",javaType = List.class,
                    many = @Many(select = "com.micwsx.project.dao.OrderDetailMapper.getByOrderId"))
    })
    List<Order> getAll();

    @Insert({
            "insert into `order`(id,orderNum,introduction,total_price) " +
            "values(#{id},#{orderNum},#{introduction},#{total_price})"
    })
    int insert(Order order);

    @Delete("delete from `order` where id=#{id}")
    int delete(@Param("id") String orderId);

}
