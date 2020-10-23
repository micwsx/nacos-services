package model;

import java.util.Date;

/**
 * @author Michael
 * @create 9/30/2020 3:28 PM
 */
public class OrderDetail {

    private Integer id;
    private String orderId;
    private Integer productId;
    private String product_name;
    private Integer quantity;
    private Double price;
    private Date createdTime;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, Integer productId, String product_name, Integer quantity, Double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", productId=" + productId +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", createdTime=" + createdTime +
                '}';
    }
}
