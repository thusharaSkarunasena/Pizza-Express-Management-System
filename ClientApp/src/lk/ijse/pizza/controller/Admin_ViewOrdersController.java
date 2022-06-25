package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.connecter.ServerConnecter;
import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;
import lk.ijse.pizza.model.Admin_ViewOrder;
import lk.ijse.pizza.model.Admin_ViewOrderDetail;
import lk.ijse.pizza.view.tblModel.Admin_VIewOrderTM;
import lk.ijse.pizza.view.tblModel.Admin_ViewOrderDetailTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Admin_ViewOrdersController implements Initializable {

    @FXML
    private AnchorPane admin_viewOrdersAnchPane;
    @FXML
    private ImageView backToDashIcon;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Admin_VIewOrderTM> orderDetailsTbl;
    @FXML
    private JFXDatePicker startDateDP;
    @FXML
    private JFXDatePicker endDateDP;
    @FXML
    private JFXTextField chef_empIDTF;
    @FXML
    private JFXTextField deli_T1TF;
    @FXML
    private JFXTextField deli_empIDTF;
    @FXML
    private JFXTextField chef_T1TF;
    @FXML
    private JFXTextField deli_T2TF;
    @FXML
    private JFXTextField recep_t1TF;
    @FXML
    private JFXTextField recep_empIDTF;
    @FXML
    private JFXTextField chef_T2TF;
    @FXML
    private ImageView reloadIcon;
    @FXML
    private TableView<Admin_ViewOrderDetailTM> itemDetailsTbl;

    private Admin_ViewOrderController adminViewOrderController;
    private ObservableList<Admin_VIewOrderTM> admin_vIewOrderTM;
    private ObservableList<Admin_ViewOrderDetailTM> adminViewOrderDetailTM;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        try {
            adminViewOrderController = (Admin_ViewOrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.ADMINVIEWORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        orderDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(5).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(6).setStyle("-fx-alignment:center");
        orderDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        orderDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerID"));
        orderDetailsTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("total"));
        orderDetailsTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        orderDetailsTbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("netAmount"));
        orderDetailsTbl.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("status"));

        itemDetailsTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemDetailsTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        itemDetailsTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        itemDetailsTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemDetailsTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nameNsize"));
        itemDetailsTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadViewOrdersTable();

    }

    public void loadViewOrdersTable() {
        try {
            ArrayList<Admin_ViewOrder> adminViewOrders = adminViewOrderController.getAllOrders();

            admin_vIewOrderTM = orderDetailsTbl.getItems();
            admin_vIewOrderTM.removeAll(admin_vIewOrderTM);

            for (Admin_ViewOrder viewOrder : adminViewOrders) {
                admin_vIewOrderTM.add(new Admin_VIewOrderTM(
                        viewOrder.getOrderID(),
                        viewOrder.getDate(),
                        viewOrder.getCustomerID(),
                        Double.toString(viewOrder.getTotal()),
                        Double.toString(viewOrder.getDiscount()),
                        Double.toString(viewOrder.getNetAmount()),
                        viewOrder.getStatus()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToDashIcon_onMouseClicked(MouseEvent event) {
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/adminMainDash.fxml")));
            Stage stage = (Stage) this.admin_viewOrdersAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("Pizza Express >>> Admin's Main DashBoard ");
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
    public void endDateDP_onAction(ActionEvent event) {

    }

    @FXML
    public void orderDetailsTbl_onMouserClick(MouseEvent event) {
        {
            try {
                Admin_VIewOrderTM orderTM = orderDetailsTbl.getSelectionModel().getSelectedItem();

                ArrayList<Admin_ViewOrder> adminViewOrders = adminViewOrderController.getAllOrders();

                for (Admin_ViewOrder viewOrder : adminViewOrders) {
                    if (viewOrder.getOrderID().equals(orderTM.getOrderID())) {
                        recep_empIDTF.setText(viewOrder.getRecep_employeeID());
                        recep_t1TF.setText(viewOrder.getRecep_queuedTime());
                        chef_empIDTF.setText(viewOrder.getChef_employeeID());
                        chef_T1TF.setText(viewOrder.getChef_acceptedTime());
                        chef_T2TF.setText(viewOrder.getChef_finishedTime());
                        deli_empIDTF.setText(viewOrder.getDeli_employeeID());
                        deli_T1TF.setText(viewOrder.getDeli_acceptedTime());
                        deli_T2TF.setText(viewOrder.getDeli_finishedTime());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        {
            Admin_VIewOrderTM orderTM = orderDetailsTbl.getSelectionModel().getSelectedItem();
            if (orderTM != null) {
                try {
                    ArrayList<Admin_ViewOrderDetail> orderDetails = adminViewOrderController.getorderDetails(orderTM.getOrderID());
                    adminViewOrderDetailTM = itemDetailsTbl.getItems();
                    adminViewOrderDetailTM.removeAll(adminViewOrderDetailTM);
                    for (Admin_ViewOrderDetail viewOrderDetail : orderDetails) {
                        adminViewOrderDetailTM.add(new Admin_ViewOrderDetailTM(
                                viewOrderDetail.getItemCode(),
                                viewOrderDetail.getName() + " - " + viewOrderDetail.getSize(),
                                Integer.toString(viewOrderDetail.getQty())
                        ));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                adminViewOrderDetailTM = itemDetailsTbl.getItems();
                adminViewOrderDetailTM.removeAll(adminViewOrderDetailTM);
            }
        }
    }

    @FXML
    void reloadIcon_onMouseClicked(MouseEvent event) {
        loadViewOrdersTable();
        clearAll();
    }

    public void clearAll() {
        searchBoxTF.setText("");
        recep_empIDTF.setText("");
        recep_t1TF.setText("");
        chef_empIDTF.setText("");
        chef_T1TF.setText("");
        chef_T2TF.setText("");
        deli_empIDTF.setText("");
        deli_T1TF.setText("");
        deli_T2TF.setText("");
        searchBoxTF.setStyle("-fx-text-fill: #000000");

        adminViewOrderDetailTM = itemDetailsTbl.getItems();
        adminViewOrderDetailTM.removeAll(adminViewOrderDetailTM);
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (searchBoxTF.getText().isEmpty()) {
            loadViewOrdersTable();
        } else {
            try {
                ArrayList<Admin_ViewOrder> adminViewOrders = adminViewOrderController.searchOrders(searchBoxTF.getText());

                if (adminViewOrders.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadViewOrdersTable();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    admin_vIewOrderTM = orderDetailsTbl.getItems();
                    admin_vIewOrderTM.removeAll(admin_vIewOrderTM);

                    for (Admin_ViewOrder viewOrder : adminViewOrders) {
                        admin_vIewOrderTM.add(new Admin_VIewOrderTM(
                                viewOrder.getOrderID(),
                                viewOrder.getDate(),
                                viewOrder.getCustomerID(),
                                Double.toString(viewOrder.getTotal()),
                                Double.toString(viewOrder.getDiscount()),
                                Double.toString(viewOrder.getNetAmount()),
                                viewOrder.getStatus()
                        ));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void searchBox_onMouseClick(MouseEvent event) {
        searchBoxTF.selectAll();
    }

    @FXML
    public void startDateDP_onAction(ActionEvent event) {

    }

    public void informTableUpdate() {

    }

}
