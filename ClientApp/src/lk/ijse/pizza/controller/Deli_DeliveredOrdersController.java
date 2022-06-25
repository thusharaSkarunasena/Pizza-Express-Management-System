package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import lk.ijse.pizza.model.Deli_D_Order;
import lk.ijse.pizza.model.Deli_D_OrderItem;
import lk.ijse.pizza.model.Employee;
import lk.ijse.pizza.view.tblModel.Deli_D_OrderItemTM;
import lk.ijse.pizza.view.tblModel.Deli_D_OrderTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Deli_DeliveredOrdersController implements Initializable {

    @FXML
    private AnchorPane deli_deliveredOrdersAnchPane;
    @FXML
    private ImageView backToDashIcon;
    @FXML
    private JFXTextField orderIDTF;
    @FXML
    private TextField statusTF;
    @FXML
    private TextField startedTimeTF;
    @FXML
    private TextField endedTimeTF;
    @FXML
    private TextField orderDateTF;
    @FXML
    private TableView<Deli_D_OrderItemTM> selectedOrderTbl;
    @FXML
    private JFXTextField customerNameTF;
    @FXML
    private JFXTextArea customerAddressTA;
    @FXML
    private JFXTextField contactNumberHomeTF;
    @FXML
    private JFXTextField contactNumberMobileTF;
    @FXML
    private TableView<Deli_D_OrderTM> completedOrdersTbl;

    private ObservableList<Deli_D_OrderTM> deliDOrderTM;
    private ObservableList<Deli_D_OrderItemTM> deliDOrderItemTM;
    private Deli_D_OrderController deliDOrderController;
    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            deliDOrderController = (Deli_D_OrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.DELIDORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        employee = LogInControllerAll.loggedEmployee;

        completedOrdersTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        completedOrdersTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        completedOrdersTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        completedOrdersTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderID"));

        selectedOrderTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        selectedOrderTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        selectedOrderTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nameNsize"));
        selectedOrderTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadDeliveredOrders();

    }

    @FXML
    void backToDashIcon_onMouseClicked(MouseEvent event) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/deliverMainDash.fxml")));
            Stage stage = (Stage) this.deli_deliveredOrdersAnchPane.getScene().getWindow();
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

    public void loadDeliveredOrders() {

        try {
            ArrayList<Deli_D_Order> deliDOrders = deliDOrderController.getAllOrders(employee.getEmployeeID());


            if (deliDOrders != null) {
                deliDOrderTM = completedOrdersTbl.getItems();
                deliDOrderTM.removeAll(deliDOrderTM);

                for (Deli_D_Order dOrder : deliDOrders) {
                    deliDOrderTM.add(new Deli_D_OrderTM(
                            dOrder.getOrderDate(),
                            dOrder.getOrderID()
                    ));
                }
                completedOrdersTbl.refresh();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void completedOrdersTbl_onMouseClicked(MouseEvent event) {
        Deli_D_OrderTM dOrderTM = completedOrdersTbl.getSelectionModel().getSelectedItem();

        if (dOrderTM != null) {
            try {
                ArrayList<Deli_D_Order> deliDOrders = deliDOrderController.getAllOrders(employee.getEmployeeID());
                for (Deli_D_Order dOrder : deliDOrders) {
                    if (dOrder.getOrderID().equals(dOrderTM.getOrderID())) {
                        orderIDTF.setText(dOrder.getOrderID());
                        orderDateTF.setText(dOrder.getOrderDate());
                        startedTimeTF.setText(dOrder.getStartedTime());
                        endedTimeTF.setText(dOrder.getDeliveredTime());
                        customerNameTF.setText(dOrder.getCusName());
                        customerAddressTA.setText(dOrder.getCusAddress_no() + ", " + dOrder.getCusAddress_street() + ", " + dOrder.getCusAddress_village() + ", " + dOrder.getCusAddress_city());
                        contactNumberHomeTF.setText(dOrder.getCusCNhome());
                        contactNumberMobileTF.setText(dOrder.getCusCNMobile());
                        statusTF.setText(dOrder.getStatus());

                        deliDOrderItemTM = selectedOrderTbl.getItems();
                        deliDOrderItemTM.removeAll(deliDOrderItemTM);

                        ArrayList<Deli_D_OrderItem> dOrderItems = deliDOrderController.getAllOrderItems(dOrderTM.getOrderID());

                        for (Deli_D_OrderItem dOrderItem : dOrderItems) {
                            deliDOrderItemTM.add(new Deli_D_OrderItemTM(
                                    dOrderItem.getItemCode(),
                                    dOrderItem.getName() + " - " + dOrderItem.getSize(),
                                    Integer.toString(dOrderItem.getQty())
                            ));
                        }
                        selectedOrderTbl.refresh();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
