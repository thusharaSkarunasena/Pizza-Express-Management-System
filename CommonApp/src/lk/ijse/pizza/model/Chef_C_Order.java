package lk.ijse.pizza.model;

import java.io.Serializable;

public class Chef_C_Order implements Serializable {

    private String orderID;
    private String orderDate;
    private String startedTime;
    private String endedTime;
    private String status;

    public Chef_C_Order() {
    }

    public Chef_C_Order(String orderID, String orderDate, String startedTime, String endedTime, String status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.startedTime = startedTime;
        this.endedTime = endedTime;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(String startedTime) {
        this.startedTime = startedTime;
    }

    public String getEndedTime() {
        return endedTime;
    }

    public void setEndedTime(String endedTime) {
        this.endedTime = endedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chef_C_Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", startedTime='" + startedTime + '\'' +
                ", endedTime='" + endedTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
