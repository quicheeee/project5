package pj5;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
/**
 * Sharing Server
 *
 * This class represents the server users connect to.
 * The server stores information by user.
 *
 * @author Amelia Williams, Meha Kavoori, Anish Puri, Tyler Barnett
 *
 * @version 04/23/2023
 *
 */
// class constructor extends thread
public class SharingServer extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SharingServer(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(this.socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(this.socket.getInputStream());
    }
    // run method
    public void run() {
        //System.out.printf("Connection received from %s\n", socket);

        try {
            while (true) {
                String request = (String) in.readObject();

                switch (request) {
                    case "User.searchSellerByUser": {
                        searchSellerByUser();
                        break;
                    }
                    case "User.searchCustomerByUser": {
                        searchCustomerByUser();
                        break;
                    }
                    case "User.getCustomersByUser": {
                        getCustomersByUser();
                        break;
                    }
                    case "Exit": {
                        return;
                    }
                    default: {
                        executeMethod(request);
                        break;
                    }
                }
                
                out.reset();
            }

        } catch (Exception e) {
        	
           if (!e.getMessage().equals("Connection reset")) 
        	   e.printStackTrace();
           
        }
    }
    // executes given the method name
    public void executeMethod(String method) throws IOException, ClassNotFoundException, InvocationTargetException,
                                                    IllegalAccessException {

        String[] split = method.split("[.]");

        Class c = Class.forName("pj5." + split[0]);
        Method[] ms = c.getMethods();
        Method m = null;
        for (Method x : ms) {
            if (x.getName().equals(split[1])) {
                m = x;
                break;
            }
        }

        Object[] parameters = new Object[m.getParameterTypes().length];
        for (int i=0; i < parameters.length; i++) {
            parameters[i] = in.readObject();
        }

        Object result = m.invoke(null, parameters);

        // send back to client
        out.writeObject(result);
        out.flush();
    }
    // writes results of searched seller to user
    public void searchSellerByUser() throws IOException, ClassNotFoundException {
        String search = (String) in.readObject();
        User user = (User) in.readObject();

        ArrayList<User> results = User.searchSellerByUser(search, user);

        // send back to client
        out.writeObject(results);
        out.flush();
    }
    // writes results of searched customer to user
    public void searchCustomerByUser() throws IOException, ClassNotFoundException {
        String search = (String) in.readObject();
        User user = (User) in.readObject();

        ArrayList<User> results = User.searchCustomerByUser(search, user);

        // send back to client
        out.writeObject(results);
        out.flush();
    }
    // writes results of retrieved customer to user
    public void getCustomersByUser() throws IOException, ClassNotFoundException {
        User user = (User) in.readObject();

        ArrayList<User> results = User.getCustomersByUser(user);

        // send back to client
        out.writeObject(results);
        out.flush();
    }
    // starts server
    public static void startServer() {
        try {
            //Port number will be 3333
            ServerSocket serverSocket = new ServerSocket(3333);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                SharingServer server = new SharingServer(clientSocket);
                server.start();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // main method
    public static void main(String[] args) {
        //User.getUsers();
        SharingServer.startServer();
    }

}
