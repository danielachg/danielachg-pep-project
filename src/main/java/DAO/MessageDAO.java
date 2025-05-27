package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class MessageDAO {
    
    public Message insertMessage(Message message){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql ="INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();

            ResultSet primaryKeyResultSet = preparedStatement.getGeneratedKeys();

            if (primaryKeyResultSet.next()){
                int generated_message_id = primaryKeyResultSet.getInt(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message getUserByPostedId(int posted_by){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql = "SELECT * FROM Message WHERE posted_by=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, posted_by);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Message> getAllMessages(){

        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try{

            String sql = "SELECT * FROM Message;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return messages;

    }

    public Message getMessageById(int message_id){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql = "SELECT * FROM Message WHERE message_id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                return new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }


    public Message deleteMessageById(int message_id){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql = "SELECT * FROM Message WHERE message_id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){

                Message targetMessage = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));

                try{

                PreparedStatement deleteStatement = connection.prepareStatement("DELETE * FROM Message WHERE message_id=?;");
                deleteStatement.setInt(1, message_id);

                preparedStatement.executeUpdate();

                } catch(SQLException e){
                    System.out.println(e.getMessage());
                }

                return targetMessage;

            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    
    public Message updateMessage(int message_id, Message message){

        Connection connection = ConnectionUtil.getConnection();

        try{

            String sql = "UPDATE Message SET message_text=? WHERE message_id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();

           
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    public List<Message> getAllMessagesByUserId(int account_id){

        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try{

            String sql = "SELECT * FROM Message WHERE posted_by=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return messages;

    }
}
