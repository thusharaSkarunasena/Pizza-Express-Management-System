package lk.ijse.pizza.observable;

import lk.ijse.pizza.observer.Chef_Q_OrderObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Chef_Q_OrderObservable {

    private ArrayList<Chef_Q_OrderObserver> observers = new ArrayList<>();

    public void addCustomerObserver(Chef_Q_OrderObserver chefQOrderObserver) {
        observers.add(chefQOrderObserver);
    }

    public void removeCustomerObserver(Chef_Q_OrderObserver chefQOrderObserver) {
        observers.remove(chefQOrderObserver);
    }

    public void notifyObservers(String controller) throws Exception {
        for (Chef_Q_OrderObserver observer : observers) {
            if (!observer.getController().equals(controller)) {
                new Thread(() -> {
                    try {
                        observer.updateTable();
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
