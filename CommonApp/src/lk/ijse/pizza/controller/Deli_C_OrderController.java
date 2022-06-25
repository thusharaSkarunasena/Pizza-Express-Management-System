package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Deli_C_Order;
import lk.ijse.pizza.model.Deli_C_OrderItem;
import lk.ijse.pizza.observer.Deli_C_OrderObserver;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Deli_C_OrderController extends SuperController, Remote {

    public ArrayList<Deli_C_Order> getAllOrders() throws Exception;

    public ArrayList<Deli_C_OrderItem> getAllOrderItems(String id) throws Exception;

    public boolean acceptOrder(String orderID, String deliID, String time, String status, String controller) throws Exception;

    public boolean doneOrder(String orderID, String time, String status) throws Exception;

    public void addDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) throws Exception;

    public void removeDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) throws Exception;

    public boolean reserveOrder(String id) throws Exception;

    public boolean releaseOrder(String id) throws Exception;


}
