package lk.ijse.pizza.model;

import java.io.Serializable;

public class Admin_ViewOrder implements Serializable {

    private String orderID;
    private String date;
    private String customerID;
    private double total;
    private double discount;
    private double netAmount;
    private String status;

    private String recep_employeeID;
    private String recep_queuedTime;

    private String chef_employeeID;
    private String chef_acceptedTime;
    private String chef_finishedTime;

    private String deli_employeeID;
    private String deli_acceptedTime;
    private String deli_finishedTime;

    public Admin_ViewOrder() {
    }

    public Admin_ViewOrder(String orderID, String date, String customerID, double total, double discount, double netAmount, String status, String recep_employeeID, String recep_queuedTime, String chef_employeeID, String chef_acceptedTime, String chef_finishedTime, String deli_employeeID, String deli_acceptedTime, String deli_finishedTime) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.total = total;
        this.discount = discount;
        this.netAmount = netAmount;
        this.status = status;
        this.recep_employeeID = recep_employeeID;
        this.recep_queuedTime = recep_queuedTime;
        this.chef_employeeID = chef_employeeID;
        this.chef_acceptedTime = chef_acceptedTime;
        this.chef_finishedTime = chef_finishedTime;
        this.deli_employeeID = deli_employeeID;
        this.deli_acceptedTime = deli_acceptedTime;
        this.deli_finishedTime = deli_finishedTime;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecep_employeeID() {
        return recep_employeeID;
    }

    public void setRecep_employeeID(String recep_employeeID) {
        this.recep_employeeID = recep_employeeID;
    }

    public String getRecep_queuedTime() {
        return recep_queuedTime;
    }

    public void setRecep_queuedTime(String recep_queuedTime) {
        this.recep_queuedTime = recep_queuedTime;
    }

    public String getChef_employeeID() {
        return chef_employeeID;
    }

    public void setChef_employeeID(String chef_employeeID) {
        this.chef_employeeID = chef_employeeID;
    }

    public String getChef_acceptedTime() {
        return chef_acceptedTime;
    }

    public void setChef_acceptedTime(String chef_acceptedTime) {
        this.chef_acceptedTime = chef_acceptedTime;
    }

    public String getChef_finishedTime() {
        return chef_finishedTime;
    }

    public void setChef_finishedTime(String chef_finishedTime) {
        this.chef_finishedTime = chef_finishedTime;
    }

    public String getDeli_employeeID() {
        return deli_employeeID;
    }

    public void setDeli_employeeID(String deli_employeeID) {
        this.deli_employeeID = deli_employeeID;
    }

    public String getDeli_acceptedTime() {
        return deli_acceptedTime;
    }

    public void setDeli_acceptedTime(String deli_acceptedTime) {
        this.deli_acceptedTime = deli_acceptedTime;
    }

    public String getDeli_finishedTime() {
        return deli_finishedTime;
    }

    public void setDeli_finishedTime(String deli_finishedTime) {
        this.deli_finishedTime = deli_finishedTime;
    }

    @Override
    public String toString() {
        return "Admin_ViewOrder{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                ", netAmount=" + netAmount +
                ", status='" + status + '\'' +
                ", recep_employeeID='" + recep_employeeID + '\'' +
                ", recep_queuedTime='" + recep_queuedTime + '\'' +
                ", chef_employeeID='" + chef_employeeID + '\'' +
                ", chef_acceptedTime='" + chef_acceptedTime + '\'' +
                ", chef_finishedTime='" + chef_finishedTime + '\'' +
                ", deli_employeeID='" + deli_employeeID + '\'' +
                ", deli_acceptedTime='" + deli_acceptedTime + '\'' +
                ", deli_finishedTime='" + deli_finishedTime + '\'' +
                '}';
    }
}
