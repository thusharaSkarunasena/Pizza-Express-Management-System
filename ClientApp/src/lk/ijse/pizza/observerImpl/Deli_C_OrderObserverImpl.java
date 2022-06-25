package lk.ijse.pizza.observerImpl;

import lk.ijse.pizza.controller.Deli_CookedOrdersController;
import lk.ijse.pizza.observer.Deli_C_OrderObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Deli_C_OrderObserverImpl extends UnicastRemoteObject implements Deli_C_OrderObserver {
    private Deli_CookedOrdersController deliCookedOrdersController;

    public Deli_C_OrderObserverImpl(Deli_CookedOrdersController deliCookedOrdersController) throws RemoteException {
        this.deliCookedOrdersController = deliCookedOrdersController;
    }

    @Override
    public void updateTable() throws Exception {
        System.out.println("observer impl override method is running...");
        deliCookedOrdersController.informTableUpdate();
    }

    @Override
    public String getController() throws Exception {
        return deliCookedOrdersController.toString();
    }

}
