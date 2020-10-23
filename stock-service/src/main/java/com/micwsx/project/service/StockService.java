package com.micwsx.project.service;

import com.micwsx.project.dao.StockMapper;
import com.micwsx.project.dao.StockProductMapper;
import model.Stock;
import model.StockProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Michael
 * @create 10/15/2020 4:03 PM
 */
@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockProductMapper stockProductMapper;

    // 获取仓库产品库存
    public Integer getQuantity(Integer stockId, Integer productId) {
        StockProduct stockProduct = stockProductMapper.getStockProduct(new StockProduct(stockId, productId));
        return stockProduct == null ? 0 : stockProduct.getQuantity();
    }

    public StockProduct get(StockProduct stockProduct) {
        StockProduct result = stockProductMapper.getStockProduct(stockProduct);
        return result;
    }


    // 获取仓库
    public Stock getStock(Integer stockId) {
        return stockMapper.getStock(stockId);
    }

    // 更新商品库存
    public Integer updateQuantity(StockProduct stockProduct) {
        return stockProductMapper.updateQuantity(stockProduct);
    }
    // 添加商品库存
    public Integer add(StockProduct stockProduct) {
        return stockProductMapper.addStockProduct(stockProduct);
    }

}
