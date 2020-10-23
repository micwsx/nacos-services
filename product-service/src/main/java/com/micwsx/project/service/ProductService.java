package com.micwsx.project.service;

import com.micwsx.project.dao.ProductMapper;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 2:22 PM
 * 这里可以加sentinel流控
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product getProduct(Integer productId){
        return productMapper.getProduct(productId);
    }

    public List<Product> getAll(){
        return productMapper.getAll();
    }

    public int deleteProduct(Integer id){
        return productMapper.delete(id);
    }

    public int addProduct(Product product){
        return productMapper.add(product);
    }

}
