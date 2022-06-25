package lk.ijse.pizza.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControllerFactory extends Remote {

    public SuperController getController(lk.ijse.pizza.controllerImpl.ControllerFactoryImpl.ContollerTypes contollerTypes) throws RemoteException;

}
