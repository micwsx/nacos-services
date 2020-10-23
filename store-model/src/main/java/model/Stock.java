package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael
 * @create 10/15/2020 4:25 PM
 */
public class Stock {
    private Integer id;
    private String name;
    private String location;

    private List<StockProduct> products=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", products=" + products +
                '}';
    }
}
