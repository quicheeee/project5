package pj5;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class Message
 * The message class represents individual messages sent between different users.
 * It takes String objects and converts the text to Message objects which can be stored.
 *
 * @author Amelia Williams, Meha Kavoori, Anish Puri, Tyler Barnett
 * @version 04/28/2023
 */
// following class implements Serializabe and Comparable<Message> so that the messages can be compared
public class Message implements Serializable {
    private User sender;
    private User receiver;
    private String message;
    private String createDate;
    private boolean disappearing;

    private User deletedFor = null;

    private static final long serialVersionUID = 2L;
    // following constructor passes all the previously stated fields along with a User message owner
    public Message(User sender, User receiver, String message, boolean disappearing) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.disappearing = disappearing;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.createDate = dtf.format(LocalDateTime.now());
    }

    @Override
    // following method checks if an object is equal to this and returns true. If not and o is null, it returns false.
    // if true, a the object is cast as a message to check if sender equals the message sender,
    // the receiver equals the message receiver
    //, the owner equals  the message owner, and if the timestamp equals the message timestamp.
    // if all of this is true, the message is returned
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!getSender().equals(message1.getSender())) return false;
        if (!getReceiver().equals(message1.getReceiver())) return false;
        if (!getCreateDate().equals(message1.getCreateDate())) return false;
        return getMessage().equals(message1.getMessage());
    }

    /*public boolean related(Object o) {
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!getSender().equals(message1.getSender())) return false;
        if (!getReceiver().equals(message1.getReceiver())) return false;
        if (!getCreateDate().equals(message1.getCreateDate())) return false;
        return getMessage().equals(message1.getMessage());
    }
*/
    // gets the sender field
    public User getSender() {
        return sender;
    }
    // gets the receiver field
    public User getReceiver() {
        return receiver;
    }
    // sets the Message field
    public void setMessage(String message) {
        this.message = message;
    }
    // gets the message field
    public String getMessage() {
        return message;
    }
    // gets the timestamp of when the message was created
    public String getCreateDate() {
        return this.createDate;
    }
    // possible method for disappearing messages
    public boolean isDisappearing() {
        return disappearing;
    }
    // returns user that the message is deleted for
    public User getDeletedFor() {
        return deletedFor;
    }
    // sets user the message is deleted for
    public void setDeletedFor(User deletedFor) {
        this.deletedFor = deletedFor;
    }

}
