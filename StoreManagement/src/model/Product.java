package src.model;


public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
    private String productUnit;
    private String productDetail;
    private int productPrice;
    
    public Product(){

    }

    public Product(String productId, String productName, int productQuantity,  String productDetail, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;

        this.productDetail = productDetail;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}

