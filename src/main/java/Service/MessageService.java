package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public Message addMessage(Message message){

        if (message.getMessage_text() != "" && message.getMessage_text().length() <= 255 && messageDAO.getUserByPostedId(message.getPosted_by()) != null ) {
            return messageDAO.insertMessage(message);
        }

        return null;

    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id){
        return messageDAO.getMessageById(message_id);
    }
    
}
