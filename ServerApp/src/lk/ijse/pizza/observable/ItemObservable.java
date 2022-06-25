package lk.ijse.pizza.observable;

import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.observer.ItemObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ItemObservable {
    private ArrayList<ItemObserver> observers = new ArrayList<>();

    public void addItemObsrver(ItemObserver itemObserver) {
        observers.add(itemObserver);
    }

    public void removeObserver(ItemObserver itemObserver) {
        observers.remove(itemObserver);
    }

    public void notifyObservers(String s, Item item, String status) throws Exception {
        for (ItemObserver observer : observers) {
            if (!observer.getController().equals(s)) {
                new Thread(() -> {
                    try {
                        observer.updateTable(item, status);
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
