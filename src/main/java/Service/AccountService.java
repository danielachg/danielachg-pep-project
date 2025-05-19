package Service;

import Model.Account;
import DAO.AccountDAO;


public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }


    public Account addAccount(Account account){

        if (account.username != "" && accountDAO.getAccountByUsername(account.username) == null && account.password.length() >= 4){
            return accountDAO.insertAccount(account);
        }

        return null;
    }

    
}
