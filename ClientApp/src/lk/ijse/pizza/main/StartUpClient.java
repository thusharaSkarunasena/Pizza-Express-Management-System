package lk.ijse.pizza.main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.controller.*;

import java.util.Optional;

public class StartUpClient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/loading.fxml"));
        Scene temp = new Scene(root);
        primaryStage.setScene(temp);
        primaryStage.setTitle("Pizza Express >>> Wel Come!");
        primaryStage.getIcons().add(new Image("lk/ijse/pizza/asset/myIcon.png"));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event ->  {
            event.consume();
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setTitle("Exit?");
            logoutAlert.setHeaderText(null);
            logoutAlert.setContentText(" Are You Sure to Exit?");
            Optional<ButtonType> action = logoutAlert.showAndWait();

            if (action.get() == ButtonType.OK) {
                Recep_CustomerManagementController.onClose();
                Recep_ItemManagementController.onClose();
                Recep_OrderManagementController.onClose();
                Chef_QueuedOrdersController.onClose();
                Deli_CookedOrdersController.onClose();
                System.exit(0);
            }
        });

        FadeTransition trans = new FadeTransition(Duration.millis(500), root);
        trans.setFromValue(0.02);
        trans.setToValue(1);
        trans.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
