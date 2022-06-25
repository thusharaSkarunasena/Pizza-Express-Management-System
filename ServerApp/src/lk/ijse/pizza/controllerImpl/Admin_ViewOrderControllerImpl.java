package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.Admin_ViewOrderController;
import lk.ijse.pizza.dbAccess.Admin_ViewOrderDBAccess;
import lk.ijse.pizza.model.Admin_ViewOrder;
import lk.ijse.pizza.model.Admin_ViewOrderDetail;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Admin_ViewOrderControllerImpl extends UnicastRemoteObject implements Admin_ViewOrderController {

    private Admin_ViewOrderDBAccess dbAccess = new Admin_ViewOrderDBAccess();

    protected Admin_ViewOrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Admin_ViewOrder> getAllOrders() throws Exception {
        return dbAccess.getAllOrders();
    }

    @Override
    public ArrayList<Admin_ViewOrderDetail> getorderDetails(String orderID) throws Exception {
        return dbAccess.getorderDetails(orderID);
    }

    @Override
    public ArrayList<Admin_ViewOrder> searchOrders(String searchText) throws Exception {
        return dbAccess.searchOrders(searchText);
    }
}
