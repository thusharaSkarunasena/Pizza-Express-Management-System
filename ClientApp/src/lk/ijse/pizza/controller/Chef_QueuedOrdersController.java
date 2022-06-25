package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.connecter.ServerConnecter;
import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;
import lk.ijse.pizza.model.Chef_Q_Order;
import lk.ijse.pizza.model.Chef_Q_OrderItem;
import lk.ijse.pizza.model.Employee;
import lk.ijse.pizza.observer.Chef_Q_OrderObserver;
import lk.ijse.pizza.observerImpl.Chef_Q_OrderObserverImpl;
import lk.ijse.pizza.view.tblModel.Chef_Q_OrderItemTM;
import lk.ijse.pizza.view.tblModel.Chef_Q_OrderTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Chef_QueuedOrdersController implements Initializable {

    private static Chef_Q_OrderController chefQOrderController;
    private static Chef_Q_OrderObserver chefQOrderObserver;
    private static Employee employee;
    String orderID = null;
    @FXML
    private AnchorPane chef_queuedOrdersAnchPane;
    @FXML
    private ImageView backToDashIcon;
    @FXML
    private JFXTextField orderIDTF;
    @FXML
    private TextField statusTF;
    @FXML
    private JFXButton acceptOrderBtn;
    @FXML
    private JFXButton doneBtn;
    @FXML
    private TextField startedTimeTF;
    @FXML
    private TextField currentTimeTF;
    @FXML
    private TextField queuedTimeTF;
    @FXML
    private TableView<Chef_Q_OrderTM> queuedOrdersTbl;
    @FXML
    private TableView<Chef_Q_OrderItemTM> selectedOrderTbl;
    private ObservableList<Chef_Q_OrderTM> chef_Q_orderTM;
    private ObservableList<Chef_Q_OrderItemTM> chef_Q_orderItemTM;

    public static void onClose() {
        if (chefQOrderObserver != null) {
            try {
                chefQOrderController.removeChef_Q_OrderObserver(chefQOrderObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        orderID = "";

        try {
            chefQOrderController = (Chef_Q_OrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.CHEFQORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            chefQOrderObserver = new Chef_Q_OrderObserverImpl(this);
            chefQOrderController.addChef_Q_OrderObserver(chefQOrderObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        employee = LogInControllerAll.loggedEmployee;

        queuedOrdersTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        queuedOrdersTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        queuedOrdersTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        queuedOrdersTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("queuedTime"));

        selectedOrderTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        selectedOrderTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nameNsize"));
        selectedOrderTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        Timeline time = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            currentTimeTF.setText(LocalTime.now().toString().substring(0, 8));
        }));

        time.setCycleCount(Animation.INDEFINITE);
        time.play();

        acceptOrderBtn.setDisable(true);
        doneBtn.setDisable(true);
        queuedOrdersTbl.requestFocus();

        loadQueuedOrders();

    }

    public void loadQueuedOrders() {
        try {
            ArrayList<Chef_Q_Order> chefQOrders = chefQOrderController.getAll();

            chef_Q_orderTM = queuedOrdersTbl.getItems();
            chef_Q_orderTM.removeAll(chef_Q_orderTM);

            for (Chef_Q_Order chefQOrder : chefQOrders) {
                chef_Q_orderTM.add(new Chef_Q_OrderTM(
                        chefQOrder.getOrderID(),
                        chefQOrder.getQueuedTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        queuedOrdersTbl.requestFocus();
    }

    @FXML
    public void acceptOrderBtn_onAction(ActionEvent event) {
        try {
            boolean result = chefQOrderController.acceptOrder(orderIDTF.getText(), currentTimeTF.getText(), employee.getEmployeeID(), "Cooking", this.toString());

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been reserved successfully for you", ButtonType.OK).show();
                loadQueuedOrders();
                statusTF.setText("Cooking...");
                startedTimeTF.setText(currentTimeTF.getText());
                acceptOrderBtn.setDisable(true);
                backToDashIcon.setDisable(true);
                queuedOrdersTbl.setDisable(true);
                doneBtn.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to reserve the order.", ButtonType.OK).show();
                loadQueuedOrders();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToDashIcon_onMouseClicked(MouseEvent event) {
        onClose();
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/chefMainDash.fxml")));
            Stage stage = (Stage) this.chef_queuedOrdersAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("Pizza Express >>> Chef's Main DashBoard ");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

            TranslateTransition trans = new TranslateTransition(Duration.millis(300), temp.getRoot());
            trans.setFromX(-temp.getHeight());
            trans.setToX(0);
            trans.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doneBtn_onAction(ActionEvent event) {
        try {
            boolean result = chefQOrderController.doneOrder(orderIDTF.getText(), currentTimeTF.getText(), "Cooked");

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been submitted successfully to database.", ButtonType.OK).show();
                loadQueuedOrders();
                orderIDTF.setText("");
                queuedTimeTF.setText("");
                statusTF.setText("");
                startedTimeTF.setText("");
                backToDashIcon.setDisable(false);
                queuedOrdersTbl.setDisable(false);
                acceptOrderBtn.setDisable(false);
                doneBtn.setDisable(true);
                chef_Q_orderItemTM = selectedOrderTbl.getItems();
                chef_Q_orderItemTM.removeAll(chef_Q_orderItemTM);
                queuedOrdersTbl.requestFocus();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to submit the order to database.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void queuedOrdersTbl_onMouseClicked(MouseEvent event) {
        Chef_Q_OrderTM chef_Q_orderTM = queuedOrdersTbl.getSelectionModel().getSelectedItem();

        if (chef_Q_orderTM != null) {
            try {
                if (chefQOrderController.reserveOrder(chef_Q_orderTM.getOrderID())) {
                    if (!this.orderID.equals(chef_Q_orderTM.getOrderID())) {
                        chefQOrderController.releaseOrder(this.orderID);
                        this.orderID = chef_Q_orderTM.getOrderID();
                    }
                    orderIDTF.setText(chef_Q_orderTM.getOrderID());
                    queuedTimeTF.setText(chef_Q_orderTM.getQueuedTime());
                    acceptOrderBtn.setDisable(false);

                    try {
                        ArrayList<Chef_Q_OrderItem> chefQOrderItems = chefQOrderController.getOrderDetail(chef_Q_orderTM.getOrderID());

                        chef_Q_orderItemTM = selectedOrderTbl.getItems();
                        chef_Q_orderItemTM.removeAll(chef_Q_orderItemTM);

                        for (Chef_Q_OrderItem chefQOrderItem : chefQOrderItems) {
                            chef_Q_orderItemTM.add(new Chef_Q_OrderItemTM(
                                    chefQOrderItem.getItemCode(),
                                    chefQOrderItem.getName() + " - " + chefQOrderItem.getSize(),
                                    Integer.toString(chefQOrderItem.getQty())
                            ));
                        }
                        statusTF.setText("Queue");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! \n\n The order you have selected, has been reserved by another \n client.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void informTableUpdate() {
        Platform.runLater(() -> {
            if (acceptOrderBtn.isDisable()) {
                loadQueuedOrders();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "The queued orders table has been updated because of another client. \n So we has been reloaded queued order table for you.", ButtonType.OK).show();
                loadQueuedOrders();
            }
        });
    }

}
