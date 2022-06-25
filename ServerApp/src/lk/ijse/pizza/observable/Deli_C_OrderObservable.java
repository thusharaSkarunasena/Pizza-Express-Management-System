package lk.ijse.pizza.observable;

import lk.ijse.pizza.observer.Deli_C_OrderObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Deli_C_OrderObservable {

    private ArrayList<Deli_C_OrderObserver> observers = new ArrayList<>();

    public void addDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) {
        observers.add(deliCOrderObserver);
    }

    public void removeDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) {
        observers.remove(deliCOrderObserver);
    }

    public void notifyObservers(String controller) throws Exception {
        for (Deli_C_OrderObserver observer : observers) {
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
