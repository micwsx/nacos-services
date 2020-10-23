package model;

/**
 * @author Michael
 * @create 9/30/2020 2:12 PM
 */
public class Product {

    private Integer id;
    private String productName;
    private Double price;
    private String content;

    public Product() {
    }

    public Product(Integer id, String productName, Double price, String content) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                '}';
    }
}
