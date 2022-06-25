package lk.ijse.pizza.reservation;

import lk.ijse.pizza.controller.Deli_C_OrderController;

import java.util.HashMap;

public class Deli_C_OrderReserver {

    private HashMap<String, Deli_C_OrderController> reserveData = new HashMap<>();

    public boolean reserveOrder(String id, Deli_C_OrderController deliCOrderController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == deliCOrderController) {
                return true;
            }
            return false;
        } else {
            reserveData.put(id, deliCOrderController);
            return true;
        }
    }

    public boolean releaseOrder(String id, Deli_C_OrderController deliCOrderController) {
        if (reserveData.get(id) == deliCOrderController) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
