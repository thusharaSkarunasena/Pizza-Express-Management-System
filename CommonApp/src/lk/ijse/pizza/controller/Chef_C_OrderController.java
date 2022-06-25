package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Chef_C_Order;
import lk.ijse.pizza.model.Chef_C_OrderItem;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Chef_C_OrderController extends SuperController, Remote {

    public ArrayList<Chef_C_Order> getAllOrders(String id) throws Exception;

    public ArrayList<Chef_C_OrderItem> getAllOrderItems(String id) throws Exception;

}
