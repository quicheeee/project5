package pj5;
import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private boolean readCustomer;
    private boolean readSeller;
    private User customer;
    private User seller;
    private Store store;
    private ArrayList<Message> messages;
    //private User convOwner;

    private static final long serialVersionUID = 1L;

    public Conversation(User customer, User seller, Store store) {
        this.customer = customer;
        this.seller = seller;
        this.store = store;
        this.messages = new ArrayList<>();
        this.readCustomer = true;
        this.readSeller = true;
    }

    public void addMessage(User sender, User receiver, String message, boolean disappearing) {
        Message m = new Message(sender, receiver, message, disappearing);
        this.messages.add(m);

        setReadFlags(receiver, false);
    }

    private void setReadFlags(User receiver, Boolean read) {
        if (receiver.equals(customer))
            readCustomer = read;
        else
            readSeller = read;
    }

    public boolean hasUserRead(User user) {
        if (user.equals(customer))
            return readCustomer;
        else
            return readSeller;
    }

    public void deleteMessage(Message message, User user) {
        if (message.getDeletedFor() == null)
            message.setDeletedFor(user);
        else if (!message.getDeletedFor().equals(user))
            messages.remove(message);
    }

    public void updateMessage(Message message, String content, User user) {
        message.setMessage(content);

        User receiver;
        if (user instanceof Customer)
            receiver = seller;
        else
            receiver = customer;
        setReadFlags(receiver, false);
    }

    public ArrayList<Message> getMessagesForUser(User user) {
        ArrayList<Message> temp = new ArrayList<>();
        for (Message m : messages) {
            if ((m.getDeletedFor() == null) || (!m.getDeletedFor().equals(user)))
                temp.add(m);
        }
        setReadFlags(user, true);
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (!getCustomer().equals(that.getCustomer())) return false;
        if (!getSeller().equals(that.getSeller())) return false;
        return getStore().equals(that.getStore());
    }

    /*// Override the compareTo method
    public int compareTo(Conversation conversation)
    {, Comparable<Conversation>
        if (isRead() == conversation.isRead())
            return 0;
        if (isRead() && !conversation.isRead())
            return 1;
        else
            return -1;
    }
*/

    public User getCustomer() {
        return customer;
    }

    public User getSeller() {
        return seller;
    }

    public Store getStore() {
        return store;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public boolean isReadCustomer() {
        return readCustomer;
    }

    public boolean isReadSeller() {
        return readSeller;
    }

    public String getMessageString() {
        StringBuilder sb = new StringBuilder();
        for (Message m : this.messages)
            sb.append("[" + m.getSender().getName() + "] " + m.getMessage() + "\n");
        return sb.toString();
    }
}
