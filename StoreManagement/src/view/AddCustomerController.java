package src.view;

import src.utils.Util;
import src.model.Product;
import src.service.ProductService;
import src.model.Customer;

import src.service.CustomerService;

import src.utils.*;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    private CustomerController customerController;
    @FXML
    public Pane panelAddCustomer;
    @FXML
    public TextField txtCustomerId;
    @FXML
    public TextField txtCustomerName;
    @FXML
    public TextField txtCustomerPhone;
    @FXML
    public TextField txtCustomerPoint;
    @FXML
    public TextField txtCustomerIsVip;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnHuy;
    @FXML
    public ImageView imgPreview;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    private double mousepX = 0;
    private double mousepY = 0;
    File anhBia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panelAddCustomer.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelAddCustomer.setOnMouseDragged(mouseEvent -> {
            panelAddCustomer.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelAddCustomer.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });

        txtCustomerId.setText(Util.generateID(Util.PREFIX_CODE.KH));
        txtCustomerId.setDisable(true);
    }

    public void setCustomerController(CustomerController customer) {
        this.customerController= customer;
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
    }

    public void btnCloseMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 15");
        iconClose.setVisible(false);
    }

    public void btnThem_Click(ActionEvent event) throws FileNotFoundException {
        //VALIDATE
        if(txtCustomerName.getText().trim().equals("") ||
                txtCustomerIsVip.getText().trim().equals("") ||
                txtCustomerPoint.getText().toString().trim().equals("") ||
                txtCustomerPhone.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        if (true) {
            String customerId = txtCustomerId.getText();
            String customerName = txtCustomerName.getText();
            String customerIsVip = txtCustomerIsVip.getText();
            String customerPhone = txtCustomerPhone.getText();
            String customerPoint = txtCustomerPoint.getText();
            Customer customer = new Customer(customerId, customerName,"","", customerPhone,  true, Integer.parseInt(customerPoint));

            CustomerService.getInstance().addCustomer(customer);
            customerController.refreshTable();
            txtCustomerId.setText(Util.generateID(Util.PREFIX_CODE.KH));
            txtCustomerIsVip.setText("");
            txtCustomerName.setText("");
            txtCustomerPhone.setText("");
            txtCustomerPoint.setText("");


            Util.showSuccess("Quản lý khách hàng", "Thêm khách hàng thành công!");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.showAndWait();
        }
    }
}

