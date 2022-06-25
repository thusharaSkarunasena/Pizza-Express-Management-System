package lk.ijse.pizza.connecter;

import lk.ijse.pizza.controller.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerConnecter {

    private ControllerFactory controllerFactory;
    private CustomerController customerController;
    private LogInController logInController;
    private ItemController itemController;
    private RecepOrderController recepOrderController;
    private Chef_Q_OrderController chefQOrderController;
    private Chef_C_OrderController chefCOrderController;
    private Deli_C_OrderController deliCOrderController;
    private Deli_D_OrderController deliDOrderController;
    private Admin_ViewOrderController adminViewOrderController;
    private static ServerConnecter serverConnecter;

    private ServerConnecter() throws NotBoundException, MalformedURLException, RemoteException {
        controllerFactory = (ControllerFactory) Naming.lookup("rmi://192.168.56.1:2018/PizzaExpressServer");
    }

    public static ServerConnecter getInstance() throws NotBoundException, MalformedURLException, RemoteException {
        if (serverConnecter == null) {
            serverConnecter = new ServerConnecter();
        }
        return serverConnecter;
    }

    public SuperController getController(lk.ijse.pizza.controllerImpl.ControllerFactoryImpl.ContollerTypes contollerTypes) throws Exception {
        switch (contollerTypes) {

            case CUSTOMERCONTROLLER:
                if (customerController == null) {
                    customerController = (CustomerController) controllerFactory.getController(contollerTypes);
                }
                return customerController;
            case LOGINCONTROLLERALL:
                if (logInController == null) {
                    logInController = (LogInController) controllerFactory.getController(contollerTypes);
                }
                return logInController;
            case ITEMCONTROLLER:
                if (itemController == null) {
                    itemController = (ItemController) controllerFactory.getController(contollerTypes);
                }
                return itemController;
            case RECEPORDERCONTROLLER:
                if (recepOrderController == null) {
                    recepOrderController = (RecepOrderController) controllerFactory.getController(contollerTypes);
                }
                return recepOrderController;
            case CHEFQORDERCONTROLLER:
                if (chefQOrderController == null) {
                    chefQOrderController = (Chef_Q_OrderController) controllerFactory.getController(contollerTypes);
                }
                return chefQOrderController;
            case CHEFCORDERCONTROLLER:
                if (chefCOrderController == null) {
                    chefCOrderController = (Chef_C_OrderController) controllerFactory.getController(contollerTypes);
                }
                return chefCOrderController;
            case DELICORDERCONTROLLER:
                if(deliCOrderController==null){
                    deliCOrderController= (Deli_C_OrderController) controllerFactory.getController(contollerTypes);
                }
                return deliCOrderController;
            case DELIDORDERCONTROLLER:
                if(deliDOrderController==null){
                    deliDOrderController= (Deli_D_OrderController) controllerFactory.getController(contollerTypes);
                }
                return deliDOrderController;
            case ADMINVIEWORDERCONTROLLER:
                if(adminViewOrderController==null){
                    adminViewOrderController= (Admin_ViewOrderController) controllerFactory.getController(contollerTypes);
                }
                return adminViewOrderController;
            default:
                return null;
        }

    }
}
