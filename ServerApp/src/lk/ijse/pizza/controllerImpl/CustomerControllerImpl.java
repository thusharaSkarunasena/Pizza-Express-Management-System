package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.CustomerController;
import lk.ijse.pizza.dbAccess.CustomerDBAccess;
import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.observable.CustomerObservable;
import lk.ijse.pizza.observer.CustomerObserver;
import lk.ijse.pizza.reservation.CustomerReserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CustomerControllerImpl extends UnicastRemoteObject implements CustomerController {

    private static CustomerObservable customerObservable = new CustomerObservable();
    private static CustomerReserver customerReserver = new CustomerReserver();
    private CustomerDBAccess dbAccess = new CustomerDBAccess();

    public CustomerControllerImpl() throws RemoteException {
    }

    @Override
    public boolean saveCustomer(String s, Customer customer) throws Exception {
        boolean result = dbAccess.saveCustomer(customer);
        if (result) {
            customerObservable.notifyObservers(s, customer, "saved");
            return result;
        }
        return result;
    }

    @Override
    public boolean updateCustomer(String s, Customer customer) throws Exception {
        boolean result = dbAccess.updateCustomer(customer);
        if (result) {
            customerObservable.notifyObservers(s, customer, "updated");
            return result;
        }
        return result;
    }

    @Override
    public boolean deleteCustomer(String s, String id) throws Exception {
        Customer customer = dbAccess.getCustomer(id);
        boolean result = dbAccess.deleteCustomer(id);
        if (result) {
            customerObservable.notifyObservers(s, customer, "deleted");
            return result;
        }
        return result;
    }

    @Override
    public Customer getCustomer(String id) throws Exception {
        return dbAccess.getCustomer(id);
    }

    @Override
    public ArrayList<Customer> searchCustomer(String searchText) throws Exception {
        return dbAccess.searchCustomer(searchText);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws Exception {
        return dbAccess.getAllCustomers();
    }

    @Override
    public String generatrCustomerID() throws Exception {
        return dbAccess.generatrCustomerID();
    }

    @Override
    public void addCustomerObserver(CustomerObserver customerObserver) throws Exception {
        customerObservable.addCustomerObserver(customerObserver);
    }

    @Override
    public void removeCustomerObserver(CustomerObserver customerObserver) throws Exception {
        customerObservable.removeCustomerObserver(customerObserver);
    }

    @Override
    public boolean reserveCustomer(String id) throws Exception {
        return customerReserver.reserveCustomer(id, this);
    }

    @Override
    public boolean releaseCustomer(String id) throws Exception {
        return customerReserver.releaseCustomer(id, this);
    }
}
