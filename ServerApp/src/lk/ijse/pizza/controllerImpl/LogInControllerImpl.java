package lk.ijse.pizza.controllerImpl;

import lk.ijse.pizza.controller.LogInController;
import lk.ijse.pizza.dbAccess.LogInDBAccess;
import lk.ijse.pizza.model.Employee;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LogInControllerImpl extends UnicastRemoteObject implements LogInController {

    private LogInDBAccess dbAccess = new LogInDBAccess();

    public LogInControllerImpl() throws RemoteException {
    }

    @Override
    public Employee getEmployee(String username, String password) throws Exception {
        return dbAccess.getEmployee(username, password);
    }
}
