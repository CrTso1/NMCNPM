package src.dao;

import static src.dao.JDBCConnection.getConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import  src.model.Employee;
import  src.model.Admin;
import src.model.Product;

public class EmployeeDAO {
    private static EmployeeDAO instance;

    public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }
    /*public List<Employee> getAllEmployees() {
        List<Employee> listEmployee = new ArrayList<>();
        try {

            String sql = "select * from employee";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Employee Employee = new Employee();
                Employee.setID(resultSet.getString(1));
                Employee.setName(resultSet.getString(2));
                Employee.setRole(resultSet.getString(6));
                Employee.setDoB(resultSet.getString(4));
                Employee.setAddress(resultSet.getString(3));
                Employee.setShift(resultSet.getString(7));
                Employee.setPhone(resultSet.getString(5));
                Employee.setSalary(resultSet.getInt(8));
                Employee.setEmployeeName(resultSet.getString(9));
                Employee.setPassWord(resultSet.getString(10));
                listEmployee.add(Employee);

            }
            return listEmployee;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }*/
    public List<Employee> getAllEmployees() {
        List<Employee> listEmployee = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection()) {
            String query = null;
            query = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Employee Employee = new Employee();
                Employee.setID(resultSet.getString(1));
                Employee.setName(resultSet.getString(2));
                Employee.setRole(resultSet.getString(6));
                Employee.setDoB(resultSet.getString(4));
                Employee.setAddress(resultSet.getString(3));
                Employee.setShift(resultSet.getString(7));
                Employee.setPhone(resultSet.getString(5));
                Employee.setSalary(resultSet.getLong(8));
                Employee.setEmployeeName(resultSet.getString(9));
                Employee.setPassWord(resultSet.getString(10));
                listEmployee.add(Employee);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listEmployee;
    }

    public List<Employee> getAllEmployeeNames() {
        List<Employee> listEmployee = new ArrayList<>();
        try {
            String sql = "SELECT EmployeeName,Password,Role FROM EMPLOYEE ";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Employee Employee = new Employee();
                Employee.setEmployeeName(resultSet.getString("EmployeeName"));
                Employee.setPassWord(resultSet.getString("Password"));
                Employee.setRole(resultSet.getString("Role"));
                listEmployee.add(Employee);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listEmployee;    }

    public void addEmployee(Employee Employee) {
        try {
            Connection connection = getConnection();

            String sql = "INSERT INTO Employee (ID,"
                    + "address,phone_number,Role,Shift,Salary,EmployeeName,Password, name)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Employee.getID());
            ps.setString(2, Employee.getAddress());
            ps.setString(3, Employee.getPhone());
            ps.setString(4, Employee.getRole());
            ps.setString(6, Employee.getShift());
            ps.setLong(5, Employee.getSalary());
            ps.setString(7, Employee.getEmployeeName());
            ps.setString(8, Employee.getPassWord());
            ps.setString(9, Employee.getName());

            int rs = ps.executeUpdate();

            // salary
//            if (Employee.getRole().equals("Employee")) {
//                String sql0 = "INSERT INTO SalaryEmployee (IDEmployee,Month) VALUES (?,?)";
//                for (int i = 1; i < 13; i++) {
//                    PreparedStatement ps0 = connection.prepareStatement(sql0);
//                    ps0.setInt(1, Employee.getIDEmployee());
//                    ps0.setInt(2, i);
//                    int rs0 = ps0.executeUpdate();
//                }
//            }

//                      String sql0 = "INSERT INTO SalaryEmployee (IDEmployee) VALUES (?)";
//            PreparedStatement ps0 = connection.prepareStatement(sql0);
//            ps0.setInt(1, Employee.getIDEmployee());
//            int rs0 = ps0.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeEmployee(String IDEmployee) {
        try {
            Connection connection = getConnection();
            String sql1 = "DELETE FROM Employee WHERE ID = ? ";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, IDEmployee);
            int rs1 = ps1.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void updateEmployeeEA(Employee Employee) {
//        try {
//            Connection connection = getConnection();
//            String sql = "UPDATE Employee SET Address = ?, Phone =?, Role = ?, Shift = ?, Salary = ?, "
//                    + "EmployeeName=?,Password=? , name =? WHERE ID =?";
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, Employee.getID());
//            ps.setString(2, Employee.getAddress());
//            ps.setString(3, Employee.getPhone());
//            ps.setString(4, Employee.getRole());
//            ps.setString(6, Employee.getShift());
//            ps.setLong(5, Employee.getSalary());
//            ps.setString(7, Employee.getEmployeeName());
//            ps.setString(8, Employee.getPassWord());
//            ps.setString(9, Employee.getName());
//            int rs = ps.executeUpdate();
////            String sql0 = "DELETE FROM SalaryEmployee WHERE IDEmployee = ? ";
////
////            PreparedStatement ps0 = connection.prepareStatement(sql0);
////            ps0.setInt(1, Employee.getIDEmployee());
////            int rs0 = ps0.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

//    public void updateEmployeeAE(Employee Employee) {
//        try {
//            Connection connection = getConnection();
//            String sql = "UPDATE Employeeoop SET FullName =? ,Gender=?,Dob=?,Address=?,"
//                    + "Phone=?,EmployeeName=?,Password=? , Role =? WHERE IDEmployee =?";
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(9, Employee.getIDEmployee());
//            ps.setString(1, Employee.getFullName());
//            ps.setString(2, Employee.getGender());
//            ps.setString(3, Employee.getDob());
//            ps.setString(5, Employee.getPhone());
//            ps.setString(4, Employee.getAddress());
//            ps.setString(6, Employee.getEmployeeName());
//            ps.setString(7, Employee.getPassword());
//            ps.setString(8, Employee.getRole());
//            int rs = ps.executeUpdate();
//            String sql0 = "INSERT INTO SalaryEmployee (IDEmployee,Month) VALUES (?,?)";
//            for (int i = 1; i < 13; i++) {
//                PreparedStatement ps0 = connection.prepareStatement(sql0);
//                ps0.setInt(1, Employee.getIDEmployee());
//                ps0.setInt(2, i);
//                int rs0 = ps0.executeUpdate();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public void updateEmployee(Employee Employee) {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE employee SET address = ? phone_number =?, Role = ?, Shift = ?, Salary = ? "
                    + ", name =? WHERE ID =?";
            String sql2 = "update employee set address=?, phone_number = ?, Salary = ?, Shift = ?, name = ? where ID=?";

            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setString(1, Employee.getAddress());
            ps.setString(2, Employee.getPhone());
            ps.setString(3, ""+Employee.getSalary());
            ps.setString(4, Employee.getShift());
            ps.setString(5, Employee.getName());
            ps.setString(6, Employee.getID());
            int rs = ps.executeUpdate();
//            String sql0 = "DELETE FROM SalaryEmployee WHERE IDEmployee = ? ";
//
//            PreparedStatement ps0 = connection.prepareStatement(sql0);
//            ps0.setInt(1, Employee.getIDEmployee());
//            int rs0 = ps0.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void updateEmployee(Employee employee) {
//        try {
//            Connection connection = getConnection();
//            String sql = "UPDATE employee SET Address = ?, phone_number =?, Role = ?, Shift = ?, Salary = ?, "
//                    + "EmployeeName=?,Password=? , name =? WHERE ID =?";
//            String sql2 = "update employee set name=?, Shift = ?, Salary = ?, Role = ?, EmployeeName = ?, Password = ? where ID=?";
//            //UPDATE supermarket.employee SET EmployeeName = 'Tuan2000' WHERE (ID = 'IDEmp128');
//            //UPDATE supermarket.employee SET name = 'Đình Tuấn', DOB = '2000-6-24', phone_number = '329413043', Shift = 'đêm', Salary = '99999999' WHERE (ID = 'NV120932');
//            PreparedStatement ps = connection.prepareStatement(sql2);
//            ps.setString(1, employee.getName());
//            ps.setString(2, employee.getShift());
//            ps.setString(3, ""+employee.getSalary());
//            ps.setString(4, employee.getRole());
//            ps.setString(5, employee.getEmployeeName());
//            ps.setString(6, employee.getPassWord());
//            ps.setString(7, employee.getID());
//            int rs = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }


    public Employee getEmployeeByID(String IDEmployee) {

        try {

            String sql = "SELECT * FROM Employee WHERE ID =?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, IDEmployee);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Employee u = new Employee();
                u.setID(resultSet.getString("ID"));
                u.setName(resultSet.getString("name"));
                u.setRole(resultSet.getString("Role"));
                u.setDoB(resultSet.getString("DOB"));
                u.setAddress(resultSet.getString("address"));
                u.setPhone(resultSet.getString("phone_number"));
                u.setSalary(resultSet.getInt("Salary"));
                u.setEmployeeName(resultSet.getString("EmployeeName"));
                u.setPassWord(resultSet.getString("Password"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

//    public List<Employee> getSalaryEmployeeByID(int IDEmployee) {
//        List<Employee> listE = new ArrayList<>();
//        try {
//
//            String sql = "select Employeeoop.IDEmployee , FullName,Month,Shift, TotalShiftOnMonth,"
//                    + " MoneyShift ,Bonus from Employeeoop inner join SalaryEmployee ON Employeeoop.IDEmployee = "
//                    + "SalaryEmployee.IDEmployee where Employeeoop.Role = 'Employee' and Employeeoop.IDEmployee=? ";
//            Connection connection = getConnection();
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, IDEmployee);
//            ResultSet resultSet = ps.executeQuery();
//            while (resultSet.next()) {
//                Employee Employee = new Employee();
//                Employee.setIDEmployee(resultSet.getInt("IDEmployee"));
//                Employee.setFullName(resultSet.getString("FullName"));
//                Employee.setMonth(resultSet.getInt("Month"));
//                Employee.setShift(resultSet.getString("Shift"));
//                Employee.setTotalShiftOnMonth(resultSet.getInt("TotalShiftOnMonth"));
//                Employee.setMoneyShift(resultSet.getInt("MoneyShift"));
//                Employee.setBonus(resultSet.getInt("Bonus"));
//
//                listE.add(Employee);
//
//            }
//            return listE;
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }

    public List<Employee> getAllAdmins() {
        List<Employee> listE = new ArrayList<>();
        try {

            String sql = "select IDEmployee , name, Gender,DOB,Address,"
                    + " Phone from Employee where Role like 'Admin'";

            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Admin ad = new Admin();
                ad.setID(resultSet.getString("ID"));
                ad.setName(resultSet.getString("name"));
                ad.setRole(resultSet.getString("Role"));
                ad.setDoB(resultSet.getString("DOB"));
                ad.setAddress(resultSet.getString("address"));
                ad.setPhone(resultSet.getString("phone_number"));
                ad.setSalary(resultSet.getInt("Salary"));
                ad.setEmployeeName(resultSet.getString("EmployeeName"));
                ad.setPassWord(resultSet.getString("Password"));
                listE.add(ad);

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listE;
    }

    public void updateEmployeePassword(Employee Employee, String newPass) {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE Employee SET Password=? WHERE IDEmployee =?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(2, Employee.getID());
            ps.setString(1, newPass);

            int rs = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public Employee getEmployeeByUsername(String username, String passWord) {

        try {

            String sql = "SELECT * FROM Employee WHERE EmployeeName =? and passWord = ?";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passWord);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Employee u = new Employee();
                u.setID(resultSet.getString("ID"));
                u.setName(resultSet.getString("name"));
                u.setRole(resultSet.getString("Role"));
                u.setDoB(resultSet.getString("DOB"));
                u.setAddress(resultSet.getString("address"));
                u.setPhone(resultSet.getString("phone_number"));
                u.setSalary(resultSet.getInt("Salary"));
                u.setEmployeeName(resultSet.getString("EmployeeName"));
                u.setPassWord(resultSet.getString("Password"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Employee> getEmployeeByName(String name) {
        List<Employee> listProduct = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Product WHERE name like N'%"+name+"%'";
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                Employee d = new Employee();
//                d.setID(rs.getString("id"));
//                d.setProductName(rs.getString("name"));
//                d.setProductQuantity(rs.getInt("quantity"));
//                d.setProductPrice(rs.getInt("price"));
//                d.setProductDetail(rs.getString("product_detail"));
//                listProduct.add(d);
                Employee u = new Employee();
                u.setID(rs.getString("ID"));
                u.setName(rs.getString("name"));
                u.setRole(rs.getString("Role"));
                u.setDoB(rs.getString("DOB"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone_number"));
                u.setSalary(rs.getInt("Salary"));
                u.setEmployeeName(rs.getString("EmployeeName"));
                u.setPassWord(rs.getString("Password"));
                listProduct.add(u);
                return listProduct;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listProduct;
    }
    public List<Employee> searchNV(String find){
        List<Employee> listEmployee = new ArrayList<>();

        try (Connection conn = JDBCConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from employee where (name is null or name = '' or name LIKE ?)");
            ps.setString(1, "%" + find + "%");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String employeeID = res.getString(1);
                String employeeName = res.getString(2);
                String employeeAddress = res.getString(3);
                String employeeDOB = ""+res.getDate(4);
                String employeePhone = res.getString(5);
                String emRole = res.getString(6);
                String emShift = res.getString(7);
                Long salary = res.getLong(8);
                String username = res.getString(9);
                String password = res.getString(10);
                listEmployee.add(new Employee(employeeID, employeeName, employeeDOB, employeeAddress, employeePhone, emRole, emShift, salary, username, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listEmployee;
    }
}
