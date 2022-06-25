package lk.ijse.pizza.view.tblModel;

public class Recep_OrderManagementTM {

    private String orderID;
    private String orderDate;
    private String customerID;
    private String netAmount;
    private String status;

    public Recep_OrderManagementTM() {
    }

    public Recep_OrderManagementTM(String orderID, String orderDate, String customerID, String netAmount, String status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.netAmount = netAmount;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Recep_OrderManagementTM{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerID='" + customerID + '\'' +
                ", netAmount='" + netAmount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
