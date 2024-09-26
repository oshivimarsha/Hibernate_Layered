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
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.tm.CustomerTm;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    public AnchorPane rootNode;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerName;
    public TextField txtCustomerTel;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public TableView<CustomerTm> tblCustomerCart;
    public TableColumn<?, ?> colCustomerId;
    public TableColumn<?, ?> colCustomerName;
    public TableColumn<?, ?> colCustomerAddress;
    public TableColumn<?, ?> colCustomerTel;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMERBO);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentId();
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = customerBO.generateNewID();
            txtCustomerId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }


    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel()
                );

                obList.add(tm);
            }

            tblCustomerCart.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerTel.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String tel = txtCustomerTel.getText();

            CustomerDTO customer = new CustomerDTO(id, name, address, tel);

            try {
                boolean isSaved = customerBO.saveCustomer(customer);//CustomerRepo.save(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String tel = txtCustomerTel.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, tel);

        boolean isUpdated = false;
        try {
            isUpdated = customerBO.updateCustomer(customer);//CustomerRepo.update(customer);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String nic = txtCustomerId.getText();
        System.out.println(txtCustomerId.getText());
        try {
            boolean isDeleted = false;

            isDeleted = customerBO.deleteCustomer(nic);//CustomerRepo.delete(nic);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();
                initialize();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerAddressOnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerNameOnAction(ActionEvent actionEvent) {

    }

    public void txtCustomerTelOnAction(ActionEvent actionEvent) {

    }
}
