package pj5;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    private User sender;
    private User receiver;
    private String message;
    private String createDate;
    private boolean disappearing;

    private User deletedFor = null;

    private static final long serialVersionUID = 2L;

    public Message(User sender, User receiver, String message, boolean disappearing) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.disappearing = disappearing;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.createDate = dtf.format(LocalDateTime.now());
    }

    @Override
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
    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public boolean isDisappearing() {
        return disappearing;
    }

    public User getDeletedFor() {
        return deletedFor;
    }

    public void setDeletedFor(User deletedFor) {
        this.deletedFor = deletedFor;
    }

}
