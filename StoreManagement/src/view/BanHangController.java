package src.view;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import src.model.Product;
import src.service.ProductService;
import src.service.CustomerService;
import src.model.Customer;
import src.model.Bill;
import src.service.BillService;
import src.utils.Util;

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

    @FXML
    private Button refreshBtn;

    private ObservableList<Product> listProduct;
    private ObservableList<Product> copyList;
    ObservableList<Product> billProdList = FXCollections.observableArrayList();
    private Map<String, Product> mapProduct = new HashMap<String, Product>();

    private Customer customer;
    private Product product;

    private boolean isNewCus = true;
    private int totalPrice = 0;
    private int currentPrice = 0;
    private float currentDiscount;
    private String curentID;

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCell();
        loadData();
        searchProduct.textProperty().addListener((observableValue, s, t1) -> {
//            ObservableList<Product> copyList;
//            for(Product prod : listProduct){
//                copyList.add(prod);
//            }
            Product findProd = ProductService.getInstance().getProductByID(t1);
            if(findProd != null) {
                listProduct.clear();
                listProduct.addAll(ProductService.getInstance().getProductByID(t1));
            }
            else{
                listProduct.clear();
                listProduct = FXCollections.observableArrayList(ProductService.getInstance().getAllProduct());
                for(Product prod : listProduct){
                    System.out.println(prod.getProductName());
                }
                productList.refresh();
                productList.setItems(listProduct);
            }

//            }
//            else productList.setItems(listProduct);
//            listProduct.clear();
////            listProduct.addAll(ProductService.getInstance().getProductByID(Integer.parseInt(t1)));
//            listProduct.addAll(ProductService.getInstance().getProductByID(t1));
//            productList.refresh();
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
        curentID = selected.getProductId();
    }

    public void refreshProductList (ActionEvent e) {
        listProduct.clear();
        listProduct = FXCollections.observableArrayList(ProductService.getInstance().getAllProduct());
        for(Product prod : listProduct){
            System.out.println(prod.getProductName());
        }
        productList.refresh();
        productList.setItems(listProduct);
    }

    public void increase (ActionEvent e) {
        int quantity = Integer.parseInt(quantityText.getText());
        quantity ++;
        quantityText.setText("" + quantity);
    }

    public void decrease (ActionEvent e) {
        int quantity = Integer.parseInt(quantityText.getText());
        if(quantity > 1) {
            quantity--;
            quantityText.setText("" + quantity);
        }
    }

    public void addProducttoBill (ActionEvent e) {
        Product newProduct = new Product();
        String name = productNameText.getText();
        System.out.println(name);
        newProduct.setProductName(name);
        newProduct.setProductId(curentID);
        int quantity = Integer.parseInt(quantityText.getText());
        System.out.println(quantity);
        newProduct.setProductQuantity(quantity);
        newProduct.setProductPrice(currentPrice);
        // nếu trong giỏ hàng đã có sản phẩm, duyệt lấy tổng sản phẩm
        if (billProdList != null) {
            for (Product prod : billProdList) {
                if (prod.getProductName().equals(name)) {
                    System.out.println("Tim thay");
                        int newQuantity = prod.getProductQuantity() + quantity;
                        prod.setProductQuantity(newQuantity);
                        totalPrice += currentPrice * quantity;
//                        totalPrice *= currentDiscount;
                        totalText.setText("" + totalPrice);
                        billList.refresh();
                        return;
                }
            }
        }
        System.out.println(currentPrice);

        billProdList.add(newProduct);
        if (billProdList == null)
            System.out.printf("Bill đang null");
        mapProduct.put(newProduct.getProductName(), newProduct);

        totalPrice += currentPrice*quantity;
        //totalPrice *= currentDiscount;
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
        if (cus == null){
            try {
                System.out.println("cus null");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/AddCustomertoBillView.fxml"));
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
        } else  {
            isNewCus = false;
            System.out.println("cus k null");
            customer = cus;
            cusNameText.setText(cus.getName());
            pointText.setText("" + cus.getPoint());
        }
    }

    public void thanhToan (ActionEvent event){
        if(isNewCus) CustomerService.getInstance().addCustomer(customer);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Bill newBill = new Bill(Util.generateID(Util.PREFIX_CODE.BI), totalPrice, formatter.format(date));
        BillService.getInstance().addBill(newBill);

        for(Product myProduct : billProdList){
            System.out.println(myProduct.getProductName());
            int myQuantity = myProduct.getProductQuantity();
            Product originProd = ProductService.getInstance().getProductByID(myProduct.getProductId());
            int originQuantity = originProd.getProductQuantity();
            originQuantity -= myQuantity;
            originProd.setProductQuantity(originQuantity);
            if(originQuantity == 0) ProductService.getInstance().removeProduct(originProd.getProductId());
            else ProductService.getInstance().updateProduct(originProd);
        }//


    }

}
