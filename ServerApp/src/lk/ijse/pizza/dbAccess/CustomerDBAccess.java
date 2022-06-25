package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDBAccess {

    public boolean saveCustomer(Customer customer) throws Exception {
        return CrudUtill.executeUpdate("call saveCustomer(?,?,?,?,?,?,?,?,?,?)", customer.getCustomerID(), customer.getName(), customer.getAddress_no(), customer.getAddress_street(), customer.getAddress_village(), customer.getAddress_city(), customer.getNic(), customer.getTel_home(), customer.getTel_mobile(), customer.getEmail());
    }


    public boolean updateCustomer(Customer customer) throws Exception {
        return CrudUtill.executeUpdate("call updateCustomer(?,?,?,?,?,?,?,?,?,?)", customer.getCustomerID(), customer.getName(), customer.getAddress_no(), customer.getAddress_street(), customer.getAddress_village(), customer.getAddress_city(), customer.getNic(), customer.getTel_home(), customer.getTel_mobile(), customer.getEmail());
    }


    public boolean deleteCustomer(String id) throws Exception {
        return CrudUtill.executeUpdate("call deleteCustomer(?)", id);
    }


    public Customer getCustomer(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getCustomer(?)", id);
        Customer customer = null;
        while (rst.next()) {
            customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10));
        }
        return customer;
    }


    public ArrayList<Customer> searchCustomer(String searchText) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call searchCustomer(?)", searchText);

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }
        return customers;
    }


    public ArrayList<Customer> getAllCustomers() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllCustomers()");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
        }
        return customers;
    }

    public String generatrCustomerID() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call generateCustomerID()");

        String lastID = null;
        String nextCustomerID;

        if (rst.next()) {
            lastID = rst.getString(1);

            String lastIDNumPart = lastID.substring(4, 8);

            Integer temp = Integer.parseInt(lastIDNumPart) + 1;

            String nextIDNumPart = temp.toString();

            for (int i = 0; i < 4 - temp.toString().length(); i++) {
                nextIDNumPart = "0" + nextIDNumPart;
            }

            nextCustomerID = "CUS-" + nextIDNumPart;
        } else {
            nextCustomerID = "CUS-0001";
        }

        return nextCustomerID;
    }
}
