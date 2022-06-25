package lk.ijse.pizza.main;

import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartUpServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(2018);
            System.out.println("Pizza Express Server is Starting...");
            registry.rebind("PizzaExpressServer", new ControllerFactoryImpl());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
