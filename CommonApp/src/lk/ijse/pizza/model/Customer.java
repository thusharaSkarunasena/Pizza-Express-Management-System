package lk.ijse.pizza.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private String customerID;
    private String name;
    private String address_no;
    private String address_street;
    private String address_village;
    private String address_city;
    private String nic;
    private String tel_home;
    private String tel_mobile;
    private String email;

    public Customer() {
    }

    public Customer(String customerID, String name, String address_no, String address_street, String address_village, String address_city, String nic, String tel_home, String tel_mobile, String email) {
        this.customerID = customerID;
        this.name = name;
        this.address_no = address_no;
        this.address_street = address_street;
        this.address_village = address_village;
        this.address_city = address_city;
        this.nic = nic;
        this.tel_home = tel_home;
        this.tel_mobile = tel_mobile;
        this.email = email;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_no() {
        return address_no;
    }

    public void setAddress_no(String address_no) {
        this.address_no = address_no;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getAddress_village() {
        return address_village;
    }

    public void setAddress_village(String address_village) {
        this.address_village = address_village;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTel_home() {
        return tel_home;
    }

    public void setTel_home(String tel_home) {
        this.tel_home = tel_home;
    }

    public String getTel_mobile() {
        return tel_mobile;
    }

    public void setTel_mobile(String tel_mobile) {
        this.tel_mobile = tel_mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", address_no='" + address_no + '\'' +
                ", address_street='" + address_street + '\'' +
                ", address_village='" + address_village + '\'' +
                ", address_city='" + address_city + '\'' +
                ", nic='" + nic + '\'' +
                ", tel_home='" + tel_home + '\'' +
                ", tel_mobile='" + tel_mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
