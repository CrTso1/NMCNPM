package src.service;

import src.dao.CustomerDAO;
import java.util.List;
import src.model.Customer;

public class CustomerService {
//    private CustomerDAO CustomerDAO;
    private static CustomerService instance;

    public static CustomerService getInstance(){
        if (instance == null){
            instance = new CustomerService();
        }
        return instance;
    }

//    public CustomerService() {
//        CustomerDAO = new CustomerDAO();
//    }
    public List<Customer> getAllCustomers() {
        return CustomerDAO.getInstance().getAllCustomers();
    }

    public List<Customer> getCustomersByName(String name){
        return CustomerDAO.getInstance().getCustomersByName(name);
    }
    public  void removeCustomer(String IDCustomer){
        CustomerDAO.getInstance().removeCustomer(IDCustomer);
    }
    public void updateCustomer(Customer g) {
        CustomerDAO.getInstance().updateCustomer(g);
    }
    public Customer getCustomersByID(int ID){
        return CustomerDAO.getInstance().getCustomersByID(ID);
    }
    public Customer getCustomersByPhone(String phone){
        return CustomerDAO.getInstance().getCustomersByPhone(phone);
    }
    public  void addCustomer(Customer g){
        CustomerDAO.getInstance().addCustomer(g);
    }
}
