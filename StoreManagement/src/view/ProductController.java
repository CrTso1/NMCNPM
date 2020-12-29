package src.view;

import com.mysql.cj.result.ValueFactory;
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

import src.model.Product;
import src.service.ProductService;

public class ProductController implements Initializable {
    //region khai báo biến controls
    @FXML
    public TableView<Product> tableProduct;
    @FXML
    public TableColumn<Product, String> colProductID;
    @FXML
    public TableColumn<Product, String> colProductName;
    @FXML
    public TableColumn<Product, Integer> colProductQuantity;
    @FXML
    public TableColumn<Product, String> colProductDetail;
    @FXML
    public TableColumn<Product, Integer> colProductPrice;
    @FXML
    public TextField txtProductName;
    @FXML
    public TextField txtProductId;
    @FXML
    public TextField txtProductQuantity;
    @FXML
    public TextField txtProductDetail;
    @FXML
    public TextField txtProductPrice;
    @FXML
    public Button btnAddProduct;
    @FXML
    public Button btnDeleteProduct;
    @FXML
    public Button btnEditProduct;
    @FXML
    public TextField textSearch;
    @FXML
    public AnchorPane panelSach;
    @FXML
    public ImageView imgAnhBia;
    //endregion

    //region controller
    private ObservableList<Product> listProduct;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();

        //check permission
        if(true) {
            btnAddProduct.setVisible(false);
            btnDeleteProduct.setVisible(false);
            btnEditProduct.setVisible(false);
        }

        txtProductId.setEditable(false);
        txtProductName.setEditable(false);
        txtProductPrice.setEditable(false);
        txtProductPrice.setEditable(false);
        txtProductQuantity.setEditable(false);

        textSearch.textProperty().addListener((observableValue, s, t1) -> {
            listProduct.clear();
            listProduct.addAll(ProductService.getInstance().getProductByID(Integer.parseInt(t1)));
        });
    }

    private void setCell() {
         colProductID.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
         colProductDetail.setCellValueFactory(new PropertyValueFactory<Product, String>("productDetail"));
         colProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productPrice"));
         colProductQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
    }

    private void loadData() {
        listProduct = FXCollections.observableArrayList(ProductService.getInstance().getAllProduct());
        tableProduct.setItems(listProduct);
    }

    public void refreshTable() {
        listProduct.clear();
        loadData();
        clearInput();
    }

    public void clearInput() {
        txtProductQuantity.setText("");
        txtProductPrice.setText("");
        txtProductName.setText("");
        txtProductId.setText("");
        txtProductDetail.setText("");
    }
    public void bindingData() {
        Product temp = tableProduct.getSelectionModel().getSelectedItem();
        txtProductId.setText(temp.getProductId());
        txtProductDetail.setText(temp.getProductDetail());
        txtProductName.setText(temp.getProductName());
        txtProductPrice.setText(""+temp.getProductPrice());
        txtProductQuantity.setText(""+temp.getProductQuantity());
    }

    public void btnAddProduct_Click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AddProductDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            //
            AddProductController addProductController = loader.getController();
            addProductController.setProductController(this);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEditProduct_Click(ActionEvent event) {
        if (tableProduct.getSelectionModel().getSelectedIndex() >= 0) {
            Product temp = tableProduct.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/EditProductDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                //
                EditProductController editProductController = loader.getController();
                editProductController.setProductController(this);
                editProductController.setEditProduct(temp);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn sản phẩm cần chỉnh sửa!");
            alert.showAndWait();
        }
    }

    public void btnDeleteProduct_Click(ActionEvent event) {

        if (tableProduct.getSelectionModel().getSelectedIndex() >= 0) {
            Product temp = tableProduct.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa sách");
            alert.setHeaderText("Bạn muốn xóa sản phẩm này ra khỏi danh sách?");
            alert.setContentText("[" + temp.getProductId() + "] " + temp.getProductName());

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                ProductService.getInstance().removeProduct(temp.getProductId());
                Util.showSuccess("Quản lý sản phẩm", "Xóa sản phẩm thành công!");
                refreshTable();
            } else if (option.get() == ButtonType.CANCEL) {
            } else {
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Chưa chọn sản phẩm cần xóa!");
            alert.showAndWait();
        }

    }
}
