package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.Chef_Q_OrderController;
import lk.ijse.pizza.dbAccess.Chef_Q_OrderDBAccess;
import lk.ijse.pizza.model.Chef_Q_Order;
import lk.ijse.pizza.model.Chef_Q_OrderItem;
import lk.ijse.pizza.observable.Chef_Q_OrderObservable;
import lk.ijse.pizza.observer.Chef_Q_OrderObserver;
import lk.ijse.pizza.reservation.Chef_Q_OrderReserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Chef_Q_OrderControllerImpl extends UnicastRemoteObject implements Chef_Q_OrderController {

    public static Chef_Q_OrderReserver chefQOrderReserver = new Chef_Q_OrderReserver();
    private static Chef_Q_OrderObservable chefQOrderObservable = new Chef_Q_OrderObservable();
    private Chef_Q_OrderDBAccess dbAccess = new Chef_Q_OrderDBAccess();

    protected Chef_Q_OrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Chef_Q_Order> getAll() throws Exception {
        return dbAccess.getAll();
    }

    @Override
    public ArrayList<Chef_Q_OrderItem> getOrderDetail(String id) throws Exception {
        return dbAccess.getOrderDetail(id);
    }

    @Override
    public boolean acceptOrder(String orderID, String time, String chefID, String status, String controller) throws Exception {
        boolean result = dbAccess.acceptOrder(orderID, time, chefID, status);
        if (result) {
            chefQOrderObservable.notifyObservers(controller);
            return result;
        }
        return result;
    }

    @Override
    public boolean doneOrder(String orderID, String time, String status) throws Exception {
        return dbAccess.doneOrder(orderID, time, status);
    }

    @Override
    public void addChef_Q_OrderObserver(Chef_Q_OrderObserver chefQOrderObserver) throws Exception {
        chefQOrderObservable.addCustomerObserver(chefQOrderObserver);
    }

    @Override
    public void removeChef_Q_OrderObserver(Chef_Q_OrderObserver chefQOrderObserver) throws Exception {
        chefQOrderObservable.removeCustomerObserver(chefQOrderObserver);
    }

    @Override
    public boolean reserveOrder(String id) throws Exception {
        return chefQOrderReserver.reserveOrder(id, this);
    }

    @Override
    public boolean releaseOrder(String id) throws Exception {
        return chefQOrderReserver.releaseOrder(id, this);
    }

}
