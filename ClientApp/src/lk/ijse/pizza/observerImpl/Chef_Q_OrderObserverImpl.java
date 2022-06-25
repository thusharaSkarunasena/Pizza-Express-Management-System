package lk.ijse.pizza.observerImpl;

import lk.ijse.pizza.controller.Chef_QueuedOrdersController;
import lk.ijse.pizza.observer.Chef_Q_OrderObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Chef_Q_OrderObserverImpl extends UnicastRemoteObject implements Chef_Q_OrderObserver {
    private Chef_QueuedOrdersController chefQOrderController;

    public Chef_Q_OrderObserverImpl(Chef_QueuedOrdersController chefQOrderController) throws RemoteException {
        this.chefQOrderController = chefQOrderController;
    }

    @Override
    public void updateTable() throws Exception {
        chefQOrderController.informTableUpdate();
    }

    @Override
    public String getController() throws Exception {
        return chefQOrderController.toString();
    }
}
