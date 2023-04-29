package pj5;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class Conversation
 * <p>
 * The conversation class represents individual conversations sent between different users.
 *
 * @author Amelia Williams, Meha Kavoori, Anish Puri, Tyler Barnett
 * @version 04/10/2023
 */
public class Conversation implements Serializable {
    private boolean readCustomer;
    private boolean readSeller;
    private User customer;
    private User seller;
    private Store store;
    private ArrayList<Message> messages;
    //private User convOwner;

    private static final long serialVersionUID = 1L;
    // the constructor for a conversation which inputs the customer, seller, and store
    // associated with it and initializes parameters
    public Conversation(User customer, User seller, Store store) {
        this.customer = customer;
        this.seller = seller;
        this.store = store;
        this.messages = new ArrayList<>();
        this.readCustomer = true;
        this.readSeller = true;
    }
    // a method that adds a message to the conversation
    public void addMessage(User sender, User receiver, String message, boolean disappearing) {
        Message m = new Message(sender, receiver, message, disappearing);
        this.messages.add(m);

        setReadFlags(receiver, false);
    }
    // a method that sets the associated conservation to read when the seller or customer opens the message
    private void setReadFlags(User receiver, Boolean read) {
        if (receiver.equals(customer))
            readCustomer = read;
        else
            readSeller = read;
    }
    // a method that checks whether a user has read the conversation's message
    public boolean hasUserRead(User user) {
        if (user.equals(customer))
            return readCustomer;
        else
            return readSeller;
    }
    // a method that deletes a messagae from a conversation for a user
    public void deleteMessage(Message message, User user) {
        if (message.getDeletedFor() == null)
            message.setDeletedFor(user);
        else if (!message.getDeletedFor().equals(user))
            messages.remove(message);
    }
    // a method that updates the message in a conversation to a new message when a user edits it
    public void updateMessage(Message message, String content, User user) {
        message.setMessage(content);

        User receiver;
        if (user instanceof Customer)
            receiver = seller;
        else
            receiver = customer;
        setReadFlags(receiver, false);
    }
    // a method that gets all the messages for a user within the conversation if they are not deleted for the user
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
    // checks if the object o is equal to the current conversation object
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
    } // returns the customer associated with the conversation

    public User getSeller() {
        return seller;
    } // returns the seller associated with the conversation

    public Store getStore() {
        return store;
    } // returns the store associated with the conversation

    public ArrayList<Message> getMessages() {
        return messages;
    } // returns the arraylist of messages associated with the conversation

    public boolean isReadCustomer() {
        return readCustomer;
    } // returns whether the customer has read the message

    public boolean isReadSeller() {
        return readSeller;
    } // returns whether the seller has read the message
    
    // returns a string of the conversation's messages
    public String getMessageString() {
        StringBuilder sb = new StringBuilder();
        for (Message m : this.messages)
            sb.append("[" + m.getSender().getName() + "] " + m.getMessage() + "\n");
        return sb.toString();
    }
}
