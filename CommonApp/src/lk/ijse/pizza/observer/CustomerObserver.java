package lk.ijse.pizza.observer;

import lk.ijse.pizza.model.Customer;

import java.rmi.Remote;

public interface CustomerObserver extends Remote {
    public void updateTable(Customer customer, String status) throws Exception;
    public String getController() throws Exception;
}
