package lk.ijse.pizza.reservation;


import lk.ijse.pizza.controller.ItemController;

import java.util.HashMap;

public class ItemReserver {

    private HashMap<String, ItemController> reserveData = new HashMap<>();

    public boolean reserveItem(String id, ItemController itemController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == itemController) {
                return true;
            }
            return false;
        } else {
            reserveData.put(id, itemController);
            return true;
        }
    }

    public boolean releaseItem(String id, ItemController itemController) {
        if (reserveData.get(id) == itemController) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
