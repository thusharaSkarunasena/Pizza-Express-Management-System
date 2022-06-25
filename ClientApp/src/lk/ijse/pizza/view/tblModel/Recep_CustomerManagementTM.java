package lk.ijse.pizza.view.tblModel;

public class Recep_CustomerManagementTM {

    private String customerID;
    private String name;
    private String nic;
    private String address;

    public Recep_CustomerManagementTM() {
    }

    public Recep_CustomerManagementTM(String customerID, String name, String nic, String address) {
        this.customerID = customerID;
        this.name = name;
        this.nic = nic;
        this.address = address;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Recep_CustomerManagementTM{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
