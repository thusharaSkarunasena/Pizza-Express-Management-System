package lk.ijse.pizza.model;

import java.io.Serializable;

public class Admin_ViewOrderDetail implements Serializable {

    private String itemCode;
    private String name;
    private String size;
    private int qty;

    public Admin_ViewOrderDetail() {
    }

    public Admin_ViewOrderDetail(String itemCode, String name, String size, int qty) {
        this.itemCode = itemCode;
        this.name = name;
        this.size = size;
        this.qty = qty;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Admin_ViewOrderDetail{" +
                "itemCode='" + itemCode + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", qty=" + qty +
                '}';
    }
}
