package lk.ijse.pizza.observable;

import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.observer.CustomerObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;


public class CustomerObservable {
    private ArrayList<CustomerObserver> observers = new ArrayList<>();

    public void addCustomerObserver(CustomerObserver customerObserver) {
        observers.add(customerObserver);
    }

    public void removeCustomerObserver(CustomerObserver customerObserver) {
        observers.remove(customerObserver);
    }

    public void notifyObservers(String s, Customer customer, String status) throws Exception {
        for (CustomerObserver observer : observers) {
            if (!observer.getController().equals(s)) {
                new Thread(() -> {
                    try {
                        observer.updateTable(customer, status);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }).start();
            }
        }
    }

}
