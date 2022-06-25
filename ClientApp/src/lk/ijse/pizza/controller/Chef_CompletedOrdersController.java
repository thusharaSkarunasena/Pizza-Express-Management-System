package lk.ijse.pizza.controller;

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
import lk.ijse.pizza.model.Chef_C_Order;
import lk.ijse.pizza.model.Chef_C_OrderItem;
import lk.ijse.pizza.model.Employee;
import lk.ijse.pizza.view.tblModel.Chef_C_OrderItemTM;
import lk.ijse.pizza.view.tblModel.Chef_C_OrderTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Chef_CompletedOrdersController implements Initializable {

    @FXML
    private AnchorPane chef_completedOrdersAnchPane;
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
    private TableView<Chef_C_OrderItemTM> selectedOrderTbl;
    @FXML
    private TableView<Chef_C_OrderTM> completedOrdersTbl;

    private Chef_C_OrderController chef_c_orderController;
    private ObservableList<Chef_C_OrderTM> chef_c_orderTM;
    private ObservableList<Chef_C_OrderItemTM> chef_c_orderItemTM;


    private Employee employee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            chef_c_orderController = (Chef_C_OrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.CHEFCORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        employee = LogInControllerAll.loggedEmployee;

        loadCompletedOrder();

    }

    @FXML
    void backToDashIcon_onMouseClicked(MouseEvent event) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/chefMainDash.fxml")));
            Stage stage = (Stage) this.chef_completedOrdersAnchPane.getScene().getWindow();
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

    public void loadCompletedOrder() {
        try {
            ArrayList<Chef_C_Order> chefCOrders = chef_c_orderController.getAllOrders(employee.getEmployeeID());

            chef_c_orderTM = completedOrdersTbl.getItems();
            chef_c_orderTM.removeAll(chef_c_orderTM);

            for (Chef_C_Order chefCOrder : chefCOrders) {
                chef_c_orderTM.add(new Chef_C_OrderTM(
                        chefCOrder.getOrderDate(),
                        chefCOrder.getOrderID()
                ));
            }
            completedOrdersTbl.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void completedOrdersTbl_onMouseClicked(MouseEvent event) {
        Chef_C_OrderTM cOrderTM = completedOrdersTbl.getSelectionModel().getSelectedItem();
        if (cOrderTM != null) {
            try {

                {
                    ArrayList<Chef_C_Order> chefCOrders = chef_c_orderController.getAllOrders(employee.getEmployeeID());
                    for (Chef_C_Order chefCOrder : chefCOrders) {
                        if (chefCOrder.getOrderID().equals(cOrderTM.getOrderID())) {
                            orderIDTF.setText(chefCOrder.getOrderID());
                            orderDateTF.setText(chefCOrder.getOrderDate());
                            startedTimeTF.setText(chefCOrder.getStartedTime());
                            endedTimeTF.setText(chefCOrder.getEndedTime());
                            statusTF.setText(chefCOrder.getStatus());
                            break;
                        }
                    }
                }

                ArrayList<Chef_C_OrderItem> chef_c_orderItems = chef_c_orderController.getAllOrderItems(cOrderTM.getOrderID());

                chef_c_orderItemTM = selectedOrderTbl.getItems();
                chef_c_orderItemTM.removeAll(chef_c_orderItemTM);

                for (Chef_C_OrderItem item : chef_c_orderItems) {
                    chef_c_orderItemTM.add(new Chef_C_OrderItemTM(
                            item.getItemCode(),
                            item.getName() + " - " + item.getSize(),
                            Integer.toString(item.getQty())
                    ));
                }
                selectedOrderTbl.refresh();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
