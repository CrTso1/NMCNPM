package src.service;

import src.dao.EmployeeDAO;
import java.util.List;
import src.model.Employee;
import src.model.Admin;
public class EmployeeService {
//    private EmployeeDAO EmployeeDAO;

    private static EmployeeService instance;

    public static EmployeeService getInstance(){
        if (instance == null){
            instance = new EmployeeService();
        }
        return instance;
    }

//    public EmployeeService() {
//        EmployeeDAO = new EmployeeDAO();
//
//    }

    public List<Employee> getAllEmployee() {
        return EmployeeDAO.getInstance().getAllEmployees();
    }

    public void addEmployee(Employee Employee) {
        EmployeeDAO.getInstance().addEmployee(Employee);
    }

    public void updateEmployee(Employee u) {
        EmployeeDAO.getInstance().updateEmployee(u);
    }

    public void removeEmployee(String IDEmployee) {
        EmployeeDAO.getInstance().removeEmployee(IDEmployee);
    }

    public List<Employee> getAllEmployeeNames() {
        return EmployeeDAO.getInstance().getAllEmployeeNames();
    }

    public List<Employee> getAllEmployees() {
        return EmployeeDAO.getInstance().getAllEmployees();
    }

    public Employee getEmployeeByID(String IDEmployee) {

        return EmployeeDAO.getInstance().getEmployeeByID(IDEmployee);
    }


    public  void changPassEmployee(Employee Employee, String newPass){
        EmployeeDAO.getInstance().updateEmployeePassword(Employee, newPass);
    }

    public Employee getEmployeeByUsername(String username, String passWord){
        return EmployeeDAO.getInstance().getEmployeeByUsername(username, passWord);
    }
}
