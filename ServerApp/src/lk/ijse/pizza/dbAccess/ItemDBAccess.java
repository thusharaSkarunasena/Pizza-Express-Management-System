package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Item;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDBAccess {

    public boolean saveItem(Item item) throws Exception {
        return CrudUtill.executeUpdate("call saveItem(?,?,?,?,?,?)", item.getItemCode(), item.getName(), item.getDescription(), item.getSize(), item.getPrice(), item.getOther_detail());
    }

    public boolean updateItem(Item item) throws Exception {
        return CrudUtill.executeUpdate("call updateItem(?,?,?,?,?,?)", item.getItemCode(), item.getName(), item.getDescription(), item.getSize(), item.getPrice(), item.getOther_detail());
    }

    public boolean deleteItem(String id) throws Exception {
        return CrudUtill.executeUpdate("call deleteItem(?)", id);
    }

    public Item getItem(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getItem(?)", id);
        Item item = null;

        while (rst.next()) {
            item = new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            );
        }
        return item;
    }

    public ArrayList<Item> searchItem(String searchText) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call searchItem(?)", searchText);
        ArrayList<Item> items = new ArrayList<>();

        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            ));
        }
        return items;
    }

    public ArrayList<Item> getAllItems() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllItems()");
        ArrayList<Item> items = new ArrayList<>();

        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6)
            ));
        }
        return items;
    }

    public String generatrItemCode() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call generateItemCode()");

        String lastCode = null;
        String nextItemCode;

        if (rst.next()) {
            lastCode = rst.getString(1);

            String lastCodeNumPart = lastCode.substring(2, 6);

            Integer temp = Integer.parseInt(lastCodeNumPart) + 1;

            String nextCodeNumPart = temp.toString();

            for (int i = 0; i < 4 - temp.toString().length(); i++) {
                nextCodeNumPart = "0" + nextCodeNumPart;
            }

            nextItemCode = "I-" + nextCodeNumPart;
        } else {
            nextItemCode = "I-0001";
        }
        return nextItemCode;
    }

}
