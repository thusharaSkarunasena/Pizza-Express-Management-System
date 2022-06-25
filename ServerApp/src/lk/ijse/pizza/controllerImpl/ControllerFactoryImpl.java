package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.ControllerFactory;
import lk.ijse.pizza.controller.SuperController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControllerFactoryImpl extends UnicastRemoteObject implements ControllerFactory {


    public ControllerFactoryImpl() throws RemoteException {
    }

    @Override
    public SuperController getController(ContollerTypes contollerTypes) throws RemoteException {
        switch (contollerTypes) {
            case CUSTOMERCONTROLLER:
                return new CustomerControllerImpl();
            case LOGINCONTROLLERALL:
                return new LogInControllerImpl();
            case ITEMCONTROLLER:
                return new ItemControllerImpl();
            case RECEPORDERCONTROLLER:
                return new RecepOrderControllerImpl();
            case CHEFQORDERCONTROLLER:
                return new Chef_Q_OrderControllerImpl();
            case CHEFCORDERCONTROLLER:
                return new Chef_C_OrderControllerImpl();
            case DELICORDERCONTROLLER:
                return new Deli_C_OrderControllerImpl();
            case DELIDORDERCONTROLLER:
                return new Deli_D_OrderControllerImpl();
            case ADMINVIEWORDERCONTROLLER:
                return new Admin_ViewOrderControllerImpl();
            default:
                return null;
        }
    }

    public enum ContollerTypes {
        CUSTOMERCONTROLLER, LOGINCONTROLLERALL, ITEMCONTROLLER, RECEPORDERCONTROLLER, CHEFQORDERCONTROLLER, CHEFCORDERCONTROLLER, DELICORDERCONTROLLER, DELIDORDERCONTROLLER, ADMINVIEWORDERCONTROLLER;
    }


}
