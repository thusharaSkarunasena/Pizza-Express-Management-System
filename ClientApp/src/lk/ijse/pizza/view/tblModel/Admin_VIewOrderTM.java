package lk.ijse.pizza.view.tblModel;

public class Admin_VIewOrderTM {

    private String orderID;
    private String date;
    private String customerID;
    private String total;
    private String discount;
    private String netAmount;
    private String status;

    public Admin_VIewOrderTM() {
    }

    public Admin_VIewOrderTM(String orderID, String date, String customerID, String total, String discount, String netAmount, String status) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.total = total;
        this.discount = discount;
        this.netAmount = netAmount;
        this.status = status;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
        return "Admin_VIewOrderTM{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", customerID='" + customerID + '\'' +
                ", total='" + total + '\'' +
                ", discount='" + discount + '\'' +
                ", netAmount='" + netAmount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
