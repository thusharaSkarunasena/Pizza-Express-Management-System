package lk.ijse.pizza.dbAccess;

import lk.ijse.pizza.db.CrudUtill;
import lk.ijse.pizza.model.Employee;

import java.sql.ResultSet;

public class LogInDBAccess {

    public Employee getEmployee(String username, String password) throws Exception {
        ResultSet rst = CrudUtill.executeQuery("call getLoggedEmployee(?,?)", username, password);

        Employee employee = null;

        while (rst.next()) {
            employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11)
            );
        }
        return employee;
    }

}
