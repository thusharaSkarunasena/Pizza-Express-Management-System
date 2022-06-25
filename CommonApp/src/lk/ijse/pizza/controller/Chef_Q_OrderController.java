package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Chef_Q_Order;
import lk.ijse.pizza.model.Chef_Q_OrderItem;
import lk.ijse.pizza.observer.Chef_Q_OrderObserver;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Chef_Q_OrderController extends SuperController, Remote {

    public ArrayList<Chef_Q_Order> getAll() throws Exception;

    public ArrayList<Chef_Q_OrderItem> getOrderDetail(String id) throws Exception;

    public boolean acceptOrder(String orderID, String time, String chefID, String status, String controller) throws Exception;

    public boolean doneOrder(String orderID, String time, String status) throws Exception;

    public void addChef_Q_OrderObserver(Chef_Q_OrderObserver chefQOrderObserver) throws Exception;

    public void removeChef_Q_OrderObserver(Chef_Q_OrderObserver chefQOrderObserver) throws Exception;

    public boolean reserveOrder(String id) throws Exception;

    public boolean releaseOrder(String id) throws Exception;

}
