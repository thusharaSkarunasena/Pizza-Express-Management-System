package lk.ijse.pizza.observable;

import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.observer.RecepOrderObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RecepOrderObservable {
    private ArrayList<RecepOrderObserver> observers = new ArrayList<>();

    public void addItemObsrver(RecepOrderObserver recepOrderObserver) {
        observers.add(recepOrderObserver);
    }

    public void removeObserver(RecepOrderObserver recepOrderObserver) {
        observers.remove(recepOrderObserver);
    }

    public void notifyObservers(String s, RecepOrder recepOrder, String status) throws Exception {
        for (RecepOrderObserver observer : observers) {
            if (!observer.getController().equals(s)) {
                new Thread(() -> {
                    try {
                        observer.updateTable(recepOrder, status);
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
