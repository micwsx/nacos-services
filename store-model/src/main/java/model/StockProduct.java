package model;

/**
 * @author Michael
 * @create 10/15/2020 4:26 PM
 */
public class StockProduct {
    private Integer id;
    private Integer stockId;
    private Integer productId;
    private String productName;
    private Integer quantity;

    public StockProduct() {
    }

    public StockProduct(Integer stockId, Integer productId) {
        this.stockId = stockId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockProduct{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
