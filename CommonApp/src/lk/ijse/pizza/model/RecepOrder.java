package lk.ijse.pizza.model;

import java.io.Serializable;

public class RecepOrder implements Serializable {

    private String orderID;
    private String orderDate;
    private String customerID;
    private double totalAmount;
    private double discount;
    private double netAmount;
    private String employeeID;
    private String status;

    public RecepOrder() {
    }

    public RecepOrder(String orderID, String orderDate, String customerID, double totalAmount, double discount, double netAmount, String employeeID, String status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.netAmount = netAmount;
        this.employeeID = employeeID;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RecepOrder{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerID='" + customerID + '\'' +
                ", totalAmount=" + totalAmount +
                ", discount=" + discount +
                ", netAmount=" + netAmount +
                ", employeeID='" + employeeID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
