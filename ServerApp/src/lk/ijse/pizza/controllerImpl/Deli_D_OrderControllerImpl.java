package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.Deli_D_OrderController;
import lk.ijse.pizza.dbAccess.Deli_D_OrderDBAccess;
import lk.ijse.pizza.model.Deli_D_Order;
import lk.ijse.pizza.model.Deli_D_OrderItem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Deli_D_OrderControllerImpl extends UnicastRemoteObject implements Deli_D_OrderController {

    private Deli_D_OrderDBAccess dbAccess = new Deli_D_OrderDBAccess();

    protected Deli_D_OrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Deli_D_Order> getAllOrders(String id) throws Exception {
        return dbAccess.getAllOrders(id);
    }

    @Override
    public ArrayList<Deli_D_OrderItem> getAllOrderItems(String id) throws Exception {
        return dbAccess.getAllOrderItems(id);
    }
}
