package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.observer.CustomerObserver;

import java.rmi.Remote;
import java.util.ArrayList;

public interface CustomerController extends SuperController, Remote {

    public boolean saveCustomer(String s, Customer customer) throws Exception;

    public boolean updateCustomer(String s, Customer customer) throws Exception;

    public boolean deleteCustomer(String s, String id) throws Exception;

    public Customer getCustomer(String id) throws Exception;

    public ArrayList<Customer> searchCustomer(String searchText) throws Exception;

    public ArrayList<Customer> getAllCustomers() throws Exception;

    public String generatrCustomerID() throws Exception;

    public void addCustomerObserver(CustomerObserver customerObserver) throws Exception;

    public void removeCustomerObserver(CustomerObserver customerObserver) throws Exception;

    public boolean reserveCustomer(String id) throws Exception;

    public boolean releaseCustomer(String id) throws Exception;

}
