package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Admin_ViewOrder;
import lk.ijse.pizza.model.Admin_ViewOrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Admin_ViewOrderDBAccess {

    public ArrayList<Admin_ViewOrder> getAllOrders() throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call adminGetAllOrders()");
        ArrayList<Admin_ViewOrder> adminViewOrders = new ArrayList<>();
        while (rst.next()) {
            adminViewOrders.add(new Admin_ViewOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14),
                    rst.getString(15)

            ));
        }
        return adminViewOrders;
    }

    public ArrayList<Admin_ViewOrderDetail> getorderDetails(String orderID) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call adminGetOrderDetail(?)", orderID);
        ArrayList<Admin_ViewOrderDetail> adminViewOrderDetails = new ArrayList<>();
        while (rst.next()) {
            adminViewOrderDetails.add(new Admin_ViewOrderDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return adminViewOrderDetails;
    }

    public ArrayList<Admin_ViewOrder> searchOrders(String searchText) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call adminSearchOrders(?)", searchText);
        ArrayList<Admin_ViewOrder> adminViewOrders = new ArrayList<>();
        while (rst.next()) {
            adminViewOrders.add(new Admin_ViewOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12),
                    rst.getString(13),
                    rst.getString(14),
                    rst.getString(15)

            ));
        }
        return adminViewOrders;
    }

}
