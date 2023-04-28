package pj5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class auxDash extends JComponent implements Runnable{
    private static SharingClient client;
    private static JLabel newMessagesLabel;
    private static JFrame commonFrame;

    public static void main(String[] args) {
        client = new SharingClient();
        if (!client.startConnection())
            return;

        SwingUtilities.invokeLater(new auxDash());
    }

    public static void customerMenu(User user){
        JFrame frame;
        frame = new JFrame("Customer Menu");
        Container content = frame.getContentPane();
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JButton sendNewMsgButton = new JButton("Send a New Message");
        JButton viewConvosButton = new JButton("View Conversations");
        JButton blockUserButton = new JButton ("Block User");
        JButton exportConvoButton = new JButton("Export Conversation");
        JButton importConvoButton = new JButton("Import Conversation");
        JButton deleteAccountButton = new JButton("Delete Accounts");
        JButton addFiltersButton = new JButton ("Add Filters");

        ActionListener custMenuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sendNewMsgButton) {
                    //frame.dispose();
                    sendNewMailCustomer(user);
                    //customerMenu(user);
                }
                if (e.getSource() == viewConvosButton) {
                    //frame.dispose();
                    viewConversations(user);
                    //customerMenu(user);
                }
                if (e.getSource() == blockUserButton) {
                    //frame.dispose();
                    blockUser(user);
                    //customerMenu(user);
                }
                if (e.getSource() == exportConvoButton) {
                    //frame.dispose();
                    exportConversation(user);
                    //customerMenu(user);
                }
                if (e.getSource() == importConvoButton) {
                    //frame.dispose();
                    importConversation(user);
                    //customerMenu(user);
                }
                if (e.getSource() == deleteAccountButton) {
                    frame.dispose();
                    deleteAccount(user);
                    SwingUtilities.invokeLater(new auxDash());
                }
                if (e.getSource() == addFiltersButton) {
                    //frame.dispose();
                    addFilters(user);
                    //customerMenu(user);
                }
                refreshNewMessageLabel(user);
            }
        };

        newMessagesLabel = new JLabel();
        newMessagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refreshNewMessageLabel(user);

        JPanel panel = new JPanel(new GridLayout(8, 1));
        panel.add(newMessagesLabel);
        sendNewMsgButton.addActionListener(custMenuActionListener);
        viewConvosButton.addActionListener(custMenuActionListener);
        blockUserButton.addActionListener(custMenuActionListener);
        exportConvoButton.addActionListener(custMenuActionListener);
        importConvoButton.addActionListener(custMenuActionListener);
        deleteAccountButton.addActionListener(custMenuActionListener);
        addFiltersButton.addActionListener(custMenuActionListener);
        panel.add(sendNewMsgButton);
        panel.add(viewConvosButton);
        panel.add(blockUserButton);
        panel.add(exportConvoButton);
        panel.add(importConvoButton);
        panel.add(deleteAccountButton);
        panel.add(addFiltersButton);
        content.add(panel);

        commonFrame = frame;
    }

    public static void sellerMenu(User user){
        JFrame frame;
        frame = new JFrame("Seller Menu");
        Container content = frame.getContentPane();
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JButton sendNewMsgButton = new JButton("Send a New Message");
        JButton viewConvosButton = new JButton("View Conversations");
        JButton blockUserButton = new JButton ("Block User");
        JButton exportConvoButton = new JButton("Export Conversation");
        JButton importConvoButton = new JButton("Import Conversation");
        JButton deleteAccountButton = new JButton("Delete Accounts");
        JButton addFiltersButton = new JButton ("Add Filters");

        ActionListener sellMenuActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sendNewMsgButton) {
                    //frame.dispose();
                    sendNewMailSeller(user);
                    //sellerMenu(user);
                }
                if (e.getSource() == viewConvosButton) {
                    //frame.dispose();
                    viewConversations(user);
                    //sellerMenu(user);
                }
                if (e.getSource() == blockUserButton) {
                    //frame.dispose();
                    blockUser(user);
                    //sellerMenu(user);
                }
                if (e.getSource() == exportConvoButton) {
                    //frame.dispose();
                    exportConversation(user);
                    //sellerMenu(user);
                }
                if (e.getSource() == importConvoButton) {
                    //frame.dispose();
                    importConversation(user);
                    //sellerMenu(user);
                }
                if (e.getSource() == deleteAccountButton) {
                    frame.dispose();
                    deleteAccount(user);
                    SwingUtilities.invokeLater(new auxDash());
                }
                if (e.getSource() == addFiltersButton) {
                    //frame.dispose();
                    addFilters(user);
                    //sellerMenu(user);
                }

            }
        };

        newMessagesLabel = new JLabel();
        newMessagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        refreshNewMessageLabel(user);

        JPanel panel = new JPanel(new GridLayout(8, 1));
        panel.add(newMessagesLabel);
        sendNewMsgButton.addActionListener(sellMenuActionListener);
        viewConvosButton.addActionListener(sellMenuActionListener);
        blockUserButton.addActionListener(sellMenuActionListener);
        exportConvoButton.addActionListener(sellMenuActionListener);
        importConvoButton.addActionListener(sellMenuActionListener);
        deleteAccountButton.addActionListener(sellMenuActionListener);
        addFiltersButton.addActionListener(sellMenuActionListener);
        panel.add(sendNewMsgButton);
        panel.add(viewConvosButton);
        panel.add(blockUserButton);
        panel.add(exportConvoButton);
        panel.add(importConvoButton);
        panel.add(deleteAccountButton);
        panel.add(addFiltersButton);
        content.add(panel);

        commonFrame = frame;
    }

    private static User signInOrCreateAccount(){
        boolean invalidinput = true;
        User user = null;
        while (invalidinput) {
            String[] createOrSignIn = {"Create an Account", "Sign in to an Account"};
            String Select1 = (String) JOptionPane.showInputDialog(null, "Select what to do next",
                    "Messenger", JOptionPane.PLAIN_MESSAGE, null, createOrSignIn,null);
            if (Select1 == null) return null;

            if(Select1.equals(createOrSignIn[0])){
                JTextField nameField = new JTextField(10);
                JTextField emailField = new JTextField(10);
                JTextField passwordField = new JTextField(10);
                String[] acType = {"Seller", "Customer"};
                JComboBox<String> jcb = new JComboBox<>();
                jcb.addItem(acType[0]);
                jcb.addItem(acType[1]);
                JPanel panelName = new JPanel();
                JPanel panel = new JPanel();
                JPanel panel1 = new JPanel();
                panelName.add(new Label("Name"));
                panelName.add(nameField);
                panel.add(new JLabel("Email"));
                panel.add(emailField);

                panel.add(new JLabel("Password"));
                panel.add(passwordField);
                panel1.add(new JLabel("Account Type:"));
                panel1.add(jcb);

                JPanel[] p = {panelName, panel, panel1};

                //Object[] o = {"Email", emailField, "Password", passwordField};

                String name = "";
                String email = "";
                String password = "";
                String accountType = "";
                do{
                    int a = JOptionPane.showConfirmDialog(null, p, "Create Account", JOptionPane.OK_CANCEL_OPTION);
                    if (a == JOptionPane.CANCEL_OPTION)
                        return null;
                    name = nameField.getText();
                    email = emailField.getText();
                    password = passwordField.getText();
                    accountType = String.valueOf(jcb.getSelectedItem());
                } while(name.equals("") || email.equals("") || password.equals(""));

                if(accountType.equals("Customer")){
                    user = client.newUser(name, email, password, "", 1);
                }
                if(accountType.equals("Seller")){
                    String storeName = (String) JOptionPane.showInputDialog(null, "What would you like your first store to be named?");
                    if (storeName == null) return null;

                    while (storeName.trim().equals("")) {
                        storeName = (String) JOptionPane.showInputDialog(null, "Please enter a valid store name.", "Error", JOptionPane.WARNING_MESSAGE);
                        if (storeName == null) return null;
                    }
                    try {
                        user = client.newUser(name, email, password, storeName, 2);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else if (Select1.equals(createOrSignIn[1])) {
                JTextField email = new JTextField();
                JTextField password = new JTextField();
                Object [] options = {"Email", email, "Password", password};
                int res = JOptionPane.showConfirmDialog(null, options, "Log In", JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.CANCEL_OPTION)
                    return null;

                user = client.signIn(email.getText(), password.getText());
                if(user == null){
                    JOptionPane.showMessageDialog(null, "Incorrect Login Information", "Error", JOptionPane.WARNING_MESSAGE);
                    invalidinput = true;
                } else{
                    return user;
                }
            }
        }
        return user;
    }

    public static void deleteAccount(User user) {

        int de = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?");
        if (de == 0) {
            client.deleteUser(user);
            JOptionPane.showMessageDialog(null, "Account Deleted");

        }

    }

    private static void sendNewMailCustomer(User user){
        String[] selectStoreOrSearchSeller= {"Select a store to message", "Search for a seller to message"};
        String select2 = (String) JOptionPane.showInputDialog(null,
                "Select what to do next", "Messenger", JOptionPane.PLAIN_MESSAGE,
                null, selectStoreOrSearchSeller,null);
        if (select2 == null) return;

        if(select2.equals(selectStoreOrSearchSeller[0])){
            ArrayList<Store> stores = client.getAllStoresForUser(user);
            chooseStoreToMail(user, stores, null);
        } else if(select2.equals(selectStoreOrSearchSeller[1])){
            String sellerSearch = (String) JOptionPane.showInputDialog(null,
                    "Please enter seller you are searching for:", "Seller Search",
                    JOptionPane.PLAIN_MESSAGE);
            if (sellerSearch == null) return;

            ArrayList<User> users = client.searchSellerByUser(sellerSearch, user);
            if (users.size() == 0) {
                JOptionPane.showMessageDialog(null, "No sellers match", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                String [] userOptions = new String[users.size()];
                for (int i = 0; i < users.size(); i++) {
                    userOptions[i] = ( (i+1) + ". " + users.get(i).getEmail());
                }
                String select3 = (String) JOptionPane.showInputDialog(null,
                        "Select which seller you would like to message.", "Seller Choices",
                        JOptionPane.PLAIN_MESSAGE, null, userOptions, null);
                if (select3 == null) return;

                int num = -1;
                for(int i = 0; i < userOptions.length; i++){
                    if(userOptions[i].equals(select3)){
                        num = i;
                    }
                }
                ArrayList<Store> stores = ((Seller) users.get(num)).getListOfStores();
                chooseStoreToMail(user, stores, null);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Uh Oh. Something went wrong.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void sendNewMailSeller(User user){
        String[] selectOrSearchCustomer= {"Select a customer to message", "Search for a customer to message"};
        String select2 = (String) JOptionPane.showInputDialog(null,
                "Select what to do next", "Messenger", JOptionPane.PLAIN_MESSAGE,
                null, selectOrSearchCustomer,null);
        if(select2.equals(selectOrSearchCustomer[0])){
            ArrayList<User> users = client.getCustomersByUser(user);
            if (users.size() == 0) {
                JOptionPane.showMessageDialog(null, "No customers exist to message", "Error", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String [] userOptions = new String[users.size()];
                for (int i = 0; i < users.size(); i++) {
                    userOptions[i] = ( (i+1) + ". " + users.get(i).getEmail());
                }
                String select3 = (String) JOptionPane.showInputDialog(null,
                        "Select which customer you would like to message.", "Customer Choices",
                        JOptionPane.PLAIN_MESSAGE, null, userOptions, null);
                ArrayList<Store> stores = ((Seller) user).getListOfStores();
                int num = -1;
                for(int i = 0; i < userOptions.length; i++){
                    if(userOptions[i].equals(select3)){
                        num = i;
                    }
                }
                chooseStoreToMail(user, stores, (Customer) users.get(num));
            }
        } else if(select2.equals(selectOrSearchCustomer[1])){
            String customerSearch = (String) JOptionPane.showInputDialog(null,
                    "Please enter customer you are searching for:", "Customer Search",
                    JOptionPane.PLAIN_MESSAGE);
            ArrayList<User> users = client.searchCustomerByUser(customerSearch, user);
            if (users.size() == 0) {
                JOptionPane.showMessageDialog(null, "No customers match", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                String [] userOptions = new String[users.size()];
                for (int i = 0; i < users.size(); i++) {
                    userOptions[i] = ( (i+1) + ". " + users.get(i).getEmail());
                }
                String select3 = (String) JOptionPane.showInputDialog(null,
                        "Select which customer you would like to message.", "Customer Choices",
                        JOptionPane.PLAIN_MESSAGE, null, userOptions, null);
                int num = -1;
                for(int i = 0; i < userOptions.length; i++){
                    if(userOptions[i].equals(select3)){
                        num = i;
                    }
                }
                ArrayList<Store> stores = ((Seller) user).getListOfStores();
                chooseStoreToMail(user, stores, (Customer) users.get(num));

            }

        } else {
            JOptionPane.showMessageDialog(null, "Uh Oh. Something went wrong.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static int getConversationIndex(Conversation conv, User u) {
        ArrayList<Conversation> conversations = client.getConversationsForUser(u);
        int indexToReturn = -1;
        for (int i = 0; i < conversations.size(); i++) {
            if (conv.equals(conversations.get(i))) {
                indexToReturn = i;
            }
        }

        return indexToReturn;
    }


    public static void exportConversation(User user) {
        ArrayList<Conversation> convs = client.getConversationsForUser(user);
        if (convs.size() == 0)
            JOptionPane.showMessageDialog(null, "There are no conversations to view", "Error", JOptionPane.WARNING_MESSAGE);
        else {


            String header = String.format("%30s %30s %30s %20s\n", "Customer", "Seller", "Store", "New");
            String[] list = new String[convs.size()];

            for (int i = 0; i < convs.size(); i++) {
                list[i] = String.format(" %30s %30s %30s %3s\n", convs.get(i).getCustomer().getName(),
                        convs.get(i).getSeller().getName(), convs.get(i).getStore().getStoreName(),
                        convs.get(i).hasUserRead(user) ? "N" : "Y");

            }
            String select3 = (String) JOptionPane.showInputDialog(null,
                    String.format("Select which conversation you would like to export.%n%s", header), "Conversation Choices",
                    JOptionPane.PLAIN_MESSAGE, null, list, null);
            if(select3 == null){
                return;
            }
            int indexInList = 0;
            for (int i = 0; i < list.length; i++) {
                if (select3.equals(list[i])) {
                    indexInList = i;
                }
            }
            Conversation conv = convs.get(indexInList);
            int conversationIndex = getConversationIndex(conv, user); // index where selected conversation is found
            String filePathName = (String) JOptionPane.showInputDialog(null, "Please input a file path to write to ending in.csv");
            ArrayList<String []> list2 = new ArrayList<String[]>();
            String[] headers = {"Timestamp", "Sender", "Receiver", "Message"};
            list2.add(headers);
            for (Message m : convs.get(conversationIndex).getMessages()) {
                String[] msg = {m.getCreateDate(), m.getSender().getName(), m.getReceiver().getName(), m.getMessage()};
                list2.add(msg);
            }

            try {
                File csvOutput = new File(filePathName);
                FileWriter fw = new FileWriter(csvOutput);
                PrintWriter pw = new PrintWriter(fw);
                for (String[] s : list2) {
                    int count = 1;
                    for (String s1 : s) {
                        if (count != 4) {
                            pw.print(s1);
                            pw.print(",");
                            count++;
                        } else {
                            pw.println(s1);
                        }
                    }
                }
                pw.close();
                JOptionPane.showMessageDialog(null, "File Exported", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException fnfe) {
                System.out.println("Creating new file...");
            } catch (Exception e) {
                System.out.println("Exception Caught");
            }
        }
    }

    public static void importConversation(User user) {
        ArrayList<Conversation> convs = client.getConversationsForUser(user);
        if (convs.size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no conversations to import to.");
        } else {
            String header = String.format("%30s %30s %30s %20s\n", "Customer", "Seller", "Store", "New");
            String[] list = new String[convs.size()];

            for (int i = 0; i < convs.size(); i++) {
                list[i] = String.format(" %30s %30s %30s %3s\n", convs.get(i).getCustomer().getName(),
                        convs.get(i).getSeller().getName(), convs.get(i).getStore().getStoreName(),
                        convs.get(i).hasUserRead(user) ? "N" : "Y");

            }
            String select3 = (String) JOptionPane.showInputDialog(null,
                    String.format("Select which conversation you would like to import.%n%s", header), "Conversation Choices",
                    JOptionPane.PLAIN_MESSAGE, null, list, list[0]);
            if(select3 == null){
                return;
            }
            int indexInList = 0;
            for (int i = 0; i < list.length; i++) {
                if (select3.equals(list[i])) {
                    indexInList = i;
                }
            }
            Conversation conv = convs.get(indexInList);
            int conversationIndex = getConversationIndex(conv, user); // index where selected conversation is found
            String filePathName = (String) JOptionPane.showInputDialog(null, "Please input the file path for the text file to import");
            String message = readFile(filePathName);
            if (message == null) {
                JOptionPane.showMessageDialog(null, "File could not be read");
            } else {
                client.addMessageToConversation(convs.get(conversationIndex), user, message, false);
                JOptionPane.showMessageDialog(null, "Your message has been imported");
            }
        }

    }

    private static String readFile(String fileName) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = bfr.readLine()) != null) {
                if (!sb.toString().isEmpty())
                    sb.append("\n");
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static void addFilters(User u) {
        ArrayList<String> filters = u.getFilters();
        if (filters.size() == 0) {
            JOptionPane.showMessageDialog(null, "There are currently no filters");
        }
        String[] op1 = {"Add a new filter", "Go back"};
        String select = (String) JOptionPane.showInputDialog(null, "Select what to do next",
                "Messenger", JOptionPane.PLAIN_MESSAGE, null, op1, null);
        if (select == null) return;

        if (select.equals("Add a new filter")) {
            String filterWord = (String) JOptionPane.showInputDialog("Enter what you want to filter", "");
            String replacementWord = (String) JOptionPane.showInputDialog("Enter the replacement word", "");
            if (replacementWord.equals("")) {
                replacementWord = "*****";
            }
            client.addFilterForUser(u, filterWord, replacementWord);
            u.addFilter(filterWord, replacementWord);

            JOptionPane.showMessageDialog(null, "Filter has been added");
        }
    }

    private static void chooseStoreToMail(User user, ArrayList<Store> stores, Customer receiver){
        if (stores.size() == 0)
            JOptionPane.showMessageDialog(null, "No stores exist to message.", "Error", JOptionPane.WARNING_MESSAGE);
        else {
            String [] storeOptions = new String[stores.size()];
            for (int i = 0; i < stores.size(); i++) {
                storeOptions[i] = ( (i+1) + ". " + stores.get(i).getStoreName());
            }
            String select3 = (String) JOptionPane.showInputDialog(null,
                    "Select which store you would like to message with.", "Store Choices",
                    JOptionPane.PLAIN_MESSAGE, null, storeOptions, null);
            if (select3 == null) return;

            int num = -1;
            for(int i = 0; i < storeOptions.length; i++){
                if(storeOptions[i].equals(select3)){
                    num = i;
                }
            }
            String message = JOptionPane.showInputDialog(null,
                    "What message would you like to send?", "New Message", JOptionPane.OK_OPTION);
            if (message == null) return;

            if (user instanceof Customer)
                client.sendNewMessage(user, stores.get(num).getSeller(), message, false,
                        (Customer) user, stores.get(num));
            else
                client.sendNewMessage(user, receiver, message, false,
                        receiver, stores.get(num));
            JOptionPane.showMessageDialog(null, "Your message is sent! Thank you.",
                    "Message sent", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void viewConversations(User u){
        ArrayList<Conversation> convs = client.getConversationsForUser(u);
        if (convs.size() == 0)
            JOptionPane.showMessageDialog(null, "There are no conversations to view", "Error", JOptionPane.WARNING_MESSAGE );
        else {
            while(true){
                int i = printConversationList(convs, u);
                if(i == -2){
                    return;
                } else if ((i > convs.size() - 1) || (i == -1)) {
                    JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.WARNING_MESSAGE);
                    continue;
                } else {
                    //System.out.println(convs.get(i-1).getMessageString());
                    //ArrayList<Message> temp = client.getMessagesForUser(convs.get(i), u);
                    messageMenu(u, convs.get(i));
                    break;
                }
            }
        }
    }

    private static void messageMenu(User current, Conversation conversation) {
        ArrayList<Message> messages = client.getMessagesForUser(conversation, current);

        JDialog frame;
        frame = new JDialog(commonFrame, true);
        frame.setTitle(String.format("Conversation between %s and %s", conversation.getCustomer().getName(), conversation.getSeller().getName()));

        Container content = frame.getContentPane();
        //frame.setLocation(1000,-1100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(messages.size() + 1, 1));
        int count = 50;
        if (messages.size() == 0)
            JOptionPane.showMessageDialog(null, "There are no messages to view", "Error", JOptionPane.WARNING_MESSAGE);
        else {
            ArrayList<JButton> buttons = new ArrayList<JButton>(messages.size());
            for (int i = 0; i < messages.size(); i++) {
                count += 50;
                String temp = current.applyFilters(messages.get(i).getMessage());
                if(messages.get(i).getSender().equals(current)){
                    JPanel message = new JPanel();
                    JLabel msgLabel = new JLabel("[" + messages.get(i).getSender().getName() + "]");
                    int labelWidth = messages.get(i).getSender().getName().length();
                    msgLabel.setSize(labelWidth, 20);
                    JButton msg = new JButton(temp);
                    buttons.add(msg);
                    int messageWidth = temp.length() * 20;
                    msg.setSize(messageWidth, 20);
                    message.add(msgLabel);
                    message.add(msg);

                    panel.add(message);
                } else{
                    JPanel message = new JPanel();
                    JButton msg = new JButton(temp);
                    buttons.add(msg);
                    JLabel msgLabel = new JLabel("[" + messages.get(i).getSender().getName() + "]");
                    int labelWidth = messages.get(i).getSender().getName().length();
                    msgLabel.setSize(labelWidth, 20);
                    int messageWidth = temp.length() * 20;
                    msg.setSize(messageWidth, 20);
                    message.add(msg);
                    message.add(msgLabel);
                    panel.add(message);
                }

            }
            JPanel panel2 = new JPanel();
            JLabel newMessageBox = new JLabel(String.format("[" + current.getName() + "]"));
            newMessageBox.setSize(current.getName().length(), 20);
            JTextField newMessage = new JTextField(10);
            JButton send = new JButton("Send");
            ActionListener messageMenuActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (button.equals(send)) {
                        frame.dispose();
                        String text = newMessage.getText();
                        client.addMessageToConversation(conversation, current, text, false);
                        //client.sendNewMessage(current, conversation.getSeller(), text, false,
                        //        (Customer) conversation.getCustomer(), conversation.getStore());
                        //Message m = new Message(conversation.getCustomer(), conversation.getSeller(), text, false);
                        //messages.add(m);
                        messageMenu(current, conversation);
                    }
                    if(buttons.contains(button)) {
                        int i = buttons.indexOf(button);
                        Message temp = messages.get(i);
                        if (temp.getSender().equals(current)) {
                            String[] options = {"Edit Message", "Delete Message"};
                            String choice = (String) JOptionPane.showInputDialog(null,
                                    "What would you like to do with this message", "Message Options",
                                    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                            if (choice == null) {

                            } else if (choice.equals(options[0])) {
                                String newMsg = (String) JOptionPane.showInputDialog(null,
                                        "What would you like this message to say?",
                                        "Edit Message", JOptionPane.INFORMATION_MESSAGE);
                                client.editMessage(conversation, temp, newMsg.trim(), current);
                                //Message newMsg2 = new Message(conversation.getCustomer(), conversation.getSeller(), newMsg.trim(), false);
                                //messages.set(messages.indexOf(temp), newMsg2);
                                frame.dispose();
                                messageMenu(current, conversation);
                            } else if (choice.equals(options[1])) {
                                client.deleteMessage(conversation, temp, current);
                                frame.dispose();
                                //messages.remove(temp);
                                messageMenu(current, conversation);
                            }

                        }
                    }
                }
            };
            for(JButton jb: buttons){
                jb.addActionListener(messageMenuActionListener);
            }
            send.addActionListener(messageMenuActionListener);
            //newMessage.setSize(400,20);
            panel2.add(newMessageBox);
            panel2.add(newMessage);
            panel2.add(send);
            panel.add(panel2);
        }
        content.add(panel);
        //frame.setSize(300, count);
        frame.pack();
        frame.setLocationRelativeTo(commonFrame);
        frame.setVisible(true);
    }

    private static int printConversationList(ArrayList<Conversation> conversations, User user){
        String header = String.format("%30s %30s %30s %20s\n", "Customer", "Seller", "Store", "New");
        ArrayList<String> list = new ArrayList<String>();
        boolean sentinel;
       
        
        ArrayList<User> blocked = user.getBlockedUsers();

        for (int i = 0; i < conversations.size(); i++) {
        	
        	sentinel = true;
        	String currCust = conversations.get(i).getCustomer().getName();
        	String currSell = conversations.get(i).getSeller().getName();
        	
        	
        	for(int j = 0; j < blocked.size(); j ++) {
        		
        		if (currCust.equals(blocked.get(j).getName()) || currSell.equals(blocked.get(j).getName()))
        			sentinel = false;
        	}
        	
        	if (sentinel) {
        		list.add(String.format (" %30s %30s %30s %3s\n", conversations.get(i).getCustomer().getName(),
        				conversations.get(i).getSeller().getName(), conversations.get(i).getStore().getStoreName(),
        				conversations.get(i).hasUserRead(user) ? "N" : "Y"));
        	}

        }
        
        String finalList[] = list.toArray(new String[0]);
        
        String select3 = (String) JOptionPane.showInputDialog(null,
                String.format("Select which conversation you would like to access.%n%s", header), "Conversation Choices",
                JOptionPane.PLAIN_MESSAGE, null, finalList, null);

        if(select3 == null){
            //customerMenu(user);
            return -2;
        }

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(select3)){
                return i;
            }
        }
        return -1;
    }

    private static void blockUser(User user){
        ArrayList<User> users;
        String search = (String) JOptionPane.showInputDialog(null, "Please search user you want to block:", "Block User", JOptionPane.INFORMATION_MESSAGE);
        if(search == null){
            return;
        }
        if (user instanceof Customer)
            users = client.searchSellerByUser(search, user);
        else
            users = client.searchCustomerByUser(search, user);
        if (users.size() == 0)
            JOptionPane.showMessageDialog(null, "Error: No Users Found", "Error", JOptionPane.WARNING_MESSAGE);
        else {
            String[] userList = new String[users.size()];
            for (int i = 0; i < users.size(); i++) {
                userList[i] = users.get(i).getName();
            }
            String u = (String) JOptionPane.showInputDialog(null, "Please select a user to block", "Block User",
                    JOptionPane.INFORMATION_MESSAGE, null, userList, userList[0]);
            if (u == null){
                return;
            }
            int choice = -1;
            for(int i = 0; i < userList.length; i++){
                if(userList[i].equals(users.get(i).getName())){
                    choice = i;
                }
            }
            client.blockUser(user, users.get(choice));
            //user.blockUser(user, users.get(choice));
            
            JOptionPane.showMessageDialog(null, "Block set!", "Block User", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void refreshNewMessageLabel(User user) {
        if (client.existsUnreadMessagesForUser(user))
            newMessagesLabel.setText("<html><center>" + newMessagesLabel.getText()
                    + "<br>You have NEW MESSAGES</center></html>");
        else
            newMessagesLabel.setText("Welcome " + user.getName());
    }

    @Override
    public void run() {
        User user = null;
        while(user == null) {
        JOptionPane.showMessageDialog(null, "Welcome to the messaging application!",
                "Messenger", JOptionPane.INFORMATION_MESSAGE);
        user = signInOrCreateAccount();
        }
        if(user instanceof Customer){
            customerMenu(user);
        } else if(user instanceof Seller){
            sellerMenu(user);
        }

    }

    public auxDash(){

    }


}
