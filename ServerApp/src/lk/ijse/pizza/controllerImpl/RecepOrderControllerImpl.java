package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.RecepOrderController;
import lk.ijse.pizza.dbAccess.CustomerDBAccess;
import lk.ijse.pizza.dbAccess.ItemDBAccess;
import lk.ijse.pizza.dbAccess.RecepOrderDBAccess;
import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.model.RecepOrderItem;
import lk.ijse.pizza.observable.RecepOrderObservable;
import lk.ijse.pizza.observer.RecepOrderObserver;
import lk.ijse.pizza.reservation.RecepOrderReserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RecepOrderControllerImpl extends UnicastRemoteObject implements RecepOrderController {

    private static RecepOrderObservable recepOrderObservable = new RecepOrderObservable();
    private static RecepOrderReserver recepOrderReserver = new RecepOrderReserver();
    private CustomerDBAccess customerDBAccess = new CustomerDBAccess();
    private ItemDBAccess itemDBAccess = new ItemDBAccess();
    private RecepOrderDBAccess orderDBAccess = new RecepOrderDBAccess();

    protected RecepOrderControllerImpl() throws RemoteException {
    }

    @Override
    public ArrayList<Customer> getCustomers() throws Exception {
        return customerDBAccess.getAllCustomers();
    }

    @Override
    public Customer getCustomer(String id) throws Exception {
        return customerDBAccess.getCustomer(id);
    }

    @Override
    public ArrayList<Item> getItems() throws Exception {
        return itemDBAccess.getAllItems();
    }


    @Override
    public Item getItem(String code) throws Exception {
        return itemDBAccess.getItem(code);
    }

    @Override
    public ArrayList<RecepOrder> getAllOrders() throws Exception {
        return orderDBAccess.getAllOrders();
    }

    @Override
    public RecepOrder getOrder(String orderID) throws Exception {
        return orderDBAccess.getOrder(orderID);
    }

    @Override
    public ArrayList<RecepOrderItem> getItems(String orderID) throws Exception {
        return orderDBAccess.getItems(orderID);
    }

    @Override
    public boolean saveOrder(String s, RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception {
        Boolean result = orderDBAccess.saveOrder(recepOrder, recepOrderItems);
        if (result) {
            recepOrderObservable.notifyObservers(s, recepOrder, "Saved");
            return result;
        }
        return result;
    }

//    @Override
//    public boolean updateOrder(String s, RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception {
//        boolean result=orderDBAccess.updateOrder(recepOrder, recepOrderItems);
//        if (result){
//            recepOrderObservable.notifyObservers(s, recepOrder, "Updated");
//            return result;
//        }
//        return result;
//    }

    @Override
    public boolean deleteOrder(String s, String orderID) throws Exception {
        RecepOrder recepOrder = orderDBAccess.getOrder(orderID);
        boolean result = orderDBAccess.deleteOrder(orderID);
        if (result) {
            recepOrderObservable.notifyObservers(s, recepOrder, "Deleted");
            return result;
        }
        return result;
    }

    @Override
    public ArrayList<RecepOrder> searchOrder(String searchText) throws Exception {
        return orderDBAccess.searchOrder(searchText);
    }

    @Override
    public String generateOrderID() throws Exception {
        return orderDBAccess.generateOrderID();
    }

    @Override
    public void addItemObserver(RecepOrderObserver recepOrderObserver) throws Exception {
        recepOrderObservable.addItemObsrver(recepOrderObserver);
    }

    @Override
    public void removeItemObserver(RecepOrderObserver recepOrderObserver) throws Exception {
        recepOrderObservable.removeObserver(recepOrderObserver);
    }

    @Override
    public boolean reserveRecepOrder(String id) throws Exception {
        return recepOrderReserver.reserveRecepOrder(id, this);
    }

    @Override
    public boolean releaseRecepOrder(String id) throws Exception {
        return recepOrderReserver.releaseRecepOrder(id, this);
    }
}
