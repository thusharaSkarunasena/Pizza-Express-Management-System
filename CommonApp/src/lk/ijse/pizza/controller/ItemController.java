package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.observer.ItemObserver;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ItemController extends SuperController, Remote {

    public boolean saveItem(String s, Item item) throws Exception;

    public boolean updateItem(String s, Item item) throws Exception;

    public boolean deleteItem(String s, String id) throws Exception;

    public Item getItem(String id) throws Exception;

    public ArrayList<Item> searchItem(String searchText) throws Exception;

    public ArrayList<Item> getAllItems() throws Exception;

    public String generatrItemCode() throws Exception;

    public void addItemObserver(ItemObserver itemObserver) throws Exception;

    public void removeItemObserver(ItemObserver itemObserver) throws Exception;

    public boolean reserveItem(String id) throws Exception;

    public boolean releaseItem(String id) throws Exception;

}
