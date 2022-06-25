package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Deli_D_Order;
import lk.ijse.pizza.model.Deli_D_OrderItem;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Deli_D_OrderDBAccess {

    public ArrayList<Deli_D_Order> getAllOrders(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllDeliveredOrders(?)", id);
        ArrayList<Deli_D_Order> dOrders = new ArrayList<>();
        while (rst.next()) {
            dOrders.add(new Deli_D_Order(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12)
            ));
        }
        return dOrders;
    }

    public ArrayList<Deli_D_OrderItem> getAllOrderItems(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllDeliveredOrderItems(?)", id);
        ArrayList<Deli_D_OrderItem> dOrderItems = new ArrayList<>();
        while (rst.next()) {
            dOrderItems.add(new Deli_D_OrderItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return dOrderItems;
    }

}
