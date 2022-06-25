package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.model.RecepOrderItem;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;

public class RecepOrderDBAccess {

    public ArrayList<RecepOrder> getAllOrders() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getAllOrders()");
        ArrayList<RecepOrder> recepOrders = new ArrayList<>();
        while (rst.next()) {
            recepOrders.add(new RecepOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(8),
                    rst.getString(15)
            ));
        }
        return recepOrders;
    }

    public RecepOrder getOrder(String orderID) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getOrder(?)", orderID);
        RecepOrder recepOrder = null;
        while (rst.next()) {
            recepOrder = new RecepOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(8),
                    rst.getString(15)
            );
        }
        return recepOrder;
    }

    public ArrayList<RecepOrderItem> getItems(String orderID) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getOrderDetail(?)", orderID);
        ArrayList<RecepOrderItem> recepOrderItems = new ArrayList<>();
        while (rst.next()) {
            recepOrderItems.add(new RecepOrderItem(
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
        return recepOrderItems;
    }

    public boolean saveOrder(RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception {
        Boolean b1 = CrudUtill.executeUpdate("call saveOrder(?,?,?,?,?,?,?,?,?)", recepOrder.getOrderID(), recepOrder.getOrderDate(), recepOrder.getCustomerID(), recepOrder.getTotalAmount(), recepOrder.getDiscount(), recepOrder.getNetAmount(), LocalTime.now().toString(), recepOrder.getEmployeeID(), recepOrder.getStatus());
        for (RecepOrderItem recepOrderItem : recepOrderItems) {
            CrudUtill.executeUpdate("call saveOrderDetail(?,?,?,?)", recepOrder.getOrderID(), recepOrderItem.getItemCode(), recepOrderItem.getQty(), recepOrderItem.getTotal());
        }
        return b1;
    }

//    public boolean updateOrder(RecepOrder recepOrder, ArrayList<RecepOrderItem> recepOrderItems) throws Exception {
//        Boolean b1 = CrudUtill.executeUpdate("call update(?,?,?,?,?,?,?,?,?)", recepOrder.getOrderID(), recepOrder.getOrderDate(), recepOrder.getCustomerID(), recepOrder.getTotalAmount(), recepOrder.getDiscount(), recepOrder.getNetAmount(), LocalTime.now().toString(), recepOrder.getEmployeeID(), recepOrder.getStatus());
//        CrudUtill.executeUpdate("call deleteItems(?,?)",recepOrder.getOrderID(), recepOrder.getOrderDate());
//        for (RecepOrderItem recepOrderItem : recepOrderItems) {
//            CrudUtill.executeUpdate("call saveOrderDetail(?,?,?,?)", recepOrder.getOrderID(), recepOrderItem.getItemCode(), recepOrderItem.getQty(), recepOrderItem.getTotal());
//        }
//        return b1;
//    }

    public boolean deleteOrder(String orderID) throws Exception {
        return CrudUtill.executeUpdate("call deleteOrder(?)", orderID);
    }

    public ArrayList<RecepOrder> searchOrder(String searchText) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call searchOrder(?)", searchText);
        ArrayList<RecepOrder> recepOrders = new ArrayList<>();
        while (rst.next()) {
            recepOrders.add(new RecepOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(8),
                    rst.getString(15)
            ));
        }
        return recepOrders;
    }

    public String generateOrderID() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call generateOrderID()");

        String lastOrderID = null;
        String nextOrderID;

        if (rst.next()) {

            lastOrderID = rst.getString(1);

            String lastIDNumPart = lastOrderID.substring(4, 12);

            Integer temp = Integer.parseInt(lastIDNumPart) + 1;

            String nextIDNumPart = temp.toString();

            for (int i = 0; i < 8 - temp.toString().length(); i++) {
                nextIDNumPart = "0" + nextIDNumPart;
            }

            nextOrderID = "ORD-" + nextIDNumPart;
        } else {
            nextOrderID = "ORD-00000001";
        }

        return nextOrderID;

    }

}
