package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Chef_C_Order;
import lk.ijse.pizza.model.Chef_C_OrderItem;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Chef_C_OrderDBAccess {

    public ArrayList<Chef_C_Order> getAllOrders(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllChefCompletedOrders(?)", id);
        ArrayList<Chef_C_Order> chefCOrders = new ArrayList<>();
        while (rst.next()) {
            chefCOrders.add(new Chef_C_Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return chefCOrders;
    }

    public ArrayList<Chef_C_OrderItem> getAllOrderItems(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getChefCompletedOrderItem(?)", id);
        ArrayList<Chef_C_OrderItem> chefCOrderItems = new ArrayList<>();
        while (rst.next()) {
            chefCOrderItems.add(new Chef_C_OrderItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return chefCOrderItems;
    }

}
