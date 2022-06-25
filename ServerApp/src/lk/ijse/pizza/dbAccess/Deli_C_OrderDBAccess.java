package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Deli_C_Order;
import lk.ijse.pizza.model.Deli_C_OrderItem;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Deli_C_OrderDBAccess {

    public ArrayList<Deli_C_Order> getAllOrders() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllCookedOrders()");
        ArrayList<Deli_C_Order> deliCOrders = new ArrayList<>();
        while (rst.next()) {
            deliCOrders.add(new Deli_C_Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return deliCOrders;
    }

    public ArrayList<Deli_C_OrderItem> getAllOrderItems(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getDeliOrderDetail(?)", id);
        ArrayList<Deli_C_OrderItem> deliCOrderItems = new ArrayList<>();
        while (rst.next()) {
            deliCOrderItems.add(new Deli_C_OrderItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return deliCOrderItems;
    }

    public boolean acceptOrder(String orderID, String deliID, String time, String status) throws Exception {
        return CrudUtill.executeUpdate("call deliAcceptOrder(?,?,?,?)", orderID, deliID, time, status);
    }

    public boolean doneOrder(String orderID, String time, String status) throws Exception {
        return CrudUtill.executeUpdate("call deliDoneOrder(?,?,?)", orderID, time, status);
    }

}
