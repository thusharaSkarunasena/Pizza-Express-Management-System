package lk.ijse.pizza.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
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
import lk.ijse.pizza.model.Item;
import lk.ijse.pizza.observer.ItemObserver;
import lk.ijse.pizza.observerImpl.ItemObserverImpl;
import lk.ijse.pizza.view.tblModel.Recep_ItemManagementTM;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Recep_ItemManagementController implements Initializable {

    private static ItemController itemController;
    private static ItemObserver itemObserver;
    private static String itemCode = null;
    ObservableList<Recep_ItemManagementTM> recep_itemManagementTM;
    @FXML
    private AnchorPane recep_itemManagementAnchPane;
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
    private JFXTextField itemCodeTF;
    @FXML
    private JFXComboBox<String> sizeComBox;
    @FXML
    private JFXTextField priceTF;
    @FXML
    private JFXTextArea descriptionTA;
    @FXML
    private JFXTextArea otherDetailsTA;
    @FXML
    private TextField searchBoxTF;
    @FXML
    private TableView<Recep_ItemManagementTM> itemManagementTbl;

    public static void onClose() {
        try {
            if (itemObserver != null) {
                itemController.removeItemObserver(itemObserver);
            }
            if (itemCode != null) {
                itemController.releaseItem(itemCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.gc();
        itemCode = "";

        try {
            itemController = (ItemController) ServerConnecter.getInstance().getController(ControllerFactoryImpl.ContollerTypes.ITEMCONTROLLER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            itemObserver = new ItemObserverImpl(this);
            itemController.addItemObserver(itemObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemManagementTbl.getColumns().get(0).setStyle("-fx-alignment:center");
        itemManagementTbl.getColumns().get(1).setStyle("-fx-alignment:center");
        itemManagementTbl.getColumns().get(2).setStyle("-fx-alignment:center");
        itemManagementTbl.getColumns().get(3).setStyle("-fx-alignment:center");
        itemManagementTbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemManagementTbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        itemManagementTbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("size"));
        itemManagementTbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        loadAllItems();
        generateItemCode();
        loadSizeComboBox();

    }

    public void loadAllItems() {
        try {
            ArrayList<Item> items = itemController.getAllItems();

            if (!items.isEmpty()) {
                recep_itemManagementTM = itemManagementTbl.getItems();
                recep_itemManagementTM.removeAll(recep_itemManagementTM);

                for (Item item : items) {
                    recep_itemManagementTM.add(new Recep_ItemManagementTM(
                            item.getItemCode(),
                            item.getName(),
                            item.getSize(),
                            Double.toString(item.getPrice())
                    ));
                }
            } else {
                recep_itemManagementTM = itemManagementTbl.getItems();
                recep_itemManagementTM.removeAll(recep_itemManagementTM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateItemCode() {
        try {
            itemCodeTF.setText(itemController.generatrItemCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSizeComboBox() {
        sizeComBox.getItems().addAll("Small", "Medium", "Large");
    }

    @FXML
    public void backToDashIcon_onMouseClicked(MouseEvent event) {

        onClose();

        try {
            Scene temp = new Scene(FXMLLoader.load(this.getClass().getResource("/lk/ijse/pizza/view/receptionMainDash.fxml")));
            Stage stage = (Stage) this.recep_itemManagementAnchPane.getScene().getWindow();
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

    @FXML
    public void deleteBtn_onAction(ActionEvent event) {
        try {
            boolean result = itemController.deleteItem(this.toString(), itemCodeTF.getText());

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Item has been Deleted Successfully.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete the Item.", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        newBtn.fire();
    }

    @FXML
    public void itemManagementTbl_OnMouseClick(MouseEvent event) {
        Recep_ItemManagementTM itemManagementTM = itemManagementTbl.getSelectionModel().getSelectedItem();

        if (itemManagementTM != null) {
            try {
                if (itemController.reserveItem(itemManagementTM.getItemCode())) {
                    if (!this.itemCode.equals(itemManagementTM.getItemCode())) {
                        itemController.releaseItem(this.itemCode);
                        this.itemCode = itemManagementTM.getItemCode();
                    }
                    Item item = itemController.getItem(itemManagementTM.getItemCode());

                    itemCodeTF.setText(item.getItemCode());
                    nameTF.setText(item.getName());
                    descriptionTA.setText(item.getDescription());
                    sizeComBox.setValue(item.getSize());
                    priceTF.setText(Double.toString(item.getPrice()));
                    otherDetailsTA.setText(item.getOther_detail());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Sorry! \n\n The item you have selected, has been reserved by another \n client.", ButtonType.OK).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void nameTF_onAction(ActionEvent event) {
        descriptionTA.requestFocus();
    }

    @FXML
    public void newBtn_onAction(ActionEvent event) {

        nameTF.setText("");
        descriptionTA.setText("");
        sizeComBox.setValue("");
        priceTF.setText("");
        otherDetailsTA.setText("");
        searchBoxTF.setText("");

        generateItemCode();
        loadAllItems();

        searchBoxTF.setStyle("-fx-text-fill: #000000");

        newBtn.requestFocus();

    }

    @FXML
    public void priceTF_onAction(ActionEvent event) {
        otherDetailsTA.requestFocus();
    }

    @FXML
    public void saveBtn_onAction(ActionEvent event) {
        Item item = new Item(
                itemCodeTF.getText(),
                nameTF.getText(),
                descriptionTA.getText(),
                sizeComBox.getValue().toString(),
                Double.parseDouble(priceTF.getText()),
                otherDetailsTA.getText()
        );

        recep_itemManagementTM = itemManagementTbl.getItems();
        boolean isExists = false;
        for (Recep_ItemManagementTM itemManagementTM : recep_itemManagementTM) {
            if (itemManagementTM.getName().equals(nameTF.getText()) & itemManagementTM.getSize().equals(sizeComBox.getValue().toString())) {
                isExists = true;
                break;
            }
        }

        if (isExists) {
            new Alert(Alert.AlertType.ERROR, "The item you are going to save is already in item table", ButtonType.OK).show();
        } else {
            try {
                boolean result = itemController.saveItem(this.toString(), item);

                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Item has been Saved Successfully.", ButtonType.OK).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save the Item.", ButtonType.OK).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        newBtn.fire();
    }

    @FXML
    public void searchBoxTF_keyReleased(KeyEvent event) {
        if (!searchBoxTF.getText().isEmpty()) {
            try {
                ArrayList<Item> items = itemController.searchItem(searchBoxTF.getText());

                if (items.isEmpty()) {
                    searchBoxTF.setStyle("-fx-text-fill: #D91022");
                    loadAllItems();
                } else {
                    searchBoxTF.setStyle("-fx-text-fill: #000000");
                    recep_itemManagementTM = itemManagementTbl.getItems();
                    recep_itemManagementTM.removeAll(recep_itemManagementTM);

                    for (Item item : items) {
                        recep_itemManagementTM.add(new Recep_ItemManagementTM(
                                item.getItemCode(),
                                item.getName(),
                                item.getSize(),
                                Double.toString(item.getPrice())
                        ));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadAllItems();
        }
    }

    @FXML
    public void searchBox_onMouseClick(MouseEvent event) {
        searchBoxTF.selectAll();
    }

    @FXML
    public void updateBtn_onAction(ActionEvent event) {
        Item item = new Item(
                itemCodeTF.getText(),
                nameTF.getText(),
                descriptionTA.getText(),
                sizeComBox.getValue().toString(),
                Double.parseDouble(priceTF.getText()),
                otherDetailsTA.getText()
        );

        try {
            boolean result = itemController.updateItem(this.toString(), item);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Item has been Updated Successfully.", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update the Item.", ButtonType.OK).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        newBtn.fire();
    }

    @FXML
    public void sizeComBox_onAction(ActionEvent actionEvent) {
        priceTF.requestFocus();
    }

    public void informTableUpdate(Item item, String status) {
        Platform.runLater(() -> {
            try {
                Alert tableUpdateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                tableUpdateAlert.setTitle("Reload?");
                tableUpdateAlert.setHeaderText(null);
                tableUpdateAlert.setContentText("The Item '" + item.getItemCode() + "' has been " + status + " by another Client. \n Do you want to reaload your item table right now?");
                Optional<ButtonType> action = tableUpdateAlert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    loadAllItems();
                    generateItemCode();
                } else if (action.get() == ButtonType.CANCEL) {
                    tableUpdateAlert.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
