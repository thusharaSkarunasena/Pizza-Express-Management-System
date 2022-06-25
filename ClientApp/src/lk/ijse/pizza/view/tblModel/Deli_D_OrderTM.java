package lk.ijse.pizza.view.tblModel;

public class Deli_D_OrderTM {

    private String orderDate;
        private String orderID;

    public Deli_D_OrderTM() {
    }

    public Deli_D_OrderTM(String orderDate, String orderID) {
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
        return "Deli_D_OrderTM{" +
                "orderDate='" + orderDate + '\'' +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
