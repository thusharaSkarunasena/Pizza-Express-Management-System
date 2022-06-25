package lk.ijse.pizza.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private String employeeID;
    private String name;
    private String position;
    private String nic;
    private String address_no;
    private String address_street;
    private String address_village;
    private String address_city;
    private String tel_home;
    private String tel_mobile;
    private String other_detail;

    public Employee() {
    }

    public Employee(String employeeID, String name, String position, String nic, String address_no, String address_street, String address_village, String address_city, String tel_home, String tel_mobile, String other_detail) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.nic = nic;
        this.address_no = address_no;
        this.address_street = address_street;
        this.address_village = address_village;
        this.address_city = address_city;
        this.tel_home = tel_home;
        this.tel_mobile = tel_mobile;
        this.other_detail = other_detail;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getOther_detail() {
        return other_detail;
    }

    public void setOther_detail(String other_detail) {
        this.other_detail = other_detail;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", nic='" + nic + '\'' +
                ", address_no='" + address_no + '\'' +
                ", address_street='" + address_street + '\'' +
                ", address_village='" + address_village + '\'' +
                ", address_city='" + address_city + '\'' +
                ", tel_home='" + tel_home + '\'' +
                ", tel_mobile='" + tel_mobile + '\'' +
                ", other_detail='" + other_detail + '\'' +
                '}';
    }
}
