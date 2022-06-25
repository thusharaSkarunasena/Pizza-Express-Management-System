package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.connecter.ServerConnecter;
import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;
import lk.ijse.pizza.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInControllerAll implements Initializable {

    public static Employee loggedEmployee = null;
    @FXML
    private AnchorPane logInAnchPane;
    @FXML
    private JFXTextField userNameTF;
    @FXML
    private JFXPasswordField passwordPF;
    @FXML
    private JFXButton logInBtn;
    @FXML
    private Label warningLbl;
    private LogInController logInController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            logInController = (LogInController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.LOGINCONTROLLERALL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void userNameTF_onAction(ActionEvent actionEvent) {
        passwordPF.requestFocus();
    }

    @FXML
    public void passwordPF_onAction(ActionEvent actionEvent) {
        logInBtn.fire();
    }

    @FXML
    public void logInBtn_onAction(ActionEvent actionEvent) {

        String user = userNameTF.getText();
        String pass = passwordPF.getText();

        try {
            Employee employee = logInController.getEmployee(user, pass);

            if (employee == null) {
                userNameTF.setText("");
                passwordPF.setText("");
                userNameTF.requestFocus();
                warningLbl.setText("Username or Password is InCorrect!");
            } else {
                loggedEmployee = employee;

                switch (employee.getPosition()) {

                    case "Admin": {
                        loadMainDash("adminMainDash", "Pizza Express >>> Admin's Main DashBoard");
                    }
                    break;

                    case "Reception": {
                        loadMainDash("receptionMainDash", "Pizza Express >>> Receptions's Main DashBoard");
                    }
                    break;

                    case "Chef": {
                        loadMainDash("chefMainDash", "Pizza Express >>> Chef's Main DashBoard");
                    }
                    break;

                    case "Deliver": {
                        loadMainDash("deliverMainDash", "Pizza Express >>> Deliver's Main DashBoard");
                    }
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadMainDash(String fxmlFileName, String title) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/" + fxmlFileName + ".fxml")));
            Stage stage = (Stage) this.logInAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle(title);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            FadeTransition trans = new FadeTransition(Duration.millis(1000), temp.getRoot());
            trans.setFromValue(0.02);
            trans.setToValue(1);
            trans.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
