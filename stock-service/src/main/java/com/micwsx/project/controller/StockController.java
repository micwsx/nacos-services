package com.micwsx.project.controller;

import com.micwsx.project.service.StockService;
import model.Stock;
import model.StockProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michael
 * @create 10/15/2020 3:50 PM
 */
@RestController
public class StockController {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private StockService stockService;

    // 获取某个仓库某个商品
    @RequestMapping("/stock/{stockId}/{productId}")
    public Integer getQuantity(@PathVariable Integer stockId,@PathVariable Integer productId){
        Integer quantity = stockService.getQuantity(stockId, productId);
        logger.info("获取库存["+stockId+"]商品["+productId+"]："+quantity);
        return quantity;
    }

    @RequestMapping(value ="/stock/get",method = RequestMethod.POST)
    public StockProduct get(@RequestBody StockProduct stockProduct){
        StockProduct quantity = stockService.get(stockProduct);
        logger.info("获取库存["+stockProduct.getStockId()+"]商品["+stockProduct.getProductId()+"]："+quantity);
        return quantity;
    }



    @RequestMapping(value = "/stock/update",method = RequestMethod.POST)
    public Integer updateQuantity(@RequestBody StockProduct stockProduct){
        logger.info("更新商品库存："+stockProduct);
        return stockService.updateQuantity(stockProduct);
    }

    @RequestMapping(value = "/stock/add",method = RequestMethod.POST)
    public Integer addStockProduct(@RequestBody StockProduct stockProduct){
        logger.info("添加商品库存："+stockProduct);
        return stockService.add(stockProduct);
    }

    // 获取某个仓库
    @RequestMapping("/stock/{stockId}")
    public Stock getStock(@PathVariable Integer stockId){
        return stockService.getStock(stockId);
    }
}
