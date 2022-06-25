package lk.ijse.pizza.model;

import java.io.Serializable;

public class Item implements Serializable {

    private String itemCode;
    private String name;
    private String description;
    private String size;
    private double price;
    private String other_detail;

    public Item() {
    }

    public Item(String itemCode, String name, String description, String size, double price, String other_detail) {
        this.itemCode = itemCode;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.other_detail = other_detail;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOther_detail() {
        return other_detail;
    }

    public void setOther_detail(String other_detail) {
        this.other_detail = other_detail;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", other_detail='" + other_detail + '\'' +
                '}';
    }
}
