package src.view;

// import model va service
import src.service.CustomerService;
import src.utils.*;
import com.jfoenix.controls.JFXButton;

//import Controller.CustomerController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import src.model.Customer;

public class EditCustomerController implements Initializable {
    private CustomerController customerController;
    private Customer customer;
    @FXML
    public Pane panelEditCustomer;
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
    public Button btnLuu;
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
        panelEditCustomer.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelEditCustomer.setOnMouseDragged(mouseEvent -> {
            panelEditCustomer.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelEditCustomer.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });
        txtCustomerId.setDisable(true);


    }

    public void setCustomerController(CustomerController customer) {
        this.customerController = customer;
    }

    public void setEditCustomer(Customer customer) {
        this.customer = customer;
        bindingData();
    }
    private void bindingData() {
       // txtCustomerId.setText();
        txtCustomerIsVip.setText("" + customer.isVIP());
        txtCustomerName.setText(customer.getName());
        txtCustomerPoint.setText("" + customer.getPoint());
        txtCustomerPhone.setText(customer.getPhone());

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

    public void btnLuu_Click(ActionEvent event) throws FileNotFoundException {
        //VALIDATE
        if(txtCustomerIsVip.getText().trim().equals("") ||
                txtCustomerName.getText().trim().equals("") ||
                txtCustomerPhone.getText().toString().trim().equals("") ||
                txtCustomerPoint.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        //

        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String customerIsVip = txtCustomerIsVip.getText();
        String customerPoint = txtCustomerPoint.getText();
        String customerPhone = txtCustomerPhone.getText();

        Customer customer = new Customer(customerId, customerName,"","", customerPhone,  true, Integer.parseInt(customerPoint));

        CustomerService.getInstance().updateCustomer(customer);
        Util.showSuccess("Quản lý khách hàng", "Sửa khách hàng thành công!");
        customerController.refreshTable();
        txtCustomerId.setText("");
        txtCustomerIsVip.setText("");
        txtCustomerName.setText("");
        txtCustomerPhone.setText("");
        txtCustomerPoint.setText("");
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

}
