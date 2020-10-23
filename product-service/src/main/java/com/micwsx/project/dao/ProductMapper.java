package com.micwsx.project.dao;

import model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 12:00 PM
 * dao代理类操作数据库
 */
public interface ProductMapper {

    @Select("select * from product where id=#{id}")
    @Results({
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "productName",column = "name",javaType = String.class),
            @Result(property = "price",column = "price",javaType = Double.class),
            @Result(property = "content",column = "content",javaType = String.class)
    })
    @ConstructorArgs({
            @Arg(column = "id",javaType = Integer.class,id = true),
            @Arg(column = "name",javaType = String.class),
            @Arg(column = "price",javaType = Double.class),
            @Arg(column = "content",javaType = String.class)
    })
    Product getProduct(@Param("id") Integer id);

    @Select("select * from product")
    @Results({
            @Result(property = "id",column = "id",javaType = Integer.class),
            @Result(property = "productName",column = "name",javaType = String.class),
            @Result(property = "price",column = "price",javaType = Double.class),
            @Result(property = "content",column = "content",javaType = String.class)
    })
    @ConstructorArgs({
            @Arg(column = "id",javaType = Integer.class,id = true),
            @Arg(column = "name",javaType = String.class),
            @Arg(column = "price",javaType = Double.class),
            @Arg(column = "content",javaType = String.class)
    })
    List<Product> getAll();

    @Insert("insert into product(id,name,price,content) values(#{id},#{productName},#{price},#{content})")
    int add(Product product);

    @Delete("delete from product where id=#{id}")
    int delete(@Param("id") Integer id);

}
