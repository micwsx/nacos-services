package com.micwsx.project;

import model.StockProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Michael
 * @create 10/16/2020 3:57 PM
 */

@SpringBootTest
public class StoreTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testStock() {
        // 查商品库存
        StockProduct stockProduct=new StockProduct(2, 1);
        stockProduct= restTemplate.postForObject("http://stock-service/stock/get", stockProduct, StockProduct.class);
        System.out.println(stockProduct);
    }

}
