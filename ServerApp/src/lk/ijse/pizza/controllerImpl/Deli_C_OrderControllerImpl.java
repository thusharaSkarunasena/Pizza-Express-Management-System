package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.Deli_C_OrderController;
import lk.ijse.pizza.dbAccess.Deli_C_OrderDBAccess;
import lk.ijse.pizza.model.Deli_C_Order;
import lk.ijse.pizza.model.Deli_C_OrderItem;
import lk.ijse.pizza.observable.Deli_C_OrderObservable;
import lk.ijse.pizza.observer.Deli_C_OrderObserver;
import lk.ijse.pizza.reservation.Deli_C_OrderReserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Deli_C_OrderControllerImpl extends UnicastRemoteObject implements Deli_C_OrderController {

    private static Deli_C_OrderObservable deliCOrderObservable = new Deli_C_OrderObservable();
    private static Deli_C_OrderReserver deliCOrderReserver = new Deli_C_OrderReserver();
    private Deli_C_OrderDBAccess dbAccess = new Deli_C_OrderDBAccess();

    protected Deli_C_OrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Deli_C_Order> getAllOrders() throws Exception {
        return dbAccess.getAllOrders();
    }

    @Override
    public ArrayList<Deli_C_OrderItem> getAllOrderItems(String id) throws Exception {
        return dbAccess.getAllOrderItems(id);
    }

    @Override
    public boolean acceptOrder(String orderID, String deliID, String time, String status, String controller) throws Exception {
        boolean result = dbAccess.acceptOrder(orderID, deliID, time, status);
        if (result) {
            deliCOrderObservable.notifyObservers(controller);
            return result;
        }
        return result;

    }

    @Override
    public boolean doneOrder(String orderID, String time, String status) throws Exception {
        return dbAccess.doneOrder(orderID, time, status);
    }

    @Override
    public void addDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) throws Exception {
        deliCOrderObservable.addDeli_C_OrderObserver(deliCOrderObserver);
    }

    @Override
    public void removeDeli_C_OrderObserver(Deli_C_OrderObserver deliCOrderObserver) throws Exception {
        deliCOrderObservable.removeDeli_C_OrderObserver(deliCOrderObserver);
    }

    @Override
    public boolean reserveOrder(String id) throws Exception {
        return deliCOrderReserver.reserveOrder(id, this);
    }

    @Override
    public boolean releaseOrder(String id) throws Exception {
        return deliCOrderReserver.releaseOrder(id, this);
    }
}
