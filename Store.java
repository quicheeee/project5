package pj5;
import java.io.*;
import java.util.ArrayList;

public class Store implements Serializable {
    private Seller seller;
//    private List<Messenger2> messages;
    private String storeName;

    private static final long serialVersionUID = -664171850626483574L;


    public static ArrayList<Store> getAllStores() {
        ArrayList<Store> all = new ArrayList<Store>();
        for (User u : User.getUsers()) {
            if (u instanceof Seller) {
                all.addAll(((Seller) u).getListOfStores());
            }
        }
        return all;
    }

    public static ArrayList<Store> getAllStoresForUser (User u) {
        ArrayList<Store> all = new ArrayList<Store>();

        for (User temp : User.getUsers()) {
            if (temp instanceof Seller) {
                if (!(temp.getBlockedUsers().contains(u) || u.getBlockedUsers().contains(temp)))
                    all.addAll(((Seller) temp).getListOfStores());
            }
        }
        return all;
    }

    public Store(Seller seller, String storeName) {

        this.seller = seller;
        //this.messages = new ArrayList<>();
        this.storeName = storeName;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        return getStoreName().equals(store.getStoreName());
    }

    public Seller getSeller() {
        return seller;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

/*
    public void addMessages(Messenger2 message) {
        messages.add(message);
    }

    public int getNumMessages() {
        return messages.size();
    }


    public int getNumMessagesSentByCus(Customer customer) {
        int count = 0;
        for (Messenger2 message : messages) {
            // gets the number of messages sent by the customer
            if (message.getCustomer() == customer) {
                count++;
            }
        }
        return count;
    }

    public int getNumMessagesSentBySell(Seller seller) {
        int count = 0;
        for (Messenger2 message : messages) {
            //gets number of messages sent by seller
            if (message.getSeller() == seller) {
                count++;
            }
        }
        return count;
    }

    public List<Customer> getCustomers() {
        //not sure if needed but puts the customers into list --> might delete later
        List<Customer> customers = new ArrayList<>();
        for (Messenger2 message : messages) {
            customers.add(message.getCustomer());
        }
        return customers;

    }

    public Map<String, Integer> getMostCommonWordsCust() {
        Map<String, Integer> commonWordsCust = new HashMap<>();
        for (Messenger2 message : messages) {
            // there should be a getContent method in messages that stores the
            //String[] words = message.getMessageList().split(" ");
            //for (String word : words) {
            //    commonWordsCust.put(word, commonWordsCust.getOrDefault(word, 0) + 1);
            //}
        }
        return commonWordsCust;
    }

    public Map<String, Integer> getMostCommonWordsSell() {
        Map<String, Integer> commonWordsSell = new HashMap<>();
        for (Messenger2 message : messages) {
            // there should be a getContent method in messages that stores the
            //String[] words = message.getMessageList().split(" ");
            //for (String word : words) {
            //    commonWordsSell.put(word, commonWordsSell.getOrDefault(word, 0) + 1);
            //}
        }
        return commonWordsSell;
    }

    public Map<Customer, Integer> getNumCustomerMessages() {
        Map<Customer, Integer> numCustomerMessages = new HashMap<>();
        for (Messenger2 message : messages) {
            Customer sender = message.getCustomer();
            if (sender instanceof Customer) {
                numCustomerMessages.put(sender, numCustomerMessages.getOrDefault(sender, 0) + 1);
            }
        }
        return numCustomerMessages;
    }
*/


    /* public int getMessagesSentToStore() {
        int numMessagesSent = 0;

        for (Messenger2 m : messages) {
            numMessagesSent = m.msgFromCustToStore(User.findUserWithEmail(customer.getEmail()), getStoreName());

        }
        return numMessagesSent;
    }
     */

/*
    public int getMessagesReceivedByStore() {
        int numMessagesReceived = 0;
        for (Messenger2 message : messages) {
            //if (message.getSender() == this.getStoreName() && message.getRecipient() == message.getCustomer()) {
            //    numMessagesReceived++;
            //}
        }
        return numMessagesReceived;

    }

    public Map<Seller, Integer> getNumSellerMessages() {
        Map<Seller, Integer> numSellerMessages = new HashMap<>();
        for (Messenger2 message : messages) {
            Seller sender = message.getSeller();
            if (sender instanceof Seller) {
                numSellerMessages.put(sender, getNumSellerMessages().getOrDefault(sender, 0) + 1);
            }
        }
        return numSellerMessages;
    }

    public List<Customer> CustomersSortedNumMessages() {
        List<Customer> customers = new ArrayList<>();
        for (Messenger2 message : messages) {
            Customer sender = message.getCustomer();
            if (sender instanceof Customer && !customers.contains(sender)) {
                customers.add(sender);
            }
        }
        Collections.sort(customers, Collections.reverseOrder());
        //customers.sort((c1, c2) -> getNumCustomerMessages().get(c2) - getNumCustomerMessages().get(c1));
        return customers;
    }

    public List<String> SortedCustCommonWords() {
        List<String> commonWords = new ArrayList<>((Collection) getMostCommonWordsCust());
        Collections.sort(commonWords, Collections.reverseOrder());
        return commonWords;
    }
*/

/*
    public void printCustomerDashboard(Customer customer) {
        //  Map<Customer, Integer> customers = getNumCustomerMessages();
        // Map<String, Integer> commonWords = getMostCommonWordsSell();
        // getStoreName method needs to be created that gets the name of each specific Store
        //for (Store s : seller.getListOfStores()) {
        System.out.println("Dashboard for customer");
        System.out.println("----------------");
        //TODO
        //List<Store> storesByMessagesReceived = new ArrayList<>(Customer.storeList);
        List<Store> storesByMessagesReceived = new ArrayList<Store>();
        List<Store> storesByMessagesSent = new ArrayList<>();
        System.out.println("List of stores by messages received:");
        for (Store store : storesByMessagesReceived) {
            int numMessagesReceived = store.getMessagesReceivedByStore();
            if (numMessagesReceived > 0) {
                System.out.println(store.getSeller() + "(" + store.getStoreName() +
                        "):" + numMessagesReceived + "messages");
            }
        }
        System.out.println();
        System.out.println("List of stores by number of messages sent by customer: ");
        for (Messenger2 m : messages) {
            int numMessagesSent = m.msgFromCustToStore((Customer) User.findUserWithEmail(customer.getEmail()), this);
            if (numMessagesSent > 0) {
                System.out.println(getSeller() + "(" + getStoreName() +
                        ")" + numMessagesSent + "messages");
            }
        }

    }

    public void printCustomerDashboardSorted(Customer customer) {
        List<Customer> customers = CustomersSortedNumMessages();
        Map<String, Integer> commonWords = getMostCommonWordsSell();
        // getStoreName method needs to be created that gets the name of each specific Store
        System.out.println("Dashboard for store " + getStoreName());
        System.out.println("----------------");
        System.out.println("Customers by message count: ");

        // for (Customer customer : customers) {
        // int messageCount =

        // }
    }

    public void printSellerDashboard(Seller seller) {
        Map<Customer, Integer> customers = getNumCustomerMessages();
        Map<String, Integer> commonWords = getMostCommonWordsSell();
        for (Store s : seller.getListOfStores()) {
            System.out.println("Store Dashboard - " + s.getStoreName());
            System.out.println("----------------");
            System.out.println("Total messages: " + s.getStoreName());
            System.out.println("List of customers: " + getCustomers());
            System.out.println("Customer Message Counts:");
            Map<Customer, Integer> customerMessagecounts = getNumCustomerMessages();
            List<Map.Entry<Customer, Integer>> customerMessageEntries = new ArrayList<>(customerMessagecounts.entrySet());
            //sorts the Message entries from the greatest amount to least amount
            //customerMessageEntries.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
            for (Map.Entry<Customer, Integer> entry : customerMessageEntries) {
                //System.out.println(entry.getKey().getUserID() + ":" + entry.getValue());
            }
            System.out.println("Most Common Words: ");
            Map<String, Integer> commonWordsCust = getMostCommonWordsCust();
            for (Map.Entry<String, Integer> entry : commonWordsCust.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }

    public void printSellerDashboardSorted(Seller seller) {
        List<Customer> customers = CustomersSortedNumMessages();
        Map<String, Integer> commonWords = getMostCommonWordsSell();
        for (Store s : seller.getListOfStores()) {
            System.out.println("Store Dashboard - " + s.getStoreName());
            System.out.println("----------------");
            System.out.println("Total messages: " + s.getNumMessages());
            System.out.println("List of customers: " + s.getCustomers());
            System.out.println("Customer Message Counts:");
            Map<Customer, Integer> customerMessagecounts = getNumCustomerMessages();
            List<Map.Entry<Customer, Integer>> customerMessageEntries = new ArrayList<>(customerMessagecounts.entrySet());
            //sorts the Message entries from the greatest amount to least amount
            customerMessageEntries.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
            for (Map.Entry<Customer, Integer> entry : customerMessageEntries) {
                System.out.println(entry.getKey().getEmail() + ":" + entry.getValue());
            }
            System.out.println("Most Common Words: ");
            Map<String, Integer> commonWordsCust = getMostCommonWordsCust();
            for (Map.Entry<String, Integer> entry : commonWordsCust.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }
*/

    public static boolean newStore(String name, Seller seller) {
        File list = new File("stores.ser");
        ArrayList<Store> stores = Store.listStores(list, Store.getNumStoresCreated());
        for(Store s: stores) {
            if(s.getStoreName().equals(name)){
                System.out.println("Store Name Taken");
                return false;
            }
        }
        Store store = new Store(seller, name);
        stores.add(store);
        Store.setNumStoresCreated(Store.getNumStoresCreated() + 1);
        Store.writeStores(stores, list);
        return true;
    }

    public static ArrayList<Store> listStores(File f, int fileLength) {
        ArrayList<Store> stores = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            for (int i = 0; i < fileLength; i++){
                Store st = (Store) ois.readObject();
                stores.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stores;
    }

    public static int getNumStoresCreated(){
        File f = new File("numStoresCreated.txt");
        try{
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            return Integer.parseInt(bfr.readLine());
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void setNumStoresCreated(int i){
        File f = new File ("numStoresCreated.txt");
        try{
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(i);
            pw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeStores(ArrayList<Store> stores, File f){
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(Store s1: stores) {
                oos.writeObject(s1);
            }
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
