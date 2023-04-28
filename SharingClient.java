package pj5;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class SharingClient {
    private Socket socket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    public SharingClient() {
    }

    public boolean startConnection() {
        String hostName;
        int port = 3333;
        hostName = "localhost";

        try {
            socket = new Socket(hostName, port);
            //JOptionPane.showMessageDialog(null, "Connection Established!", "Connection Established", JOptionPane.INFORMATION_MESSAGE);

            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            return true;
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Connection could not be established. "
                                            + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public User signIn(String userID, String password) {
        User loggedUser;
        try {
            out.writeObject("User.signIn");
            out.writeObject(userID);
            out.writeObject(password);
            out.flush();
            loggedUser = (User) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            loggedUser = null;
        }
        return loggedUser;
    }

    public ArrayList<User> searchSellerByUser(String search, User user) {
        try {
            out.writeObject("User.searchSellerByUser");
            out.writeObject(search);
            out.writeObject(user);
            out.flush();

            ArrayList<User> temp = (ArrayList<User>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> searchCustomerByUser(String search, User user) {
        try {
            out.writeObject("User.searchCustomerByUser");
            out.writeObject(search);
            out.writeObject(user);
            out.flush();

            ArrayList<User> temp = (ArrayList<User>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getCustomersByUser(User user) {
        try {
            out.writeObject("User.getCustomersByUser");
            out.writeObject(user);
            out.flush();

            ArrayList<User> temp = (ArrayList<User>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User newUser(String name, String emailAddress, String password, String storeName, int userType) {
        try {
            out.writeObject("User.newUser");
            out.writeObject(name);
            out.writeObject(emailAddress);
            out.writeObject(password);
            out.writeObject(storeName);
            out.writeObject(userType);
            out.flush();

            User temp = (User) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addNewStore(Seller seller, String storeName) {
        try {
            out.writeObject("User.addNewStore");
            out.writeObject(seller);
            out.writeObject(storeName);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User u) {
        try {
            out.writeObject("User.deleteUser");
            out.writeObject(u);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean blockUser(User blocker, User blocked) {
        try {
            out.writeObject("User.blockUser");
            out.writeObject(blocker);
            out.writeObject(blocked);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFilterForUser(User user, String original, String replacement) {
        if((original == null) || (replacement == null))
            throw new IllegalArgumentException("Invalid filter strings");

        try {
            out.writeObject("User.addFilterForUser");
            out.writeObject(user);
            out.writeObject(original);
            out.writeObject(replacement);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Store> getAllStoresForUser (User user) {
        try {
            out.writeObject("Store.getAllStoresForUser");
            out.writeObject(user);
            out.flush();

            ArrayList<Store> temp = (ArrayList<Store>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean sendNewMessage(User sender, User receiver, String message, Boolean disappearing,
                                      Customer customer, Store store) {
        try {
            out.writeObject("Messenger.sendNewMessage");
            out.writeObject(sender);
            out.writeObject(receiver);
            out.writeObject(message);
            out.writeObject(disappearing);
            out.writeObject(customer);
            out.writeObject(store);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Conversation> getConversationsForUser(User user) {
        try {
            out.writeObject("Messenger.getConversationsForUser");
            out.writeObject(user);
            out.flush();

            ArrayList<Conversation> temp = (ArrayList<Conversation>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addMessageToConversation(Conversation conversation, User sender, String message,
                                                boolean disappearing) {
        try {
            out.writeObject("Messenger.addMessageToConversation");
            out.writeObject(conversation);
            out.writeObject(sender);
            out.writeObject(message);
            out.writeObject(disappearing);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Message> getMessagesForUser(Conversation conversation, User user) {
        try {
            out.writeObject("Messenger.getMessagesForUser");
            out.writeObject(conversation);
            out.writeObject(user);
            out.flush();

            ArrayList<Message> temp = (ArrayList<Message>) in.readObject();
            return temp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean editMessage(Conversation conversation, Message m, String content, User user) {
        try {
            out.writeObject("Messenger.editMessage");
            out.writeObject(conversation);
            out.writeObject(m);
            out.writeObject(content);
            out.writeObject(user);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMessage(Conversation conversation, Message m, User current) {
        try {
            out.writeObject("Messenger.deleteMessage");
            out.writeObject(conversation);
            out.writeObject(m);
            out.writeObject(current);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsUnreadMessagesForUser(User user) {
        try {
            out.writeObject("Messenger.existsUnreadMessagesForUser");
            out.writeObject(user);
            out.flush();

            boolean temp = (boolean) in.readObject();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void stopConnection() {
        try {
            out.writeObject("Exit");
            out.flush();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SharingClient client = new SharingClient();
        if (!client.startConnection())
            return;

        //client.newUserTest();
        User loggedUser = client.signInTest();
        if (loggedUser == null)
            return;

        ArrayList<User> temp = client.searchSellerByUser("a", loggedUser);
        String[] options =  temp.stream().map(n -> n.getName()).toArray(String[]::new);
        Object sel = JOptionPane.showInputDialog(null, "Sellers:",
                "Seller Search Results", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        temp = client.searchCustomerByUser("a", loggedUser);
        options =  temp.stream().map(n -> n.getName()).toArray(String[]::new);
        sel = JOptionPane.showInputDialog(null, "Customers:",
                "Customer Search Results", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        temp = client.getCustomersByUser(loggedUser);
        options =  temp.stream().map(n -> n.getName()).toArray(String[]::new);
        sel = JOptionPane.showInputDialog(null, "Customers:",
                "Customer Search Results", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        //should call this from an exit button
        client.stopConnection();
    }

    public User signInTest() {
        String userID;
        String password;

        JTextField userIDText = new JTextField();
        JTextField passwordText = new JTextField();
        Object[] message = {
                "User ID:", userIDText,
                "Password:", passwordText
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Log In", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            userID = userIDText.getText().trim();
            password = passwordText.getText();
        } else {
            JOptionPane.showMessageDialog(null, "User cancelled, exiting program...", "Cancelled", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        User user = signIn(userID, password);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Invalid user id or password",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println(user.getName() + " logged in.");
        }
        return user;
    }

    public boolean newUserTest() {
        String name;
        String emailAddress;
        String password;
        String storeName;
        int userType = 1;

        JTextField nameText = new JTextField();
        JTextField emailText = new JTextField();
        JTextField passwordText = new JTextField();
        JTextField storeText = new JTextField();
        JComboBox typeCombo = new JComboBox<>(new String[] {"Customer", "Seller"});

        Object[] message = {
                "Name:", nameText,
                "Email:", emailText,
                "Password:", passwordText,
                "Store:", storeText,
                "User Type:", typeCombo
        };

        int option = JOptionPane.showConfirmDialog(null, message, "New User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            name = nameText.getText().trim();
            emailAddress = emailText.getText().trim();
            password = passwordText.getText().trim();
            storeName = storeText.getText().trim();
            userType = typeCombo.getSelectedIndex() + 1;
        } else {
            JOptionPane.showMessageDialog(null, "New user creation cancelled...", "Cancelled", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        User temp = newUser(name, emailAddress, password, storeName, userType);
        if (temp == null) {
            JOptionPane.showMessageDialog(null, "User could not be created. Email is taken.",
                    "Create User Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
