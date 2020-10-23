package com.micwsx.project.dao;

import model.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 3:29 PM
 */
public interface OrderDetailMapper {
    @Select({
            "select * from `order_detail` where id=#{id}"
    })
    OrderDetail getById(@Param("id") Integer id);

    @Select({
            "select * from `order_detail` where orderId=#{id}"
    })
    List<OrderDetail> getByOrderId(@Param("id") String orderId);

    @Select("select * from `order_detail`")
    List<OrderDetail> getAll();

    @Insert({
            "insert into `order_detail`(orderId, productId, product_name, quantity, price)"
                    + "values(#{orderId},#{productId},#{product_name},#{quantity},#{price})"
    })
    int insert(OrderDetail orderDetail);

    @Insert({
            "<script>" +
                    "insert into `order_detail`(orderId, productId, product_name, quantity, price) values" +
                    "<foreach collection='list' item='item' separator=','>" +
                    "(#{item.orderId},#{item.productId},#{item.product_name},#{item.quantity},#{item.price})" +
                    "</foreach>" +
                    "</script>"
    })
    int insertBatch(@Param("list") List<OrderDetail> list);

    @Delete("delete from `order_detail` where id=#{id}")
    int delete(@Param("id") Integer id);

    @Delete({
            "<script>" +
                "delete from `order_detail` where id in " +
                    "<foreach collection='array' item='id' open='(' separator=',' close=')'>"+
                    "#{id}"+
                    "</foreach>"+
            "</script>"})
    int deleteBatch(int[] id);

    @Delete("delete from `order_detail` where orderId=#{id}")
    int deleteByOrderId(@Param("id") String orderId);

}
