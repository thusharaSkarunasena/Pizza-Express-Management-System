package lk.ijse.pizza.observerImpl;

import lk.ijse.pizza.controller.Recep_ItemManagementController;
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.observer.ItemObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ItemObserverImpl extends UnicastRemoteObject implements ItemObserver {
    private Recep_ItemManagementController recep_itemManagementController;

    public ItemObserverImpl(Recep_ItemManagementController recep_itemManagementController) throws RemoteException {
        this.recep_itemManagementController = recep_itemManagementController;
    }

    @Override
    public void updateTable(Item item, String status) throws Exception {
        recep_itemManagementController.informTableUpdate(item, status);
    }

    @Override
    public String getController() throws Exception {
        return recep_itemManagementController.toString();
    }
}
