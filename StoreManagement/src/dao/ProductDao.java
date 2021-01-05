package src.dao;
import static src.dao.JDBCConnection.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.model.Employee;
import src.model.Product;

public class ProductDao {
    private static ProductDao instance;

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }
//    public List<Product> getAllProduct() {
//        List<Product> listProduct = new ArrayList<>();
//        try {
//
//            String sql = "SELECT * FROM Product ";
//            Connection connection = getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Product d = new Product();
//                d.setProductId(rs.getString("id"));
//                d.setProductName(rs.getString("name"));
//                d.setProductQuantity(rs.getInt("quantity"));
//                d.setProductPrice(rs.getInt("price"));
//                d.setProductDetail(rs.getString("product_detail"));
//                listProduct.add(d);
//            }
//
//            return listProduct;
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
    public List<Product> getAllProduct() {
        List<Product> listProduct = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection()) {
            String query = null;
            query = "select * from product";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setProductName(resultSet.getString(2));
                product.setProductDetail(resultSet.getString(3));
                product.setProductPrice(Integer.parseInt(resultSet.getString(4)));
                product.setProductQuantity(Integer.parseInt(resultSet.getString(5)));

                listProduct.add(product);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listProduct;
    }

    public Product getProductByID(int ID) {
            Product d = new Product();
        try {
            String sql = "SELECT * FROM Product Where id =?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                d.setProductId(rs.getString("id"));
                d.setProductName(rs.getString("name"));
                d.setProductQuantity(rs.getInt("quantity"));
                d.setProductPrice(rs.getInt("price"));
                d.setProductDetail(rs.getString("product_detail"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return d;
    }

    public List<Product> getProductByName(String nameProduct) {
        List<Product> listProduct = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Product WHERE name like N'%"+nameProduct+"%'";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product d = new Product();
                d.setProductId(rs.getString("id"));
                d.setProductName(rs.getString("name"));
                d.setProductQuantity(rs.getInt("quantity"));
                d.setProductPrice(rs.getInt("price"));
                d.setProductDetail(rs.getString("product_detail"));
                listProduct.add(d);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listProduct;
    }


    public void addProduct(Product d) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO Product (id,name,price, quantity, product_detail)"
                    + " VALUES (?,?,?, ?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getProductId());
            ps.setString(2, d.getProductName());
            ps.setInt(3, d.getProductPrice());
            ps.setInt(4, d.getProductQuantity());
            ps.setString(5, d.getProductDetail());

            int rs = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateProduct(Product d) {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE Product SET name =? ,price  =?, quantity =?, product_detail =?"
                    +" WHERE id =?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(5, d.getProductId());
            ps.setString(1, d.getProductName());
            ps.setInt(2, d.getProductPrice());
            ps.setInt(3, d.getProductQuantity());
            ps.setString(4, d.getProductDetail());

            int rs = ps.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeProduct(String IDProduct){
        try {
            Connection connection = getConnection();
            String sql1 = "DELETE FROM Product WHERE id = ? ";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, IDProduct);
            int rs1 = ps1.executeUpdate();

//            String sql = "DELETE FROM Employeeoop WHERE IDEmployee = ? ";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, IDGoods);
//            int rs = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
