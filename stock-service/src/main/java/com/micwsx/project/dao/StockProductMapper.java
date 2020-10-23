package com.micwsx.project.dao;

import model.StockProduct;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Michael
 * @create 10/15/2020 5:16 PM
 */
public interface StockProductMapper {
    @Select("<script>"+
            "select * from stock_product "+
            "<where>" +
            "<if test=\"id != null and id != ''\"> id=#{id}</if>" +
            "<if test=\"stockId != null and stockId != ''\"> and stockId=#{stockId}</if>" +
            "<if test=\"productId != null and productId != ''\"> and product_id=#{productId}</if>" +
            "</where>"+
            "</script>")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "stockId", column = "stockId", javaType = Integer.class),
            @Result(property = "productId", column = "product_Id", javaType = Integer.class),
            @Result(property = "productName", column = "product_name", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class)
    })
    StockProduct getStockProduct(StockProduct stockProduct);//获取商品仓库

    @Insert("insert into stock_product(stockId,product_id,product_name,quantity) values(#{stockId},#{productId},#{productName},#{quantity})")
    Integer addStockProduct(StockProduct stockProduct);//添加商品库存


    @Delete("<script>" +
            "delete from stock_product " +
            "<where>" +
            "<if test=\"id != null and id != ''\"> id=#{id}</if>" +
            "<if test=\"stockId != null and stockId != ''\"> and stockId=#{stockId}</if>" +
            "<if test=\"productId != null and productId != ''\"> and product_id=#{productId}</if>" +
            "</where>" +
            "</script>")
    Integer deleteStockProduct(StockProduct stockProduct);//删除某个商品库存

    @Delete("delete from stock_product where stockId=#{stockId}")
    Integer deleteStockProductByStockId(Integer stockId);//删除仓库里所有库存

    @Select({
            "select * from stock_product where stockId=#{id}"
    })
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "stockId", column = "stockId", javaType = Integer.class),
            @Result(property = "productId", column = "product_id", javaType = Integer.class),
            @Result(property = "productName", column = "product_name", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class),
    })
    List<StockProduct> getProduct(@Param("id") Integer stockId);//获取某仓库所有商品库存


    @Update("<script>"+
            "update stock_product"+
            " set quantity=#{quantity}"+
            "<where>" +
            "<if test=\"id != null and id != ''\"> id=#{id}</if>" +
            "<if test=\"stockId != null and stockId != ''\"> and stockId=#{stockId}</if>" +
            "<if test=\"productId != null and productId != ''\"> and product_id=#{productId}</if>" +
            "</where>" +
            "</script>")
    Integer updateQuantity(StockProduct stockProduct);

}
