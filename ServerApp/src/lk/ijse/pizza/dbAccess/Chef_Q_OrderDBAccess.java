package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Chef_Q_Order;
import lk.ijse.pizza.model.Chef_Q_OrderItem;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Chef_Q_OrderDBAccess {

    public ArrayList<Chef_Q_Order> getAll() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllQueuedOrders()");
        ArrayList<Chef_Q_Order> chefQOrders = new ArrayList<>();
        while (rst.next()) {
            chefQOrders.add(new Chef_Q_Order(
                    rst.getString(1),
                    rst.getString(7),
                    rst.getString(15)
            ));
        }
        return chefQOrders;
    }

    public ArrayList<Chef_Q_OrderItem> getOrderDetail(String id) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getChefOrderDetail(?)", id);
        ArrayList<Chef_Q_OrderItem> chefQOrderItems = new ArrayList<>();
        while (rst.next()) {
            chefQOrderItems.add(new Chef_Q_OrderItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return chefQOrderItems;
    }

    public boolean acceptOrder(String orderID, String time, String chefID, String status) throws Exception {
        return CrudUtill.executeUpdate("call chefAcceptOrder(?,?,?,?)", orderID, time, chefID, status);
    }

    public boolean doneOrder(String orderID, String time, String status) throws Exception {
        return CrudUtill.executeUpdate("call chefDoneOrder(?,?,?)", orderID, time, status);
    }

}
