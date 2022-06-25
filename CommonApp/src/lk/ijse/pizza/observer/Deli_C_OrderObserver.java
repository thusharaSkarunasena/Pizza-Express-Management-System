package lk.ijse.pizza.observer;

import java.rmi.Remote;

public interface Deli_C_OrderObserver extends Remote {

    public void updateTable() throws Exception;

    public String getController()throws Exception;

}
