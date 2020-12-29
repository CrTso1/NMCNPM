package src.view;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.model.Product;
import src.service.ProductService;
import src.service.CustomerService;
import src.model.Customer;

public class BanHangController implements Initializable{
    @FXML
    private TextField searchProduct;

    @FXML
    private Button decrButt;

    @FXML
    private Button findCusButt;

    @FXML
    private TextField totalText;

    @FXML
    private TableView<Product> billList;

    @FXML
    private Button searchProductButt;

    @FXML
    private TableColumn<Product, String> nameProdColumn;

    @FXML
    private TextField discountText;

    @FXML
    private TableColumn<Product, String> idProductColumn;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField productNameText;

    @FXML
    private TableColumn<Product, Integer> priceListCol;

    @FXML
    private Button thanhToanButt;

    @FXML
    private TextField cusNameText;

    @FXML
    private Button addButt;

    @FXML
    private Button increButt;

    @FXML
    private TableColumn<Product, String> bilNameCol;

    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @FXML
    private Button deleteButt;

    @FXML
    private TextField quantityText;

    @FXML
    private TextField pointText;

    @FXML
    private TableView<Product> productList;

    @FXML
    private Button selectProdButton;

    private ObservableList<Product> listProduct;
    private ObservableList<Product> billProdList;
    private Map<String, Product> mapProduct = new HashMap<String, Product>();

    private Customer customer;


    private int totalPrice = 0;
    private int currentPrice = 0;
    private float currentDiscount;

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCell();
        loadData();
        searchProduct.textProperty().addListener((observableValue, s, t1) -> {
            listProduct.clear();
            //TO-DO Tìm kiếm theo ID  listProduct.addAll(ProductService.getInstance().search(t1));
        });

        bilNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));
        billList.setItems(billProdList);
    }

    public void selectProduct (ActionEvent e) {
        Product selected = productList.getSelectionModel().getSelectedItem();
        productNameText.setText(selected.getProductName());
        productNameText.setDisable(true);
        quantityText.setText("1");
        currentPrice = selected.getProductPrice();
    }

    public void increase (ActionEvent e) {
        int quantity = Integer.parseInt(quantityText.getText());
        quantity ++;
        quantityText.setText("" + quantity);
    }

    public void decrease (ActionEvent e) {
        int quantity = Integer.parseInt(quantityText.getText());
        if(quantity > 1) {
            quantity++;
            quantityText.setText("" + quantity);
        }
    }

    public void addProducttoBill (ActionEvent e) {
        Product newProduct = new Product();
        String name = productNameText.getText();
        newProduct.setProductName(name);
        int quantity = Integer.parseInt(quantityText.getText());
        newProduct.setProductQuantity(quantity);
        newProduct.setProductPrice(currentPrice);

        for(Product prod : billProdList){
            if(prod.getProductName().equals(name)){
                int newQuantity = prod.getProductQuantity() + quantity;
                prod.setProductQuantity(newQuantity);
                totalPrice += currentPrice*quantity;
                totalPrice *= currentDiscount;
                totalText.setText("" + totalPrice);
                return;
            }
        }
        billProdList.add(newProduct);

        mapProduct.put(newProduct.getProductName(), newProduct);

        totalPrice += currentPrice*quantity;
        totalPrice *= currentDiscount;
        totalText.setText("" + totalPrice);
        currentPrice = 0;
    }

    //delete Item Bill
    public void deleteItem (ActionEvent e) {
        Product selected = billList.getSelectionModel().getSelectedItem();
        String name = selected.getProductName();
        for (Map.Entry<String, Product> entry : mapProduct.entrySet()){
            if(entry.getKey().equals(name)){
                Product gonnaDelete = entry.getValue();
                int value = gonnaDelete.getProductPrice();
                int quantity = gonnaDelete.getProductQuantity();
                totalPrice -= value*quantity;
                totalPrice *= currentDiscount;
                mapProduct.remove(name);
                break;
            }
        }
        totalText.setText("" + totalPrice);
        billProdList.remove(selected);
    }

    private void setCell(){
//        idProductColumn.setCellValueFactory(cellData -> cellData.getValue().getProductId();
//        nameProdColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty);
//        priceListCol.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty);
        idProductColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        nameProdColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceListCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productPrice"));
    }
    private void loadData(){
        listProduct = FXCollections.observableArrayList(ProductService.getInstance().getAllProduct());
        productList.setItems(listProduct);
    }
    public void findCusButt_Click(ActionEvent event){
        //TO-DO viết hàm get Khách hàng by ID
        Customer cus = CustomerService.getInstance().getCustomersByPhone(phoneText.getText());
        if (cus != null) {
            customer = cus;
            cusNameText.setText(cus.getName());
            pointText.setText("" + cus.getPoint());
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("./AddCustomertoBillView.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                // set controller
                /*AddCustomerController addCustomerController = loader.getController();
                addCustomerController.setCustomerController(this);*/
                dialogStage.showAndWait();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
