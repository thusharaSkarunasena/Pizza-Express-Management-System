package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.model.RecepOrderItem;
import lk.ijse.pizza.observer.RecepOrderObserver;

import java.rmi.Remote;
import java.util.ArrayList;

public interface RecepOrderController extends SuperController, Remote {

    public ArrayList<Customer> getCustomers() throws Exception;

    public Customer getCustomer(String id) throws Exception;

    public ArrayList<Item> getItems() throws Exception;

    public Item getItem(String code) throws Exception;

    public ArrayList<RecepOrder> getAllOrders() throws Exception;

    public RecepOrder getOrder(String orderID) throws Exception;

    public ArrayList<RecepOrderItem> getItems(String orderID) throws Exception;


    public boolean saveOrder(String s, RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception;

    //    public boolean updateOrder(String s, RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception;
    public boolean deleteOrder(String s, String orderID) throws Exception;

    public ArrayList<RecepOrder> searchOrder(String searchText) throws Exception;

    public String generateOrderID() throws Exception;

    public void addItemObserver(RecepOrderObserver recepOrderObserver) throws Exception;

    public void removeItemObserver(RecepOrderObserver recepOrderObserver) throws Exception;

    public boolean reserveRecepOrder(String id) throws Exception;

    public boolean releaseRecepOrder(String id) throws Exception;

}
