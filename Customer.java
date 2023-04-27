package pj5;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements Serializable {
    private ArrayList<Store> storeList;

    public Customer(String name, String email, String password) {
        super(name, email, password);

    }

    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(ArrayList<Store> storeList) {
        this.storeList = storeList;
    }


    /*public void addSeller(Seller c) {
        ArrayList<Seller> arr = new ArrayList<>();
        for (int i = 0; i < storeList.length; i++) {
            arr.set(i, this.storeList[i]);
        }

        arr.add(c);

        for (int i = 0; i < arr.size(); i++) {
            this.storeList[i] = arr.get(i);
        }
    }
    
     */
    public void addStore(Store s) {
        storeList.add(s);
    }

    /*public String searchSeller(int sellerID) {
        String sellerEmail = "Customer Not Found";
        for (int i = 0; i < storeList.length; i++) {
            if (storeList[i].getSellerID() == sellerID) {
                sellerEmail = storeList[i].getEmail();
            }
        }
        return sellerEmail;
    }
     */
    public Seller getSeller(int index) {
        File f = new File("accounts.ser");
        int count = 1;
        for (User u : getUsers()) {
            if (u instanceof Seller) {
                if (!this.getBlockedUsers().contains(u)) {
                    //if (!this.getBlockedBy().contains(u)) {
                        if (count == index) {
                            return (Seller) u;
                        }
                        count++;
                //    }
                }
            }
        }
        return null;
    }

    public void displaySellers() {
        File f = new File("accounts.ser");
        int count = 1;
        for (User u : getUsers()) {
            if (u instanceof Seller) {
                if (!this.getBlockedUsers().contains(u)) {
/*
                    if (!this.getBlockedBy().contains(u)) {
                        if (!u.getInvisibleTo().contains(this)) {
                            System.out.println(count++ + ". " + u.getEmail());

                        }
                    }
*/
                }
            }
        }
    }

    // fix equals

    @Override // checks for customer objects with equal values
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            Customer customer = (Customer) o;
            if (customer.getEmail().equals(this.getEmail()) && customer.getPassword().equals(this.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean canMessage(Object o) {
        if (o instanceof Seller) {
            return true;
        }
        return false;
    }
    //public Message newMessage (); // in progress


}
