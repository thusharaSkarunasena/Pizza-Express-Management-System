package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.connecter.ServerConnecter;
import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;
import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.model.RecepOrder;
import lk.ijse.pizza.model.RecepOrderItem;
import lk.ijse.pizza.observer.RecepOrderObserver;
import lk.ijse.pizza.observerImpl.RecepOrderObserverImpl;
import lk.ijse.pizza.view.tblModel.Recep_OrderItemTM;
import lk.ijse.pizza.view.tblModel.Recep_OrderManagementTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Recep_OrderManagementController implements Initializable {

    private static RecepOrderController recepOrderController;
    private static RecepOrderObserver recepOrderObserver;
    private static String orderID = null;
    @FXML
    private AnchorPane recep_orderManagementAnchPane;
    @FXML
    private ImageView backToDashIcon;
    @FXML
    private JFXButton newBtn;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private JFXTextField orderIDTF;
    @FXML
    private JFXTextField qtyTF;
    @FXML
    private JFXTextField customerNameTF;
    @FXML
    private JFXComboBox<String> customerIDCombBox;
    @FXML
    private JFXComboBox<String> itemCodeComboBox;
    @FXML
    private JFXTextField itemNameTF;
    @FXML
    private TextField statusTF;
    @FXML
    private JFXButton addItemBtn;
    @FXML
    private TableView<Recep_OrderItemTM> orderItemTbl;
    @FXML
    private JFXButton updateItemBtn;
    @FXML
    private JFXButton deleteItemBtn;
    @FXML
    private JFXButton clearItemBtn;
    @FXML
    private JFXTextField employeeIDTF;
    @FXML
    private JFXTextField orderDateTF;
    @FXML
    private TextField totalAmountTF;
    @FXML
    private JFXTextField itemPriceTF;
    @FXML
    private JFXTextField discountTF;
    @FXML
    private TextField netAmountTF;
    @FXML
    private JFXTextField itemSizeTF;
    @FXML
    private TextField searchBoxTF;
    @FXML

    private TableView<Recep_OrderManagementTM> orderManagementTbl;
    private ObservableList<Recep_OrderManagementTM> recepOrderManagementTM;
    private ObservableList<Recep_OrderItemTM> recepOrderItemTM;

    public static void onClose() {
        try {
            if (recepOrderObserver != null) {
                recepOrderController.removeItemObserver(recepOrderObserver);
            }
            if (orderID != null) {
                recepOrderController.releaseRecepOrder(orderID);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();

        orderID = "";

        try {
            recepOrderController = (RecepOrderController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.RECEPORDERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            recepOrderObserver = new RecepOrderObserverImpl(this);
            recepOrderController.addItemObserver(recepOrderObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        orderManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        orderManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        orderManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        orderManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        orderManagementTbl.getColumns().get(4).setStyle("-fx-alignment:center");
        orderManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerID"));
        orderManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("netAmount"));
        orderManagementTbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));

        orderItemTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        orderItemTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        orderItemTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        orderItemTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        orderItemTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qty"));
        orderItemTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("total"));

        loadOrderTable();
        generateOrderID();
        setOrderDate();
        setEmployeeID();
        loadCustomerIDComBox();
        loadItemCodeComBox();

        newBtn.fire();
        newBtn.requestFocus();

    }

    @FXML
    public void backToDashIcon_onMouseClicked(MouseEvent event) {
        onClose();
        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/receptionMainDash.fxml")));
            Stage stage = (Stage) this.recep_orderManagementAnchPane.getScene().getWindow();
            stage.setScene(temp);
            stage.setTitle("Pizza Express >>> Reception's Main DashBoard ");
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

    public void loadOrderTable() {
        try {
            ArrayList<RecepOrder> recepOrders = recepOrderController.getAllOrders();

            if (!recepOrders.isEmpty()) {
                recepOrderManagementTM = orderManagementTbl.getItems();
                recepOrderManagementTM.removeAll(recepOrderManagementTM);

                for (RecepOrder recepOrder : recepOrders) {
                    recepOrderManagementTM.add(new Recep_OrderManagementTM(
                            recepOrder.getOrderID(),
                            recepOrder.getOrderDate(),
                            recepOrder.getCustomerID(),
                            Double.toString(recepOrder.getNetAmount()),
                            recepOrder.getStatus()
                    ));
                }
            } else {
                recepOrderManagementTM = orderManagementTbl.getItems();
                recepOrderManagementTM.removeAll(recepOrderManagementTM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateOrderID() {
        try {
            orderIDTF.setText(recepOrderController.generateOrderID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrderDate() {
        orderDateTF.setText(LocalDate.now().toString());
    }

    public void setEmployeeID() {
        employeeIDTF.setText(LogInControllerAll.loggedEmployee.getEmployeeID());
    }

    public void loadCustomerIDComBox() {
        try {
            ArrayList<Customer> customers = recepOrderController.getCustomers();
            customerIDCombBox.getItems().removeAll();
            ObservableList customerIDs = FXCollections.observableArrayList();
            for (Customer customer : customers) {
                customerIDs.add(customer.getCustomerID());
            }
            customerIDCombBox.getItems().addAll(customerIDs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadItemCodeComBox() {
        try {
            ArrayList<Item> items = recepOrderController.getItems();
            itemCodeComboBox.getItems().removeAll();
            ObservableList itemCodes = FXCollections.observableArrayList();
            for (Item item : items) {
                itemCodes.add(item.getItemCode());
            }
            itemCodeComboBox.getItems().addAll(itemCodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void customerIDCombBox_onAction(ActionEvent actionEvent) {
        try {
            Customer customer = recepOrderController.getCustomer(customerIDCombBox.getValue());
            if (customer != null) {
                customerNameTF.setText(customer.getName());
                itemCodeComboBox.setDisable(false);
                itemCodeComboBox.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void itemCodeComboBox_onAction(ActionEvent actionEvent) {
        clearItemBtn.setDisable(false);
        deleteItemBtn.setDisable(true);
        try {
            Item item = recepOrderController.getItem(itemCodeComboBox.getValue());
            if (item != null) {
                itemNameTF.setText(item.getName());
                itemSizeTF.setText(item.getSize());
                itemPriceTF.setText(Double.toString(item.getPrice()));
                qtyTF.setDisable(false);
                addItemBtn.setDisable(true);
                updateItemBtn.setDisable(true);
                qtyTF.setText("");
                qtyTF.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        recepOrderManagementTM = orderManagementTbl.getItems();
        boolean isExist = false;
        for (Recep_OrderManagementTM recepOrderManagementTM : recepOrderManagementTM) {
            if (recepOrderManagementTM.getOrderID().equals(orderIDTF.getText())) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            try {
                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.setTitle("Delete?");
                deleteAlert.setHeaderText(null);
                deleteAlert.setContentText(" Are You Sure to Delete '" + orderIDTF.getText() + "' Order?");
                Optional<ButtonType> action = deleteAlert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    boolean result = recepOrderController.deleteOrder(this.toString(), orderIDTF.getText());
                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Order has been Deleted Successfully.", ButtonType.OK).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Delete the Order.", ButtonType.OK).show();
                    }
                } else {
                    deleteAlert.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an existing order to continue with deleting process.", ButtonType.OK).show();
        }
        newBtn.fire();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {
        customerIDCombBox.setValue("");
        customerNameTF.setText("");
        itemCodeComboBox.setValue("");
        itemCodeComboBox.setDisable(true);
        itemNameTF.setText("");
        itemSizeTF.setText("");
        itemPriceTF.setText("");
        qtyTF.setText("");
        qtyTF.setDisable(true);
        recepOrderItemTM = orderItemTbl.getItems();
        recepOrderItemTM.removeAll(recepOrderItemTM);
        totalAmountTF.setText("0.00");
        discountTF.setText("0.0");
        netAmountTF.setText("0.00");
        statusTF.setText("");

        loadOrderTable();
        generateOrderID();
        setOrderDate();
        setEmployeeID();

        customerIDCombBox.setDisable(false);

        addItemBtn.setDisable(true);
        updateItemBtn.setDisable(true);
        deleteItemBtn.setDisable(true);
        clearItemBtn.setDisable(true);

        searchBoxTF.setStyle("-fx-text-fill: #000000");

        newBtn.requestFocus();
    }

    @FXML
    public void orderManagementTbl_OnMouseClick(MouseEvent event) {
        Recep_OrderManagementTM recep_orderManagementTM = orderManagementTbl.getSelectionModel().getSelectedItem();

        if (recep_orderManagementTM != null) {
            try {
                if (recepOrderController.reserveRecepOrder(recep_orderManagementTM.getOrderID())) {
                    if (!this.orderID.equals(recep_orderManagementTM.getOrderID())) {
                        recepOrderController.releaseRecepOrder(this.orderID);
                        this.orderID = recep_orderManagementTM.getOrderID();
                    }
                    RecepOrder recepOrder = recepOrderController.getOrder(recep_orderManagementTM.getOrderID());

                    orderIDTF.setText(recepOrder.getOrderID());
                    orderDateTF.setText(recepOrder.getOrderDate());
                    employeeIDTF.setText(recepOrder.getEmployeeID());
                    customerIDCombBox.setValue(recepOrder.getCustomerID());
                    customerNameTF.setText((recepOrderController.getCustomer(recepOrder.getCustomerID())).getName());
                    itemCodeComboBox.setValue("");
                    itemNameTF.setText("");
                    itemSizeTF.setText("");
                    itemPriceTF.setText("");
                    qtyTF.setText("");
                    totalAmountTF.setText(Double.toString(recepOrder.getTotalAmount()));
                    discountTF.setText(Double.toString(recepOrder.getDiscount()));
                    netAmountTF.setText(Double.toString(recepOrder.getNetAmount()));
                    statusTF.setText(recepOrder.getStatus());
                    newBtn.requestFocus();

                    ArrayList<RecepOrderItem> recepOrderItems = recepOrderController.getItems(recep_orderManagementTM.getOrderID());

                    recepOrderItemTM = orderItemTbl.getItems();
                    recepOrderItemTM.removeAll(recepOrderItemTM);
                    for (RecepOrderItem recepOrderItem : recepOrderItems) {
                        recepOrderItemTM.add(new Recep_OrderItemTM(
                                recepOrderItem.getItemCode(),
                                Integer.toString(recepOrderItem.getQty()),
                                Double.toString(recepOrderItem.getTotal())
                        ));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! \n\n The order you have selected, has been reserved by another \n client.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        customerIDCombBox.setDisable(true);
    }

    @FXML
    public void qtyTF_onAction(ActionEvent event) {
        if (addItemBtn.isDisable()) {
            updateItemBtn.fire();
        } else {
            addItemBtn.fire();
        }
    }

    @FXML
    public void qtyTF_onKeyReleased(KeyEvent keyEvent) {
        deleteItemBtn.setDisable(true);
        if (!qtyTF.getText().isEmpty()) {
            recepOrderItemTM = orderItemTbl.getItems();
            boolean isExist = false;
            for (Recep_OrderItemTM orderItemTM : recepOrderItemTM) {
                if (orderItemTM.getItemCode().equals(itemCodeComboBox.getValue())) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                updateItemBtn.setDisable(false);
            } else {
                addItemBtn.setDisable(false);
            }
        } else {
            updateItemBtn.setDisable(true);
            addItemBtn.setDisable(true);
        }
    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {
        recepOrderManagementTM = orderManagementTbl.getItems();

        boolean isExist = false;

        for (Recep_OrderManagementTM orderManagementTM : recepOrderManagementTM) {
            if (orderManagementTM.getOrderID().equals(orderIDTF.getText())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            if (!netAmountTF.getText().equals("0.00")) {
                RecepOrder recepOrder = new RecepOrder(
                        orderIDTF.getText(),
                        orderDateTF.getText(),
                        customerIDCombBox.getValue(),
                        Double.parseDouble(totalAmountTF.getText()),
                        Double.parseDouble(discountTF.getText()),
                        Double.parseDouble(netAmountTF.getText()),
                        employeeIDTF.getText(),
                        "Queue"
                );

                recepOrderItemTM = orderItemTbl.getItems();
                ArrayList<RecepOrderItem> recepOrderItems = new ArrayList<>();
                for (Recep_OrderItemTM recep_orderItemTM : recepOrderItemTM) {
                    recepOrderItems.add(new RecepOrderItem(
                            recep_orderItemTM.getItemCode(),
                            Integer.parseInt(recep_orderItemTM.getQty()),
                            Double.parseDouble(recep_orderItemTM.getTotal())
                    ));
                }

                try {
                    Boolean result = recepOrderController.saveOrder(this.toString(), recepOrder, recepOrderItems);

                    if (result) {
                        new Alert(Alert.AlertType.INFORMATION, "Order has been Saved Successfully.", ButtonType.OK).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to Save the Order.", ButtonType.OK).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please fill required fields to save an order.", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Sorry! \n The order you are going to save is already in order table. \n Try to update the order.", ButtonType.OK).show();
        }
        newBtn.fire();
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (searchBoxTF.getText().isEmpty()) {
            loadOrderTable();
        } else {
            try {
                ArrayList<RecepOrder> recepOrders = recepOrderController.searchOrder(searchBoxTF.getText());
                if (recepOrders.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadOrderTable();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    recepOrderManagementTM = orderManagementTbl.getItems();
                    recepOrderManagementTM.removeAll(recepOrderManagementTM);

                    for (RecepOrder recepOrder : recepOrders) {
                        recepOrderManagementTM.add(new Recep_OrderManagementTM(
                                recepOrder.getOrderID(),
                                recepOrder.getOrderDate(),
                                recepOrder.getCustomerID(),
                                Double.toString(recepOrder.getNetAmount()),
                                recepOrder.getStatus()
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
    public void updateBtn_onAction(ActionEvent event) {
//        if (!netAmountTF.getText().equals("0.00")) {
//            RecepOrder recepOrder = new RecepOrder(
//                    orderIDTF.getText(),
//                    orderDateTF.getText(),
//                    customerIDCombBox.getValue(),
//                    Double.parseDouble(totalAmountTF.getText()),
//                    Double.parseDouble(discountTF.getText()),
//                    Double.parseDouble(netAmountTF.getText()),
//                    employeeIDTF.getText(),
//                    statusTF.getText()
//            );
//
//            recepOrderItemTM = orderItemTbl.getItems();
//            ArrayList<RecepOrderItem> recepOrderItems = new ArrayList<>();
//            for (Recep_OrderItemTM recep_orderItemTM : recepOrderItemTM) {
//                recepOrderItems.add(new RecepOrderItem(
//                        recep_orderItemTM.getItemCode(),
//                        Integer.parseInt(recep_orderItemTM.getQty()),
//                        Double.parseDouble(recep_orderItemTM.getTotal())
//                ));
//            }
//
//            try {
//                Boolean result = recepOrderController.updateOrder(this.toString(), recepOrder, recepOrderItems);
//
//                if (result) {
//                    new Alert(Alert.AlertType.INFORMATION, "Order has been Updated Successfully.", ButtonType.OK).show();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Failed to Update the Order.", ButtonType.OK).show();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Please fill required fields to update an order.", ButtonType.OK).show();
//        }
    }

    @FXML
    public void addItemBtn_onAction(ActionEvent actionEvent) {
        double total = Double.parseDouble(itemPriceTF.getText()) * Integer.parseInt(qtyTF.getText());
        recepOrderItemTM.add(new Recep_OrderItemTM(
                itemCodeComboBox.getValue(),
                qtyTF.getText(),
                Double.toString(total)
        ));

        itemCodeComboBox.setValue("");
        itemNameTF.setText("");
        itemSizeTF.setText("");
        itemPriceTF.setText("");
        qtyTF.setText("");

        itemCodeComboBox.requestFocus();
        clearItemBtn.fire();
    }

    public void generateAmounts() {
        double totalAmount = 0.0;

        for (Recep_OrderItemTM recep_orderItemTM : orderItemTbl.getItems()) {
            totalAmount += Double.parseDouble(recep_orderItemTM.getTotal());
        }

        totalAmountTF.setText(Double.toString(totalAmount));

        if (!discountTF.getText().isEmpty()) {
            double totalAmount_double = Double.parseDouble(totalAmountTF.getText());
            double discount_double = Double.parseDouble(discountTF.getText());
            netAmountTF.setText(Double.toString(((totalAmount_double) * (100 - discount_double) / 100)));
        } else {
            netAmountTF.setText(totalAmountTF.getText());
        }

    }

    @FXML
    public void orderItemTbl_onMouseClicked(MouseEvent mouseEvent) {
        try {
            Recep_OrderItemTM recep_orderItemTM = orderItemTbl.getSelectionModel().getSelectedItem();
            Item item = recepOrderController.getItem(recep_orderItemTM.getItemCode());

            itemCodeComboBox.setValue(item.getItemCode());
            itemNameTF.setText(item.getName());
            itemSizeTF.setText(item.getSize());
            itemPriceTF.setText(Double.toString(item.getPrice()));
            qtyTF.setText(recep_orderItemTM.getQty());

        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteItemBtn.setDisable(false);
    }

    @FXML
    public void updateItemBtn_onAction(ActionEvent actionEvent) {
        recepOrderItemTM = orderItemTbl.getItems();
        double total;
        for (Recep_OrderItemTM orderItemTM : recepOrderItemTM) {
            if (orderItemTM.getItemCode().equals(itemCodeComboBox.getValue())) {
                orderItemTM.setQty(qtyTF.getText());
                total = Double.parseDouble(itemPriceTF.getText()) * Integer.parseInt(qtyTF.getText());
                orderItemTM.setTotal(Double.toString(total));
                break;
            }
        }
        clearItemBtn.fire();
    }

    @FXML
    public void deleteItemBtn_onAction(ActionEvent actionEvent) {
        Recep_OrderItemTM orderItemTM = orderItemTbl.getSelectionModel().getSelectedItem();
        recepOrderItemTM = orderItemTbl.getItems();
        recepOrderItemTM.remove(orderItemTM);
        clearItemBtn.fire();
    }

    @FXML
    public void clearItemBtn_onAction(ActionEvent actionEvent) {
        itemCodeComboBox.setValue("");
        itemCodeComboBox.requestFocus();
        itemNameTF.setText("");
        itemSizeTF.setText("");
        itemPriceTF.setText("");
        qtyTF.setText("");

        orderItemTbl.refresh();
        generateAmounts();

        addItemBtn.setDisable(true);
        updateItemBtn.setDisable(true);
        deleteItemBtn.setDisable(true);
        clearItemBtn.setDisable(true);

        customerIDCombBox.setDisable(true);

    }

    @FXML
    public void discountTF_onMouseClicked(MouseEvent event) {
        discountTF.setText("");
    }

    @FXML
    public void discountTF_onKeyReleased(KeyEvent keyEvent) {
        if (!discountTF.getText().isEmpty()) {
            double totalAmount_double = Double.parseDouble(totalAmountTF.getText());
            double discount_double = Double.parseDouble(discountTF.getText());
            netAmountTF.setText(Double.toString(((totalAmount_double) * (100 - discount_double) / 100)));
        } else {
            netAmountTF.setText(totalAmountTF.getText());
        }
    }

    public void informTableUpdate(RecepOrder recepOrder, String status) {
        Platform.runLater(() -> {
            try {
                Alert tableUpdateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                tableUpdateAlert.setTitle("Reload?");
                tableUpdateAlert.setHeaderText(null);
                tableUpdateAlert.setContentText("The order '" + recepOrder.getOrderID() + "' has been " + status + " by another Client. \n Do you want to reaload your order table right now?");
                Optional<ButtonType> action = tableUpdateAlert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    loadOrderTable();
                    generateOrderID();
                } else if (action.get() == ButtonType.CANCEL) {
                    tableUpdateAlert.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
