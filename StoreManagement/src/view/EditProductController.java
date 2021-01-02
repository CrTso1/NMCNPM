package src.view;

// import model va service
import src.service.ProductService;
import src.utils.Util;
import com.jfoenix.controls.JFXButton;

//import controller.ProductController;
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

import src.model.Product;

public class EditProductController implements Initializable {
    private ProductController productController;
    private Product product;
    @FXML
    public TextField txtProductDetail;
    @FXML
    public Pane panelEditProduct;
    @FXML
    public TextField txtProductId;
    @FXML
    public TextField txtProductPrice;
    @FXML
    public TextField txtProductName;
    @FXML
    public TextField txtProductQuantity;

    @FXML
    public RadioButton rdbTrong;
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
        panelEditProduct.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelEditProduct.setOnMouseDragged(mouseEvent -> {
            panelEditProduct.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelEditProduct.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });

    }
    public Product getEditProduct(){
        return this.product;
    }

    public void setProductController(ProductController product) {
        this.productController = product;
    }

    public void setEditProduct(Product product) {
        this.product = product;
        bindingData();
    }
    private void bindingData() {
        txtProductId.setText(String.valueOf(product.getProductId()));
        txtProductName.setText(product.getProductName());
        txtProductQuantity.setText("" + product.getProductQuantity());
        txtProductDetail.setText(product.getProductDetail());
        txtProductPrice.setText(""+product.getProductPrice());

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
        if(txtProductName.getText().trim().equals("") ||
                txtProductPrice.getText().trim().equals("") ||
                txtProductQuantity.getText().toString().trim().equals("") ||
                txtProductDetail.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        //

//        String productID = txtProductId.getText();
//        String productName = txtProductName.getText();
//        String productQuantity = txtProductQuantity.getText();
//        String productUnit = txtProductPrice.getText();
//        String productDetail = txtProductName.getText();
//        String productPrice = txtProductPrice.getText();
//
//        Product product = new Product(productID, productName, Integer.parseInt(productQuantity), productUnit, productDetail, Integer.parseInt(productPrice));
        String productId = txtProductId.getText();
        String productName = txtProductName.getText();
        String productDetail = txtProductDetail.getText();
        int unit = Integer.parseInt(txtProductQuantity.getText());
        int price = Integer.parseInt(txtProductPrice.getText());

        Product product = new Product(productId, productName, unit, productDetail, price);

        ProductService.getInstance().updateProduct(product);
        Util.showSuccess("Quản lý sản phẩm", "Sửa sản phẩm thành công!");
        productController.refreshTable();
        txtProductId.setText("");
        txtProductName.setText("");
        txtProductQuantity.setText("");
        txtProductPrice.setText("");
        txtProductDetail.setText("");
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

}
