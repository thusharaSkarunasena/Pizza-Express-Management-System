package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Deli_D_Order;
import lk.ijse.pizza.model.Deli_D_OrderItem;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Deli_D_OrderController extends SuperController, Remote {

    public ArrayList<Deli_D_Order> getAllOrders(String id) throws Exception;

    public ArrayList<Deli_D_OrderItem> getAllOrderItems(String id) throws Exception;

}
