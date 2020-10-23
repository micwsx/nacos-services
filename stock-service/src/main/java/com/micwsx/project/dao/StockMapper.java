package com.micwsx.project.dao;

import model.Stock;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Michael
 * @create 10/15/2020 3:54 PM
 */
public interface StockMapper {
    @Select({
            "select * from stock where id=#{id}"
    })
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "location", column = "location", javaType = String.class),
            @Result(property = "products", column = "id", javaType = List.class,
                    many = @Many(select = "com.micwsx.project.dao.StockProductMapper.getProduct"))
    })
    Stock getStock(@Param("id") Integer stockId);//获取仓库及仓库所有商品库存

    @Insert("insert into stock(id,name,location) values(#{id},#{name},#{location})")
    Integer add(Stock stock);//添加仓库


    // TODO: 删除主表时如何同时删除子表数据
    @Delete({"delete from stock where id=#{stockId}"})
    Integer delete(Integer stockId);//删除仓库同时也删除所有商品库存

}
