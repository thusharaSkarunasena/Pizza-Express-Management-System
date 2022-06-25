package lk.ijse.pizza.model;

import java.io.Serializable;

public class RecepOrderItem implements Serializable {

    private String itemCode;
    private int qty;
    private double total;

    public RecepOrderItem() {
    }

    public RecepOrderItem(String itemCode, int qty, double total) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "RecepOrderItem{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
