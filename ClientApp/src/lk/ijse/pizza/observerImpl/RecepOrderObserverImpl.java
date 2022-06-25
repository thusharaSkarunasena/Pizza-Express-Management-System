package lk.ijse.pizza.observerImpl;

import lk.ijse.pizza.controller.Recep_OrderManagementController;
import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.observer.RecepOrderObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RecepOrderObserverImpl extends UnicastRemoteObject implements RecepOrderObserver {
    private Recep_OrderManagementController recep_orderManagementController;

    public RecepOrderObserverImpl(Recep_OrderManagementController recep_orderManagementController) throws RemoteException {
        this.recep_orderManagementController = recep_orderManagementController;
    }

    @Override
    public void updateTable(RecepOrder recepOrder, String status) throws Exception {
        recep_orderManagementController.informTableUpdate(recepOrder, status);
    }

    @Override
    public String getController() throws Exception {
        return this.recep_orderManagementController.toString();
    }
}
