package lk.ijse.pizza.view.tblModel;

public class Chef_C_OrderTM {

    private String orderDate;
    private String orderID;

    public Chef_C_OrderTM() {
    }

    public Chef_C_OrderTM(String orderDate, String orderID) {
        this.orderDate = orderDate;
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Chef_C_OrderTM{" +
                "orderDate='" + orderDate + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
