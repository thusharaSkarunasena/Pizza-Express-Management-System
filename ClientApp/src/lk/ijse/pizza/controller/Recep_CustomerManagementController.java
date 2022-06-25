package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pizza.connecter.ServerConnecter;
import lk.ijse.pizza.controllerImpl.ControllerFactoryImpl;
import lk.ijse.pizza.model.Customer;
import lk.ijse.pizza.observer.CustomerObserver;
import lk.ijse.pizza.observerImpl.CustomerObserverImpl;
import lk.ijse.pizza.view.tblModel.Recep_CustomerManagementTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Recep_CustomerManagementController implements Initializable {

    private static CustomerController customerController;
    private static CustomerObserver customerObserver;
    private static String customerID = null;
    ObservableList<Recep_CustomerManagementTM> recep_customerManagementTM;
    @FXML
    private AnchorPane recep_customerManagementAnchPane;
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
    private JFXTextField nameTF;
    @FXML
    private JFXTextField customerIdTF;
    @FXML
    private JFXTextField nicTF;
    @FXML
    private JFXTextField contactNumberHomeTF;
    @FXML
    private JFXTextField contactNumberMobileTF;
    @FXML
    private JFXTextField addressVillageTF;
    @FXML
    private JFXTextField emailTF;
    @FXML
    private JFXTextField addressStreetTF;
    @FXML
    private JFXTextField addressNoTF;
    @FXML
    private JFXTextField addressCityTF;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Recep_CustomerManagementTM> customerManagementTbl;

    public static void onClose() {
        try {
            if (customerObserver != null) {
                customerController.removeCustomerObserver(customerObserver);
            }
            if (customerID != null) {
                customerController.releaseCustomer(customerID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();
        customerID = "";

        try {
            customerController = (CustomerController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.CUSTOMERCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            customerObserver = new CustomerObserverImpl(this);
            customerController.addCustomerObserver(customerObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        customerManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        customerManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        customerManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        customerManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        customerManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        customerManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        customerManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));

        customerIdTF.requestFocus();

        generateCustomerID();
        loadAllCustomers();

    }

    public void backToDashIcon_onMouseClicked(MouseEvent mouseEvent) {

        onClose();

        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/receptionMainDash.fxml")));
            Stage stage = (Stage) this.recep_customerManagementAnchPane.getScene().getWindow();
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

    public void generateCustomerID() {
        try {
            String nextCustomerID = customerController.generatrCustomerID();

            customerIdTF.setText(nextCustomerID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllCustomers() {

        try {
            ArrayList<Customer> customers = customerController.getAllCustomers();

            if (!customers.isEmpty()) {
                recep_customerManagementTM = customerManagementTbl.getItems();

                recep_customerManagementTM.removeAll(recep_customerManagementTM);

                for (Customer customer : customers) {
                    recep_customerManagementTM.add(new Recep_CustomerManagementTM(
                            customer.getCustomerID(),
                            customer.getName(),
                            customer.getNic(),
                            customer.getAddress_no() + "," + customer.getAddress_street() + "," + customer.getAddress_village() + "," + customer.getAddress_city()
                    ));
                }
            } else {
                recep_customerManagementTM = customerManagementTbl.getItems();

                recep_customerManagementTM.removeAll(recep_customerManagementTM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void CustomerManagementTblOnMouseClick(MouseEvent event) {
        Recep_CustomerManagementTM recep_customerManagementTM = customerManagementTbl.getSelectionModel().getSelectedItem();

        if (recep_customerManagementTM != null) {
            try {
                if (customerController.reserveCustomer(recep_customerManagementTM.getCustomerID())) {
                    if (!this.customerID.equals(recep_customerManagementTM.getCustomerID())) {
                        customerController.releaseCustomer(this.customerID);
                        this.customerID = recep_customerManagementTM.getCustomerID();
                    }
                    Customer customer = customerController.getCustomer(recep_customerManagementTM.getCustomerID());

                    customerIdTF.setText(customer.getCustomerID());
                    nameTF.setText(customer.getName());
                    addressNoTF.setText(customer.getAddress_no());
                    addressStreetTF.setText(customer.getAddress_street());
                    addressVillageTF.setText(customer.getAddress_village());
                    addressCityTF.setText(customer.getAddress_city());
                    nicTF.setText(customer.getNic());
                    contactNumberHomeTF.setText(customer.getTel_home());
                    contactNumberMobileTF.setText(customer.getTel_mobile());
                    emailTF.setText(customer.getEmail());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! \n\n The customer you have selected, has been reserved by another \n client.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void addressCityTF_onAction(ActionEvent event) {
        nicTF.requestFocus();
    }

    @FXML
    public void addressNoTF_onAction(ActionEvent event) {
        addressStreetTF.requestFocus();
    }

    @FXML
    public void addressStreetTF_onAction(ActionEvent event) {
        addressVillageTF.requestFocus();
    }

    @FXML
    public void addressVillageTF_onAction(ActionEvent event) {
        addressCityTF.requestFocus();
    }

    @FXML
    public void nicTF_onAction(ActionEvent event) {
        contactNumberHomeTF.requestFocus();
    }

    @FXML
    public void contactNumberHomeTF_onAction(ActionEvent event) {
        contactNumberMobileTF.requestFocus();
    }

    @FXML
    public void contactNumberMobileTF_onAction(ActionEvent event) {
        emailTF.requestFocus();
    }

    @FXML
    public void emailTF_onAction(ActionEvent event) {
        newBtn.requestFocus();
    }

    @FXML
    public void nameTF_onAction(ActionEvent event) {
        addressNoTF.requestFocus();
    }

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        try {
            boolean result = customerController.deleteCustomer(this.toString(), customerIdTF.getText());

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Customer has been Deleted Successfully.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete the Customer.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newBtn.fire();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {
        nameTF.setText("");
        addressNoTF.setText("");
        addressStreetTF.setText("");
        addressVillageTF.setText("");
        addressCityTF.setText("");
        nicTF.setText("");
        contactNumberHomeTF.setText("");
        contactNumberMobileTF.setText("");
        emailTF.setText("");
        searchBoxTF.setText("");

        generateCustomerID();
        loadAllCustomers();

        searchBoxTF.setStyle("-fx-text-fill: #000000");

        try {
            customerController.releaseCustomer(this.customerID);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {

        Customer customer = new Customer(
                customerIdTF.getText(),
                nameTF.getText(),
                addressNoTF.getText(),
                addressStreetTF.getText(),
                addressVillageTF.getText(),
                addressCityTF.getText(),
                nicTF.getText(),
                contactNumberHomeTF.getText(),
                contactNumberMobileTF.getText(),
                emailTF.getText()
        );

        boolean isExistsInTable = false;

        recep_customerManagementTM = customerManagementTbl.getItems();

        for (Recep_CustomerManagementTM customerManagementTM : recep_customerManagementTM) {
            if ((customer.getName().equals(customerManagementTM.getName())) | (customer.getNic().equals(customerManagementTM.getNic()))) {
                isExistsInTable = true;
                break;
            }
        }

        if (isExistsInTable) {
            new Alert(Alert.AlertType.ERROR, "The customer you are going to save is already in customer table.", ButtonType.OK).show();
        } else {
            try {
                boolean result = customerController.saveCustomer(this.toString(), customer);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer has been Saved Successfully.", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save the Customer.", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        newBtn.fire();
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (searchBoxTF.getText().isEmpty()) {
            loadAllCustomers();
        } else {
            try {
                ArrayList<Customer> customers = customerController.searchCustomer(searchBoxTF.getText());

                if (customers.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllCustomers();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    recep_customerManagementTM = customerManagementTbl.getItems();
                    recep_customerManagementTM.removeAll(recep_customerManagementTM);

                    for (Customer customer : customers) {
                        recep_customerManagementTM.add(new Recep_CustomerManagementTM(
                                customer.getCustomerID(),
                                customer.getName(),
                                customer.getNic(),
                                (customer.getAddress_no() + "," + customer.getAddress_street() + "," + customer.getAddress_village() + "," + customer.getAddress_city())
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
        Customer customer = new Customer(
                customerIdTF.getText(),
                nameTF.getText(),
                addressNoTF.getText(),
                addressStreetTF.getText(),
                addressVillageTF.getText(),
                addressCityTF.getText(),
                nicTF.getText(),
                contactNumberHomeTF.getText(),
                contactNumberMobileTF.getText(),
                emailTF.getText()
        );

        try {
            Boolean result = customerController.updateCustomer(this.toString(), customer);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Customer has been Updated Successfully.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update the Customer.", ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        newBtn.fire();
    }

    public void informTableUpdate(Customer customer, String status) {
        Platform.runLater(() -> {
            try {
                Alert tableUpdateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                tableUpdateAlert.setTitle("Reload?");
                tableUpdateAlert.setHeaderText(null);
                tableUpdateAlert.setContentText("The Customer '" + customer.getCustomerID() + "' has been " + status + " by another Client. \n Do you want to reaload your customer table right now?");
                Optional<ButtonType> action = tableUpdateAlert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    loadAllCustomers();
                    generateCustomerID();
                } else if (action.get() == ButtonType.CANCEL) {
                    tableUpdateAlert.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
