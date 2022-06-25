package lk.ijse.pizza.observer;

import lk.ijse.pizza.model.Item;

import java.rmi.Remote;

public interface ItemObserver extends Remote {

    public void updateTable(Item item, String status) throws Exception;
    public String getController() throws Exception;

}
