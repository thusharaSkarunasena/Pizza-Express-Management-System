package lk.ijse.pizza.reservation;

import lk.ijse.pizza.controller.RecepOrderController;

import java.util.HashMap;

public class RecepOrderReserver {

    private HashMap<String, RecepOrderController> reserveData = new HashMap<>();

    public boolean reserveRecepOrder(String id, RecepOrderController recepOrderController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == recepOrderController) {
                return true;
            }
            return false;
        } else {
            reserveData.put(id, recepOrderController);
            return true;
        }
    }

    public boolean releaseRecepOrder(String id, RecepOrderController recepOrderController) {
        if (reserveData.get(id) == recepOrderController) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
