package lk.ijse.pizza.model;

import java.io.Serializable;

public class Chef_Q_Order implements Serializable {
    private String orderID;
    private String queuedTime;
    private String status;

    public Chef_Q_Order() {
    }

    public Chef_Q_Order(String orderID, String queuedTime, String status) {
        this.orderID = orderID;
        this.queuedTime = queuedTime;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getQueuedTime() {
        return queuedTime;
    }

    public void setQueuedTime(String queuedTime) {
        this.queuedTime = queuedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Chef_Q_Order{" +
                "orderID='" + orderID + '\'' +
                ", queuedTime='" + queuedTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
