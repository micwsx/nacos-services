package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 * @create 9/30/2020 3:28 PM
 */
public class Order {

    private String id;
    private String orderNum;
    private String introduction;
    private Double total_price;
    private Date createdTime;
    private List<OrderDetail> orderDetails=new ArrayList<>();

    public Order() {
    }

    public Order(String id,String orderNum, String introduction, Double total_price) {
        this.id=id;
        this.orderNum=orderNum;
        this.introduction = introduction;
        this.total_price = total_price;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", introduction='" + introduction + '\'' +
                ", total_price=" + total_price +
                ", createdTime=" + createdTime +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
