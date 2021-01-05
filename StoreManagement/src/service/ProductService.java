package src.service;
import src.dao.ProductDao;
import src.model.Product;

import java.util.List;

public class ProductService {
//    private ProductDao productDao = new ProductDao();

    private static ProductService instance;

    public static ProductService getInstance(){
        if (instance == null){
            instance = new ProductService();
        }
        return instance;
    }
    public List<Product> getAllProduct(){
        return ProductDao.getInstance().getAllProduct();
    }

    public List<Product> getProductByName(String nameGoods){
        return ProductDao.getInstance().getProductByName(nameGoods);
    }
    public  void removeProduct(String IDProduct){
        ProductDao.getInstance().removeProduct(IDProduct);
    }

    public  void addProduct(Product product){
        ProductDao.getInstance().addProduct(product);
    }

    public Product getProductByID(String IDProduct){
        return ProductDao.getInstance().getProductByID(IDProduct);
    }

    public void updateProduct(Product p) {
        ProductDao.getInstance().updateProduct(p);
    }
}
