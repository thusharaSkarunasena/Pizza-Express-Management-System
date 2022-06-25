package lk.ijse.pizza.controller;

import lk.ijse.pizza.model.Employee;

import java.rmi.Remote;

public interface LogInController extends SuperController, Remote {

    public Employee getEmployee(String username, String password) throws Exception;

}
