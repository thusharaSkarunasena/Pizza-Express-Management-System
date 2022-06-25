package lk.ijse.pizza.observer;

import lk.ijse.pizza.model.RecepOrder;

import java.rmi.Remote;

public interface RecepOrderObserver extends Remote {

    public void updateTable(RecepOrder recepOrder, String status) throws Exception;
    public String getController() throws Exception;

}
