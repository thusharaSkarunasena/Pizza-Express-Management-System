package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import lk.ijse.pizza.model.Deli_C_Order;
import lk.ijse.pizza.model.Deli_C_OrderItem;
import lk.ijse.pizza.model.Employee;
import lk.ijse.pizza.observer.Deli_C_OrderObserver;
import lk.ijse.pizza.observerImpl.Deli_C_OrderObserverImpl;
import lk.ijse.pizza.view.tblModel.Deli_C_OrderItemTM;
import lk.ijse.pizza.view.tblModel.Deli_C_OrderTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Deli_CookedOrdersController implements Initializable {

    private static Deli_C_OrderController deliCOrderController;
    private static Deli_C_OrderObserver deliCOrderObserver;
    private static String orderID = null;
    @FXML
    private AnchorPane deli_cookedOrdersAnchPane;
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
    private TextField cookedTimeTF;
    @FXML
    private TextField currentTimeTF;
    @FXML
    private TextField startedTimeTF;
    @FXML
    private TableView<Deli_C_OrderItemTM> selectedOrderTbl;
    @FXML
    private JFXTextField customerNameTF;
    @FXML
    private JFXTextArea customerAddressTA;
    @FXML
    private JFXTextField contactNumberHomeTF;
    @FXML
    private JFXTextField contactNumberMobileTF;
    @FXML
    private TableView<Deli_C_OrderTM> cookedOrdersTbl;
    private ObservableList<Deli_C_OrderTM> deliCOrderTM;
    private ObservableList<Deli_C_OrderItemTM> deliCOrderItemTM;
    private Employee employee;

    public static void onClose() {
        if (deliCOrderObserver != null) {
            try {
                deliCOrderController.removeDeli_C_OrderObserver(deliCOrderObserver);
                deliCOrderController.releaseOrder(orderID);
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
            deliCOrderController = (Deli_C_OrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.DELICORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            deliCOrderObserver = new Deli_C_OrderObserverImpl(this);
            deliCOrderController.addDeli_C_OrderObserver(deliCOrderObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        employee = LogInControllerAll.loggedEmployee;

        cookedOrdersTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        cookedOrdersTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        cookedOrdersTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        cookedOrdersTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cookedTime"));

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

        loadCookedOrders();

    }

    @FXML
    void backToDashIcon_onMouseClicked(MouseEvent event) {
        onClose();
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/deliverMainDash.fxml")));
            Stage stage = (Stage) this.deli_cookedOrdersAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("Pizza Express >>> Deliverer's Main DashBoard ");
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

    public void loadCookedOrders() {
        try {
            ArrayList<Deli_C_Order> deliCOrders = deliCOrderController.getAllOrders();

            deliCOrderTM = cookedOrdersTbl.getItems();
            deliCOrderTM.removeAll(deliCOrderTM);

            for (Deli_C_Order order : deliCOrders) {
                deliCOrderTM.add(new Deli_C_OrderTM(
                        order.getOrderID(),
                        order.getCookedTime()
                ));
            }
            cookedOrdersTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cookedOrdersTbl_onMouseClicked(MouseEvent event) {
        Deli_C_OrderTM orderTM = cookedOrdersTbl.getSelectionModel().getSelectedItem();
        if (orderTM != null) {
            try {
                if (deliCOrderController.reserveOrder(orderTM.getOrderID())) {
                    if (!this.orderID.equals(orderTM.getOrderID())) {
                        deliCOrderController.releaseOrder(this.orderID);
                        this.orderID = orderTM.getOrderID();
                    }
                    try {
                        ArrayList<Deli_C_Order> deliCOrders = deliCOrderController.getAllOrders();
                        for (Deli_C_Order order : deliCOrders) {
                            if (order.getOrderID().equals(orderTM.getOrderID())) {
                                orderIDTF.setText(order.getOrderID());
                                cookedTimeTF.setText(order.getCookedTime());
                                customerNameTF.setText(order.getCusName());
                                customerAddressTA.setText(order.getCusAddress_no() + ", " + order.getCusAddress_street() + ", " + order.getCusAddress_village() + ", " + order.getCusAddress_city());
                                contactNumberHomeTF.setText(order.getCusCNhome());
                                contactNumberMobileTF.setText(order.getCusCNMobile());
                                statusTF.setText("Cooked");
                                break;
                            }

                            ArrayList<Deli_C_OrderItem> deliCOrderItems = deliCOrderController.getAllOrderItems(orderTM.getOrderID());
                            deliCOrderItemTM = selectedOrderTbl.getItems();
                            deliCOrderItemTM.removeAll(deliCOrderItemTM);

                            for (Deli_C_OrderItem orderItem : deliCOrderItems) {
                                deliCOrderItemTM.add(new Deli_C_OrderItemTM(
                                        orderItem.getItemCode(),
                                        orderItem.getName() + " - " + orderItem.getSize(),
                                        Integer.toString(orderItem.getQty())
                                ));
                            }
                            selectedOrderTbl.refresh();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! \n\n The order you have selected, has been reserved by another \n client.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            acceptOrderBtn.setDisable(false);
        }
    }

    @FXML
    void acceptOrderBtn_onAction(ActionEvent event) {
        try {
            boolean result = deliCOrderController.acceptOrder(orderIDTF.getText(), employee.getEmployeeID(), currentTimeTF.getText(), "Delivering", this.toString());

            if (result) {
                backToDashIcon.setDisable(true);
                startedTimeTF.setText(currentTimeTF.getText());
                acceptOrderBtn.setDisable(true);
                doneBtn.setDisable(false);
                loadCookedOrders();
                cookedOrdersTbl.setDisable(true);
                statusTF.setText("Delivering");

                new Alert(Alert.AlertType.INFORMATION, "The order has been reserved successfully for you.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "failed to reserve the order", ButtonType.OK).show();
                loadCookedOrders();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void doneBtn_onAction(ActionEvent event) {
        try {
            boolean result = deliCOrderController.doneOrder(orderIDTF.getText(), currentTimeTF.getText(), "Delivered");

            if (result) {
                backToDashIcon.setDisable(false);
                orderIDTF.setText("");
                cookedTimeTF.setText("");
                startedTimeTF.setText("");
                deliCOrderItemTM = selectedOrderTbl.getItems();
                deliCOrderItemTM.removeAll(deliCOrderItemTM);
                customerNameTF.setText("");
                customerAddressTA.setText("");
                contactNumberHomeTF.setText("");
                contactNumberMobileTF.setText("");
                acceptOrderBtn.setDisable(false);
                doneBtn.setDisable(true);
                statusTF.setText("");
                cookedOrdersTbl.setDisable(false);
                cookedOrdersTbl.requestFocus();
                cookedOrdersTbl.refresh();

                new Alert(Alert.AlertType.INFORMATION, "The order has been submitted successfully to database.", ButtonType.OK).show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to submit the order to database.", ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void informTableUpdate() {
        System.out.println("in UI controller");
        Platform.runLater(() -> {
            if (acceptOrderBtn.isDisable()) {
                loadCookedOrders();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "The cooked orders table has been updated because of another client. \n So we has been reloaded cooked order table for you.", ButtonType.OK).show();
                loadCookedOrders();

            }
        });
    }

}
