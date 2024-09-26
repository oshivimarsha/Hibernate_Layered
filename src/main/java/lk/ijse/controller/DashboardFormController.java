package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane rootNode;
    public JFXButton btnCustomer;
    public JFXButton btnItem;
    public JFXButton btnOrder;

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("../customer_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Form");
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/item_form.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Item Form");
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/customer_form.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order Form");
    }
}
