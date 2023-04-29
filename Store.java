package pj5;
import java.io.*;
import java.util.ArrayList;
/**
 * Store Class
 * <p>
 * The Store Class represents a Store object. The Store class has methods which allow users to
 * get, set, and access a list of Stores.
 *
 * @author Amelia Williams, Meha Kavoori, Anish Puri, Tyler Barnett
 * @version 4/28/2023
 */
public class Store implements Serializable {
    private Seller seller;
//    private List<Messenger2> messages;
    private String storeName;

    private static final long serialVersionUID = -664171850626483574L;

    // returns list of all store objects given all users
    public static ArrayList<Store> getAllStores() {
        ArrayList<Store> all = new ArrayList<Store>();
        for (User u : User.getUsers()) {
            if (u instanceof Seller) {
                all.addAll(((Seller) u).getListOfStores());
            }
        }
        return all;
    }
    // returns all store obects associated with inputted user
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
    // Store constructor
    public Store(Seller seller, String storeName) {

        this.seller = seller;
        //this.messages = new ArrayList<>();
        this.storeName = storeName;

    }

    @Override
    //checks if the given object is equivalent to the current object
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

    // given the inputted name and seller, method checks if store name already exists
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
    // given the inputted file and filelength, method returns an ArrayList of Stores
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
    // method reads numStoresCreated.txt file to get the number of stores created
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
    // method sets the number in the nmStoresCreated.txt file
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
    // given arrList of stores, the method will write to a file the list of the stores.
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
