package lk.ijse.pizza.observer;


import java.rmi.Remote;

public interface Chef_Q_OrderObserver extends Remote {

    public void updateTable() throws Exception;
    public String getController() throws Exception;

}
