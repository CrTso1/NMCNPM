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
import src.model.Bill;
import src.model.Customer;
import src.model.Employee;
import src.model.Product;


public class BillDAO {
    private static BillDAO instance;

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }
//    public List<Bill> getAllBills() {
//        List<Bill> listBill = new ArrayList<>();
//        try {
//
//            String sql = "SELECT * FROM Bill ";
//            Connection connection = getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Bill b = new Bill();
//                b.setIDOrder(rs.getInt("IDOrder"));
//                b.setDate(rs.getString("Ngay"));
//                b.setTotal(rs.getInt("Total_price"));
//                listBill.add(b);
//            }
//
//            return listBill;
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }

    public List<Bill> getAllBills() {
        List<Bill> listBill = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection()) {
            String query = null;
            query = "select * from bill";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Bill b = new Bill();
                b.setIDOrder(resultSet.getString("IDOrder"));
                b.setDate(resultSet.getString("Ngay"));
                b.setTotal(resultSet.getInt("Total_price"));
                listBill.add(b);

                listBill.add(b);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBill;
    }

    public Bill getBillByID(int IDOrder) {
        Bill b = new Bill();
        try {

            String sql = "SELECT * FROM Bill WHERE IDOrder = ? ";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, IDOrder);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                b.setIDOrder(rs.getString("IDOrder"));
                b.setDate(rs.getString("Ngay"));
                b.setTotal(rs.getInt("Total_price"));

            }

            return b;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }
    public void addBill(Bill b) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO Bill (IDOrder, DoanhThu, Ngay)"
                    + " VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, b.getIDOrder());
            ps.setInt(2, b.getTotal());
            ps.setString(3, b.getDate());

            int rs = ps.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void removeBill(int IDOrder){
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Orderoop WHERE IDOrder = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, IDOrder);
            int rs = ps.executeUpdate();

            String sql1 = "DELETE FROM Bill WHERE IDOrder = ? ";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, IDOrder);
            int rs1 = ps1.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeAllBills(){
        try {
            Connection connection = getConnection();

            String sql = "DELETE FROM Order";
            PreparedStatement ps = connection.prepareStatement(sql);
            int rs = ps.executeUpdate();

            String sql1 = "DELETE FROM Bill";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            int rs1 = ps1.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Bill> getBillByDate(String date) {
        List<Bill> listBill = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Bill WHERE Ngay like '%" +date +"%'";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill b = new Bill();
                b.setIDOrder(rs.getString("IDOrder"));
                b.setDate(rs.getString("Ngay"));
                b.setTotal(rs.getInt("Total_price"));
                listBill.add(b);
            }

            return listBill;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listBill;
    }

}
