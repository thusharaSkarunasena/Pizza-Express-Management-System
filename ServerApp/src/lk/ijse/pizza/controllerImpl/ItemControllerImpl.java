package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.ItemController;
import lk.ijse.pizza.dbAccess.ItemDBAccess;
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.observable.ItemObservable;
import lk.ijse.pizza.observer.ItemObserver;
import lk.ijse.pizza.reservation.ItemReserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ItemControllerImpl extends UnicastRemoteObject implements ItemController {

    private static ItemObservable itemObservable = new ItemObservable();
    private static ItemReserver itemReserver = new ItemReserver();
    private ItemDBAccess dbAccess = new ItemDBAccess();

    protected ItemControllerImpl() throws RemoteException {
    }

    @Override
    public boolean saveItem(String s, Item item) throws Exception {
        boolean result = dbAccess.saveItem(item);
        if (result) {
            itemObservable.notifyObservers(s, item, "saved");
            return result;
        }
        return result;
    }

    @Override
    public boolean updateItem(String s, Item item) throws Exception {
        boolean result = dbAccess.updateItem(item);
        if (result) {
            itemObservable.notifyObservers(s, item, "updated");
            return result;
        }
        return result;
    }

    @Override
    public boolean deleteItem(String s, String id) throws Exception {
        Item item = getItem(id);
        boolean result = dbAccess.deleteItem(id);
        if (result) {
            itemObservable.notifyObservers(s, item, "deleted");
            return result;
        }
        return result;
    }

    @Override
    public Item getItem(String id) throws Exception {
        return dbAccess.getItem(id);
    }

    @Override
    public ArrayList<Item> searchItem(String searchText) throws Exception {
        return dbAccess.searchItem(searchText);
    }

    @Override
    public ArrayList<Item> getAllItems() throws Exception {
        return dbAccess.getAllItems();
    }

    @Override
    public String generatrItemCode() throws Exception {
        return dbAccess.generatrItemCode();
    }

    @Override
    public void addItemObserver(ItemObserver itemObserver) throws Exception {
        itemObservable.addItemObsrver(itemObserver);
    }

    @Override
    public void removeItemObserver(ItemObserver itemObserver) throws Exception {
        itemObservable.removeObserver(itemObserver);
    }

    @Override
    public boolean reserveItem(String id) throws Exception {
        return itemReserver.reserveItem(id, this);
    }

    @Override
    public boolean releaseItem(String id) throws Exception {
        return itemReserver.releaseItem(id, this);
    }
}
