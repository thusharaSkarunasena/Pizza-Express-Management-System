package lk.ijse.pizza.model;

import java.io.Serializable;

public class Deli_D_Order implements Serializable {

    private String orderID;
    private String orderDate;
    private String startedTime;
    private String deliveredTime;
    private String cusName;
    private String cusAddress_no;
    private String cusAddress_street;
    private String cusAddress_village;
    private String cusAddress_city;
    private String cusCNhome;
    private String cusCNMobile;
    private String status;

    public Deli_D_Order() {
    }

    public Deli_D_Order(String orderID, String orderDate, String startedTime, String deliveredTime, String cusName, String cusAddress_no, String cusAddress_street, String cusAddress_village, String cusAddress_city, String cusCNhome, String cusCNMobile, String status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.startedTime = startedTime;
        this.deliveredTime = deliveredTime;
        this.cusName = cusName;
        this.cusAddress_no = cusAddress_no;
        this.cusAddress_street = cusAddress_street;
        this.cusAddress_village = cusAddress_village;
        this.cusAddress_city = cusAddress_city;
        this.cusCNhome = cusCNhome;
        this.cusCNMobile = cusCNMobile;
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

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress_no() {
        return cusAddress_no;
    }

    public void setCusAddress_no(String cusAddress_no) {
        this.cusAddress_no = cusAddress_no;
    }

    public String getCusAddress_street() {
        return cusAddress_street;
    }

    public void setCusAddress_street(String cusAddress_street) {
        this.cusAddress_street = cusAddress_street;
    }

    public String getCusAddress_village() {
        return cusAddress_village;
    }

    public void setCusAddress_village(String cusAddress_village) {
        this.cusAddress_village = cusAddress_village;
    }

    public String getCusAddress_city() {
        return cusAddress_city;
    }

    public void setCusAddress_city(String cusAddress_city) {
        this.cusAddress_city = cusAddress_city;
    }

    public String getCusCNhome() {
        return cusCNhome;
    }

    public void setCusCNhome(String cusCNhome) {
        this.cusCNhome = cusCNhome;
    }

    public String getCusCNMobile() {
        return cusCNMobile;
    }

    public void setCusCNMobile(String cusCNMobile) {
        this.cusCNMobile = cusCNMobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Deli_D_Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", startedTime='" + startedTime + '\'' +
                ", deliveredTime='" + deliveredTime + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress_no='" + cusAddress_no + '\'' +
                ", cusAddress_street='" + cusAddress_street + '\'' +
                ", cusAddress_village='" + cusAddress_village + '\'' +
                ", cusAddress_city='" + cusAddress_city + '\'' +
                ", cusCNhome='" + cusCNhome + '\'' +
                ", cusCNMobile='" + cusCNMobile + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
