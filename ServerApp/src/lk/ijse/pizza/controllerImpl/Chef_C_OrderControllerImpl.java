package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.Chef_C_OrderController;
import lk.ijse.pizza.dbAccess.Chef_C_OrderDBAccess;
import lk.ijse.pizza.model.Chef_C_Order;
import lk.ijse.pizza.model.Chef_C_OrderItem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Chef_C_OrderControllerImpl extends UnicastRemoteObject implements Chef_C_OrderController {

    private Chef_C_OrderDBAccess dbAccess = new Chef_C_OrderDBAccess();

    protected Chef_C_OrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Chef_C_Order> getAllOrders(String id) throws Exception {
        return dbAccess.getAllOrders(id);
    }

    @Override
    public ArrayList<Chef_C_OrderItem> getAllOrderItems(String id) throws Exception {
        return dbAccess.getAllOrderItems(id);
    }
}
