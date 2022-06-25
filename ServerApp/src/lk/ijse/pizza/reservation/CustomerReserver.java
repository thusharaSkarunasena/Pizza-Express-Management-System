package lk.ijse.pizza.reservation;

import lk.ijse.pizza.controller.CustomerController;

import java.util.HashMap;

public class CustomerReserver {

    private HashMap<String, CustomerController> reserveData = new HashMap<>();

    public boolean reserveCustomer(String id, CustomerController customerController) {
        if (reserveData.containsKey(id)) {
            if (reserveData.get(id) == customerController) {
                return true;
            }
            return false;
        } else {
            reserveData.put(id, customerController);
            return true;
        }
    }

    public boolean releaseCustomer(String id, CustomerController customerController) {
        if (reserveData.get(id) == customerController) {
            reserveData.remove(id);
            return true;
        }
        return false;
    }

}
