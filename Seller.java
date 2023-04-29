package pj5;

import java.io.File;
import java.util.ArrayList;
/**
 * Seller class
 * <p>
 * The Seller class represents a seller object which is a type of user that interacts with
 * users of its type and customers. An intance of the class is created with attributes name,
 * email and password.
 *
 * @author Amelia Williams, Meha Kavoori, Anish Puri, Tyler Barnett
 * @version 4/28/2023
 */
// class extends user
public class Seller extends User {
    private Customer[] customerList;

    private ArrayList<Store> listOfStores;

    private static final long serialVersionUID = 6387730094462016452L;

    // the constructor uses super for some of its fields because it extends User
    // User already contains some of these fields
    public Seller(String name, String email, String password) {
        super(name, email, password);
        listOfStores = new ArrayList<Store>();
    }
    // following method allows for a seller to add a store using a String of the store name
    public void addStore(String storeName) throws IllegalArgumentException {
        ArrayList<Store> allStores = Store.getAllStores();

        for (Store temp : allStores) {
            if (temp.getStoreName().equals(storeName))
                throw new IllegalArgumentException("Duplicate Store Name");
        }

        Store st = new Store(this, storeName);
        listOfStores.add(st);
    }

    // following method sets the customer list as a list
    public void setCustomerList(Customer[] customerList) {
        this.customerList = customerList;
    }
    // following method gets an array list of all the stores that exist
    public ArrayList<Store> getListOfStores() {
        return listOfStores;
    }
    // following method adds a customer by creating an array list that sets the customer list
    // into each element of the array list. A new customer is added to the arraylist then the entire
    // array list of customers is returned with the next for loop
    public void addCustomer(Customer c) {
        ArrayList<Customer> arr = new ArrayList<>();
        for (int i = 0; i < customerList.length; i++) {
            arr.set(i, this.customerList[i]);
        }

        arr.add(c);

        for (int i = 0; i < arr.size(); i++) {
            this.customerList[i] = arr.get(i);
        }
    }


    // following method gets the customer at a certain index from a file named "accounts.ser"
    public Customer getCustomer(int index){
        File f = new File("accounts.ser");
        int count = 1;

        return null;
    }

   
    public void displayCustomers(){
        File f = new File("accounts.ser");
        int count = 1;
        for(User u: getUsers()){
            if(u instanceof Customer){
                if(!this.getBlockedUsers().contains(u)) {
/*
                    if(!this.getBlockedBy().contains(u)) {
                        if(!u.getInvisibleTo().contains(this)) {
                            System.out.println(count++ + ". " + u.getEmail());
                        }
                    }
*/
                }
            }
        }
    }
    @Override
    // following method checks if an object is an instance of a seller before casting to a seller object to check
    // if the seller's email and password equals that of a seller that currently exists
    // and returns false if it is not equal
    public boolean equals(Object o){
        if(o instanceof Seller) {
            Seller seller = (Seller) o;
            if(seller.getEmail().equals(this.getEmail()) && seller.getPassword().equals(this.getPassword())) {
                return true;
            }
        }
        return false;
    }
    // checks if user can message given object
    public boolean canMessage(Object o) {
        if (o instanceof Seller || o instanceof Customer) {
            return true;
        }
        return false;
    }


   


    }
