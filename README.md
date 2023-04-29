# CS180 Project 5 Repository

# How to compile and run the Code.

To run the code first you need to just download the files. Next import them in IntelliJ. Finally, run the file named "SharingServer.java" to start the server, and then run the file titled "auxDash.java" to run the main program.

# Who submitted what

Meha Kavoori - Submitted Report on Brightspace. Amelia Williams - Submitted Vocareum workspace.

# Class Descriptions

## Conversation class

This class represents a conversation object. The conversation class represents the invidual conversations, sent
between users. The
Class's constructor consists of a Customer, Seller and a Store. The class utilizes addMessage methods to add individual
messages to the
conversations. The class also implements serializable and has been tested with other classes through test cases.
## User Class

This class is the parent class for all other types of users. The class implements the Serializable interface.
The class has methods which allow the user to set and get the email address, name and
password. The methods also can check other blocked users, as well as write user data to a text file.
The User class has been tested through integration
with its child classes Customer and Seller.
## Customer Class

The customer class represents a customer user and extends the User class and implements Serializable interface.
The customer class extends all the User Class' methods and has additional
ones like a can message method which checks if the Customer can message another user.
The customer has a private field store List which is a list of
sellers that the customer can add to. This class has been tested through integration
with the parent user class' methods.
## Seller Class

The seller class represents a seller user and extends the User class. The seller class extends the customer class
and has additional methods like the Customer class. The Seller alo has a private field which is a list of Customers
that it may add to and edit. The seller class also has been tested through integration with the extended methods
in the User class.
## Auxiliary Dashboard Class

This class acts as the graphical user interface for the user. The class houses the main method which
executes all the called methods within the class. In one way or another, the auxDash class interacts with all
the other classes and methods in some way. The auxDash class has been tested through integration with all
other methods and classes to ensure correct functionality. These tests include multiple test cases.
## Messenger Class

This class holds some of the methods to manipulate how and which messages are sent between users when it comes to
manipulating different files. It has methods such as writeMessages(), findRelatedMessages() and deleteMessagesForUser().
These methods each deal with reading files created from information gathered from the Server class. The messenger class has been
tested through test cases where messages were exchanged between users. The messenger class also implements the
serializable class.
## Message Class
The message class represents the individual messages sent between different users. Its constructor takes in parameters
 which are the sender and reciever--represented by User objects--and then the individual message represented as a String, and a boolean
value to represent if the message is one which disappears. The class has been tested in its functionality and how it works with other classes.

## Sharing Client class

The Sharing Client class acts as the class which represents an individual 
client. This class also houses methods which deal in editing or sending messages. 
The class is called in the main method of auxDash to enable connection to the server as well as communication 
between clients. This class has been tested to allow for concurrency, in the case that to users using the class on different devices, want ot message each other.


## Store Class

This class represents the Store object. Its constructor takes the parameters of the store name and the store's
seller. it has methods that get how many customers have been in contact with it as well as void methods which print
out the sorted values desired by the user. The store class
implements Serializable. The Store class has also been tested with through local test cases.
## Sharing Server Class
This class acts as the class which stores, retrieves, and sends out all data related to users using the program.
The server stores its data by Array Lists of all of the users. The class utilizes methods which involve searching by queries selected by dropdown menus.
The class has been tested in conjunction with the Sharing Client class and its reliability can be verified by test cases.
