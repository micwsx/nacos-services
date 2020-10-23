package com.micwsx.project;

import com.micwsx.project.dao.StockMapper;
import com.micwsx.project.dao.StockProductMapper;
import model.Stock;
import model.StockProduct;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Michael
 * @create 10/15/2020 5:10 PM
 */
@SpringBootTest
@MapperScan("com.micwsx.project.dao")
public class StockTest {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockProductMapper stockProductMapper;

    @Test
    public void stockProductOperation() {

//        StockProduct stockProduct=new StockProduct();
//        stockProduct.setId(2);
//        stockProduct.setQuantity(100);
//        stockProductMapper.updateQuantity(stockProduct);

//        StockProduct stockProduct = stockProductMapper.getStockProduct(new StockProduct(2, 1));
//        System.out.println(stockProduct);

//        StockProduct stockProduct=new StockProduct();
//        stockProduct.setId(1);
//        stockProduct.setStockId(1);
//        stockProduct.setProductId(1);
//        Integer integer = stockProductMapper.deleteStockProduct(stockProduct);
//        System.out.println(integer);

        StockProduct stockProduct = stockProductMapper.getStockProduct(new StockProduct(2, 1));
        System.out.println(stockProduct);


//        stockProductMapper.deleteStockProduct(1);

//        StockProduct stockProduct=new StockProduct();
//        stockProduct.setStockId(2);
//        stockProduct.setProductId(1);
//        stockProduct.setProductName("old and sea");
//        stockProduct.setQuantity(99);
//        Integer integer = stockProductMapper.addStockProduct(stockProduct);
//        System.out.println(integer);

//        Stock stock=new Stock();
//        stock.setName("黄浦仓库");
//        stock.setLocation("上海");
//        stockMapper.add(stock);
    }


    @Test
    public void stockOperation() {

//        stockMapper.delete(1);
//
//        Stock stock=new Stock();
//        stock.setName("黄浦仓库");
//        stock.setLocation("上海");
//        stockMapper.add(stock);

        Stock stock = stockMapper.getStock(2);
        System.out.println(stock);

    }


}
