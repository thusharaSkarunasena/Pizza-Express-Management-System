package lk.ijse.pizza.observerImpl;

import lk.ijse.pizza.controller.Recep_CustomerManagementController;
import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.observer.CustomerObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CustomerObserverImpl extends UnicastRemoteObject implements CustomerObserver {
    private Recep_CustomerManagementController recep_customerManagementController;

    public CustomerObserverImpl(Recep_CustomerManagementController recep_customerManagementController) throws RemoteException {
        this.recep_customerManagementController = recep_customerManagementController;
    }

    @Override
    public void updateTable(Customer customer, String status) throws Exception {
        recep_customerManagementController.informTableUpdate(customer, status);
    }

    @Override
    public String getController() throws Exception {
        return recep_customerManagementController.toString();
    }

}
