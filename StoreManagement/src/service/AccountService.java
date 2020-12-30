package src.service;

import src.service.AccountService;
import src.dao.AccountDao;
import src.model.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private static AccountService instance;
    private AccountService(){}

    public static AccountService getInstance(){
        if(instance==null)
            instance = new AccountService();
        return instance;
    }

    public List<Account> getAllUsers() throws SQLException {
        return AccountDao.getInstance().getAllUsers();
    }

    public void addUser(Account user) throws SQLException {
        AccountDao.getInstance().addUser(user);
    }

    public Account getUser(String username, String password) throws SQLException {
        return AccountDao.getInstance().getUser(username, password);
    }
    public Account getUserById(String username) throws SQLException {
        return AccountDao.getInstance().getUserById(username);
    }

    public boolean checkCreateUser(String username, String email) throws SQLException {
        return AccountDao.getInstance().checkCreateUser(username, email);
    }

    public int editUser(Account user) {
        return AccountDao.getInstance().editUser(user);
    }

    public List<Account> getUserNoOwner() throws SQLException {return AccountDao.getInstance().getUserNoOwner();}
}
