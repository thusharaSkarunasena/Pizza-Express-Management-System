package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Admin_ViewOrder;
import lk.ijse.pizza.model.Admin_ViewOrderDetail;

import java.rmi.Remote;
import java.util.ArrayList;

public interface Admin_ViewOrderController extends SuperController, Remote {

    public ArrayList<Admin_ViewOrder> getAllOrders() throws Exception;

    public ArrayList<Admin_ViewOrderDetail> getorderDetails(String orderID) throws Exception;

    public ArrayList<Admin_ViewOrder> searchOrders(String searchText) throws Exception;

}
