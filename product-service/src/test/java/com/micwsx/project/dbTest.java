package com.micwsx.project;

import com.micwsx.project.service.ProductService;
import model.Product;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 2:29 PM
 */
@SpringBootTest
@MapperScan("com.micwsx.project.dao")
public class dbTest {

    @Autowired
    private ProductService productService;


    @Test
    public void productOper(){

//        Product product2 =new Product(2, "west journey", 20.80D, "fantasy fiction");
//        int i = productService.addProduct(product2);

//        Product product =new Product(1, "old and sea", 40.34D, "a good book");
//        int i = productService.addProduct(product);
//        System.out.println(i);
//
//        Product product1 = productService.getProduct(product.getId());
//        System.out.println(product1);
//
        List<Product> all = productService.getAll();
        System.out.println(all.size());

//        int i1 = productService.deleteProduct(product.getId());
//        System.out.println(i1);

    }

}
