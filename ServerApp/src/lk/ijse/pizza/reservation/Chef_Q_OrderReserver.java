package lk.ijse.pizza.reservation;

import lk.ijse.pizza.controller.Chef_Q_OrderController;

import java.util.HashMap;

public class Chef_Q_OrderReserver {

    private HashMap<String, Chef_Q_OrderController> reserveData = new HashMap<>();

    public boolean reserveOrder(String id, Chef_Q_OrderController chefQOrderController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == chefQOrderController) {
                return true;
            }
            return false;
        } else {
            reserveData.put(id, chefQOrderController);
            return true;
        }
    }

    public boolean releaseOrder(String id, Chef_Q_OrderController chefQOrderController) {
        if (reserveData.get(id) == chefQOrderController) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
