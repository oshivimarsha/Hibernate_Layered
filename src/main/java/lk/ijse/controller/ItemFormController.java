package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.tm.CustomerTm;
import lk.ijse.entity.tm.ItemTm;

import java.sql.SQLException;
import java.util.List;

public class ItemFormController {
    public AnchorPane rootNode;
    public TextField txtItemId;
    public TextField txtItemName;
    public TextField txtQtyInStock;
    public TextField txtUnitPrice;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public TableView<ItemTm> tblItemCart;
    public TableColumn<?,?> colItemId;
    public TableColumn<?,?> colItemName;
    public TableColumn<?,?> colQty;
    public TableColumn<?,?> colUnitPrice;

    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEMBO);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    //    getCurrentId();
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = itemBO.generateNewID();
            txtItemId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    private void loadAllCustomers() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemList = itemBO.getAllItems();
            for (ItemDTO item : itemList) {
                ItemTm tm = new ItemTm(
                        item.getId(),
                        item.getItemName(),
                        item.getQtyInStock(),
                        item.getUnitPrice()
                );

                obList.add(tm);
            }

            tblItemCart.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyInStock"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void clearFields() {
        txtItemId.setText("");
        txtItemName.setText("");
        txtQtyInStock.setText("");
        txtUnitPrice.setText("");
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        String itemName = txtItemName.getText();
        String qtyInStock = txtQtyInStock.getText();
        String unitPrice = txtUnitPrice.getText();

        ItemDTO itemDTO = new ItemDTO(id, itemName, qtyInStock, unitPrice);

        try {
            boolean isSaved = itemBO.saveItem(itemDTO);//CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                 clearFields();
                 initialize();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtItemId.getText();
        String itemName = txtItemName.getText();
        String qtyInStock = txtQtyInStock.getText();
        String unitPrice = txtUnitPrice.getText();

        ItemDTO item = new ItemDTO(id, itemName, qtyInStock, unitPrice);

        boolean isUpdated = false;
        try {
            isUpdated = itemBO.updateItem(item);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
             clearFields();
             initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String nic = txtItemId.getText();
        System.out.println(txtItemId.getText());
        try {
            boolean isDeleted = false;

            isDeleted = itemBO.deleteItem(nic);//CustomerRepo.delete(nic);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                 clearFields();
                 initialize();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void txtItemIdOnAction(ActionEvent actionEvent) {

    }

    public void txtQtyAddressOnAction(ActionEvent actionEvent) {

    }

    public void txtItemNameOnAction(ActionEvent actionEvent) {

    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {

    }

}
