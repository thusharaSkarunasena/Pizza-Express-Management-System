package lk.ijse.pizza.view.tblModel;

public class Chef_Q_OrderTM {

    private String orderID;
    private String queuedTime;

    public Chef_Q_OrderTM() {
    }

    public Chef_Q_OrderTM(String orderID, String queuedTime) {
        this.orderID = orderID;
        this.queuedTime = queuedTime;
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

    @Override
    public String toString() {
        return "Chef_Q_OrderTM{" +
                "orderID='" + orderID + '\'' +
                ", queuedTime='" + queuedTime + '\'' +
                '}';
    }
}
