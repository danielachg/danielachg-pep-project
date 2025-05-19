package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

    public Account insertAccount(Account account){

        Connection connection = ConnectionUtil.getConnection();
        try{

            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            ResultSet primaryKeyResult = preparedStatement.getGeneratedKeys();

            if (primaryKeyResult.next()){

                int generated_account_id = primaryKeyResult.getInt(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
                
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    

    public Account getAccountByUsername(String username){

        Connection connection = ConnectionUtil.getConnection();
        try{

            String sql = "SELECT * FROM Account WHERE username=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
