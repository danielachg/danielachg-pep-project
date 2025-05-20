package Service;

import DAO.MessageDAO;
import Model.Message;

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
    
}
