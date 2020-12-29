package src.view;

import src.model.Product;
import src.service.CustomerService;
import src.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import src.model.Customer;

public class CustomerController implements Initializable {
    //region khai báo biến controls
    @FXML
    public TableView<Customer> tableCustomer;
    @FXML
    public TableColumn<Customer, String> colCustomerID;
    @FXML
    public TableColumn<Customer, String> colCustomerName;
    @FXML
    public TableColumn<Customer, String> colCustomerPhone;
    @FXML
    public TableColumn<Customer, Integer> colCustomerPoint;
    @FXML
    public TableColumn<Customer, String> colCustomerIsVip;
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
    public Button btnAddCustomer;
    @FXML
    public Button btnDeleteCustomer;
    @FXML
    public Button btnEditCustomer;
    @FXML
    public TextField textSearch;
    @FXML
    public ImageView imgAnhBia;
    //endregion

    //region controller
    private ObservableList<Customer> listCustomer;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();

        //check permission
        if(true) {
            btnAddCustomer.setVisible(false);
            btnDeleteCustomer.setVisible(false);
            btnEditCustomer.setVisible(false);
        }

        txtCustomerId.setEditable(false);
        txtCustomerIsVip.setEditable(false);
        txtCustomerName.setEditable(false);
        txtCustomerPhone.setEditable(false);
        txtCustomerPoint.setEditable(false);

        textSearch.textProperty().addListener((observableValue, s, t1) -> {
            listCustomer.clear();
            listCustomer.addAll(CustomerService.getInstance().getCustomersByID(Integer.parseInt(t1)));
        });
    }

    private void setCell() {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, String>("ID"));
        colCustomerIsVip.setCellValueFactory(new PropertyValueFactory<Customer, String>("isVIP"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        colCustomerPoint.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Point"));
    }

    private void loadData() {
        listCustomer = FXCollections.observableArrayList(CustomerService.getInstance().getAllCustomers());
        tableCustomer.setItems(listCustomer);
    }

    public void refreshTable() {
        listCustomer.clear();
        loadData();
        clearInput();
    }

    public void clearInput() {
        txtCustomerPoint.setText("");
        txtCustomerPhone.setText("");
        txtCustomerName.setText("");
        txtCustomerIsVip.setText("");
        txtCustomerId.setText("");
    }
    public void bindingData() {
        Customer temp = tableCustomer.getSelectionModel().getSelectedItem();
        txtCustomerId.setText(temp.getID());
        txtCustomerIsVip.setText(""+temp.isVIP());
        txtCustomerName.setText(temp.getName());
        txtCustomerPoint.setText(""+temp.getPoint());
        txtCustomerPhone.setText(""+temp.getPhone());
    }

    public void btnAddCustomer_Click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddCustomerDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //
            AddCustomerController addCustomerController = loader.getController();
            addCustomerController.setCustomerController(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEditCustomer_Click(ActionEvent event) {
        if (tableCustomer.getSelectionModel().getSelectedIndex() >= 0) {
            Customer temp = tableCustomer.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/EditCustomerDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                //
                EditCustomerController editCustomerController = loader.getController();
                editCustomerController.setCustomerController(this);
                editCustomerController.setEditCustomer(temp);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn khách hàng cần chỉnh sửa!");
            alert.showAndWait();
        }
    }

    public void btnDeleteCustomer_Click(ActionEvent event) {

        if (tableCustomer.getSelectionModel().getSelectedIndex() >= 0) {
            Customer temp = tableCustomer.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa sách");
            alert.setHeaderText("Bạn muốn xóa khách hàng này ra khỏi danh sách?");
            alert.setContentText("[" + temp.getID() + "] " + temp.getName());

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                CustomerService.getInstance().removeCustomer(temp.getID());
                Util.showSuccess("Quản lý khách hàng", "Xóa khách hàng thành công!");
                refreshTable();
            } else if (option.get() == ButtonType.CANCEL) {
            } else {
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn khach hang cần xóa!");
            alert.showAndWait();
        }

    }
}
