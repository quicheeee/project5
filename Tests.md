TEST #1: Seller account creation

Steps:

User launches application.
In the drop-down menu, User selects "Create an Account".
User enters info into all fields (name, email, username) and is a Seller.
User names their store "The Krabby Patty".
User selects the "Ok" button.
User exits the application.

TEST #2: New Customer creation

Steps:

User launches application
User selects "Create an Account" from drop down menu
User enters information into text fields (name, email, username) and is a customer.
User selects "Ok" button.
User exits application.

TEST #3: Second Seller account creation

Steps:

User launches application.
In the drop-down menu, User selects "Create an Account".
User enters info into all fields (name, email, username) and is a new Seller.
User names their store "Bob's Burgers".
User selects the "Ok" button.
User exits the application.
TEST #4: Second Customer creation

Steps:

User launches application
User selects "Create an Account" from drop down menu
User enters information into text fields (name, email, username) and is a new customer.
User selects "Ok" button.
User exits application.

Test #5: Customer 2 message Seller 2

Steps:

Customer 2 launches application
Selects "Sign in" and inputs
User searches and selects "Seller 2" and sends message "hi".
User closes application

Test #6: Customer 2 message Seller 1

Steps:

Customer 2 login,
See the "You have NEW Messages" indicator,
Select to view messages,
Select the message from Seller 1 to view
Send a new message --Search for a seller to message using their email --Select Seller 1 to message from results --Sends "greetings"
Select to Edit Message --Select the message to Seller 1

Test #7: User deletes message

Steps:

User logs in views messages
User puts an invalid input
User then views correct message
User then deletes the message.
User then views the message to find there are none, then imports a .txt file to the conversation but is incorrect
User then blocks user (seller) and then tries to adds invalid input

Test #8: User checks for zero message

Steps:

User logs in views messages
User puts an invalid input
User then views correct message
User then deletes the message.
User then views the message to find there are none, then imports a .txt file to the conversation but is incorrect
User then blocks user (seller) and then tries to adds invalid input

Test #8: test for concurrency

Steps:

Customer 1 logs in from one device
Customer 2 logs in from another device
Customer 1 messages Seller 1 while Customer 2 messages Seller 2
Both send their messages
Both view conversations and see their sent messages
Both close the application
