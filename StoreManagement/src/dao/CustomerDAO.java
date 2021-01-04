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
import src.model.Customer;
import src.model.Product;

public class CustomerDAO {

	private static CustomerDAO instance;

	public static CustomerDAO getInstance() {
		if (instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	}
//	public List<Customer> getAllCustomers() {
//		List<Customer> listCustomer = new ArrayList<>();
//		try {
//
//			String sql = "SELECT * FROM Customer";
//			Connection connection = getConnection();
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ResultSet resultSet = ps.executeQuery();
//			while (resultSet.next()) {
//				Customer g = new Customer();
//				g.setID(resultSet.getString("IDCustomer"));
//				g.setName(resultSet.getString("name"));
//				g.setPhone(resultSet.getString("phone_number"));
//				g.setPoint(resultSet.getInt("Points"));
//				g.setVIP(resultSet.getBoolean("VIP"));
//				g.setAddress(resultSet.getString("address"));
//				listCustomer.add(g);
//			}
//			return listCustomer;
//		} catch (SQLException ex) {
//			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		return null;
//	}

	public List<Customer> getAllCustomers() {
		List<Customer> listCustomer = new ArrayList<>();

		try (Connection conn = JDBCConnection.getConnection()) {
			String query = null;
			query = "select * from customer";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Customer g = new Customer();
				g.setID(resultSet.getString(1));
				g.setName(resultSet.getString(2));
				g.setPhone(resultSet.getString(4));
				g.setPoint(resultSet.getInt(6));
				g.setDoB(""+resultSet.getDate(5));
				g.setAddress(resultSet.getString(3));
				listCustomer.add(g);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCustomer;
	}

	public List<Customer> getCustomersByName(String name) {
		List<Customer> listCustomers = new ArrayList<>();
		try {

			String sql = "SELECT * FROM Customer WHERE name like N'%"+name+"%' ";
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Customer g = new Customer();
				g.setID(resultSet.getString("IDCustomer"));
				g.setName(resultSet.getString("name"));
				g.setPhone(resultSet.getString("phone_number"));
				g.setPoint(resultSet.getInt("Points"));
				g.setVIP(resultSet.getBoolean("VIP"));
				g.setAddress(resultSet.getString("address"));
				listCustomers.add(g);
			}

			return listCustomers;
		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return listCustomers;
	}

	public void removeCustomer(String IDCustomer){
		//:TODO
		try {
			Connection connection = getConnection();
			String sql = "DELETE FROM Customer WHERE IDCustomer = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, IDCustomer);
			int rs = ps.executeUpdate();

			String sql1 = "DELETE FROM Customer WHERE IDCustomer = ? ";
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, IDCustomer);
			int rs1 = ps1.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void updateCustomer(Customer g) {
		try {
			Connection connection = getConnection();
			String sql = "UPDATE Customer SET name =? ,phone_number =?, Points=?, address = ? VIP = ?"
					+" WHERE IDCustomer =?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(6, g.getID());
			ps.setString(1, g.getName());
			ps.setString(2, g.getPhone());
			ps.setInt(3, g.getPoint());
			ps.setString(4, g.getAddress());
			ps.setString(5, "true");
			int rs = ps.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	public Customer getCustomersByID(int ID) {

		try {

			String sql = "SELECT * FROM Customer WHERE IDCustomer= ? " ;
			Connection connection = getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ID);
			ResultSet resultSet = ps.executeQuery();
			Customer g = new Customer();
			while (resultSet.next()) {

				g.setID(resultSet.getString("IDCustomer"));
				g.setName(resultSet.getString("name"));
				g.setPhone(resultSet.getString("phone_number"));
				g.setPoint(resultSet.getInt("Points"));
				g.setVIP(resultSet.getBoolean("VIP"));
				g.setAddress(resultSet.getString("address"));
			}

			return g;
		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}
	public Customer getCustomersByPhone(String phone) {
		Customer g = new Customer();
		try {

			String sql = "SELECT * FROM Customer WHERE phone_number =? ";
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				g.setID(resultSet.getString("IDCustomer"));
				g.setName(resultSet.getString("name"));
				g.setPhone(resultSet.getString("phone_number"));
				g.setPoint(resultSet.getInt("Points"));
				g.setVIP(resultSet.getBoolean("VIP"));
				g.setAddress(resultSet.getString("address"));
			}

			return g;
		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public void addCustomer(Customer g) {
		try {
			Connection connection = getConnection();

			String sql = "INSERT INTO Customer (IDCustomer, name, phone_number, Points, address, VIP, DoB)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, g.getID());
			ps.setString(2, g.getName());
			ps.setString(3, g.getPhone());
			ps.setInt(4, g.getPoint());
			ps.setString(5, g.getAddress());
			ps.setBoolean(6, g.isVIP());
			ps.setString(5, g.getDoB());
			int rs = ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
