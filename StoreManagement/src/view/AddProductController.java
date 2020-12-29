package src.view;

import src.model.Product;
import src.service.ProductService;
import src.utils.Util;
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

public class AddProductController implements Initializable {
    private ProductController productController;
    @FXML
    public TextField txtProductId;
    @FXML
    public Pane panelAddProduct;
    @FXML
    public TextField txtProductName;
    @FXML
    public TextField txtProductUnit;
    @FXML
    public TextField txtProductDetail;
    @FXML
    public TextField txtProductPrice;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnHuy;
    @FXML
    public Button btnChonAnh;
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
        panelAddProduct.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelAddProduct.setOnMouseDragged(mouseEvent -> {
            panelAddProduct.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelAddProduct.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });

        txtProductId.setText(Util.generateID(Util.PREFIX_CODE.SP));
        txtProductId.setDisable(true);
    }

    public void setProductController(ProductController product) {
        this.productController= product;
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
        int soLuong;
        if(txtProductId.getText().trim().equals("") ||
                txtProductName.getText().trim().equals("") ||
                txtProductPrice.getText().toString().trim().equals("") ||
                txtProductDetail.getText().trim().equals("")
        ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Vui lòng nhập đầy đủ dữ liệu!");
            alert.showAndWait();
            return;
        }
        if (true) {
            String productId = txtProductId.getText();
            String productName = txtProductName.getText();
            String productDetail = txtProductDetail.getText();
            int unit = Integer.parseInt(txtProductUnit.getText());
            int price = Integer.parseInt(txtProductPrice.getText());

            Product product = new Product(productId, productName, unit, productDetail, price);

            ProductService.getInstance().addProduct(product);
            productController.refreshTable();
            txtProductId.setText(Util.generateID(Util.PREFIX_CODE.SP));
            txtProductDetail.setText("");
            txtProductId.setText("");
            txtProductName.setText("");
            txtProductPrice.setText("");
            txtProductUnit.setText("");

            Util.showSuccess("Quản lý sản phẩm", "Thêm sản phẩm thành công!");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.showAndWait();
        }
    }
}

