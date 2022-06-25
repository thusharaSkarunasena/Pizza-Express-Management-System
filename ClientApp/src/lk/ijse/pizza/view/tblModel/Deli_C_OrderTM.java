package lk.ijse.pizza.view.tblModel;

public class Deli_C_OrderTM {

    private String orderID;
    private String cookedTime;

    public Deli_C_OrderTM() {
    }

    public Deli_C_OrderTM(String orderID, String cookedTime) {
        this.orderID = orderID;
        this.cookedTime = cookedTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCookedTime() {
        return cookedTime;
    }

    public void setCookedTime(String cookedTime) {
        this.cookedTime = cookedTime;
    }

    @Override
    public String toString() {
        return "Deli_C_OrderTM{" +
                "orderID='" + orderID + '\'' +
                ", cookedTime='" + cookedTime + '\'' +
                '}';
    }
}
