package lk.ijse.pizza.controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeliverMainDashController implements Initializable {

    @FXML
    public Label dateLbl;
    @FXML
    public Label timeLbl;
    @FXML
    private AnchorPane deliverAnchPane;
    @FXML
    private HBox profileHBox;
    @FXML
    private HBox logOutHBox;
    @FXML
    private HBox exitHBox;
    @FXML
    private HBox viewMoreHBOX;
    @FXML
    private HBox connectHBox;
    @FXML
    private HBox settingsHBox;
    @FXML
    private HBox aboutHBox;
    @FXML
    private HBox helpHBox;
    @FXML
    private ImageView cookedOrdersIcon;
    @FXML
    private ImageView deliveredOrdersIcon;
    @FXML
    private Label selectionTitleLbl;
    @FXML
    private Label selectionDescriptionLbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        selectionTitleLbl.setText("Welcome to Deliverer's Main Dash.");
        selectionDescriptionLbl.setText("Please select one of above main operations to proceed...");

        Timeline dateNtime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy  E");
            dateLbl.setText(LocalDate.now().format(formatter));
            timeLbl.setText(LocalTime.now().toString().substring(0, 8));
        }));

        dateNtime.setCycleCount(Animation.INDEFINITE);
        dateNtime.play();

    }

    @FXML
    public void profileHBox_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void logOutHBox_onMouseClicked(MouseEvent mouseEvent) {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Logout?");
        logoutAlert.setHeaderText(null);
        logoutAlert.setContentText(" Are You Sure to Logout?");
        Optional<ButtonType> action = logoutAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/logInAll.fxml")));
                Stage stage = (Stage) this.deliverAnchPane.getScene().getWindow();
                stage.setScene(temp);
                stage.centerOnScreen();


                TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
                trans.setFromY(-temp.getHeight());
                trans.setToY(0);
                trans.play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void exitHBox_onMouseClicked(MouseEvent mouseEvent) {
        Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
        logoutAlert.setTitle("Exit?");
        logoutAlert.setHeaderText(null);
        logoutAlert.setContentText(" Are You Sure to Exit?");
        Optional<ButtonType> action = logoutAlert.showAndWait();

        if (action.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    public void viewMoreHBOX_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void connectHBox_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void settingsHBox_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void aboutHBox_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    public void helpHBox_onMouseClicked(MouseEvent mouseEvent) {
    }

    @FXML
    void deliveredDeliversIcon_onMouseClicked(MouseEvent event) {
        loadChefDashBoardOptions("deli_deliveredOrders", "Delivered Orders");
    }

    @FXML
    void cookedOrdersIcon_onMouseClicked(MouseEvent event) {
        loadChefDashBoardOptions("deli_cookedOrders", "Cooked Orders");
    }

    @FXML
    void deliverMainDashIcon_onMouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "cookedOrdersIcon":
                    selectionTitleLbl.setText("Cooked Orders");
                    selectionDescriptionLbl.setText("Click to view and select a order from Cooked orders list.");
                    break;
                case "deliveredOrdersIcon":
                    selectionTitleLbl.setText("Delivered Orders");
                    selectionDescriptionLbl.setText("Click to see what you did past.");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.rgb(0, 153, 204));
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    @FXML
    void deliverMainDashIcon_onMouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
            selectionTitleLbl.setText("Welcome to Deliverer's Main Dash.");
            selectionDescriptionLbl.setText("Please select one of above main operations to proceed...");
        }
    }

    public void loadChefDashBoardOptions(String fxmlName, String title) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/" + fxmlName + ".fxml"));
            Scene temp = new Scene(root);
            Stage stage = (Stage) this.deliverAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("Pizza Express >>> Deliverer's Main DashBoard >>> " + title);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(+temp.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
