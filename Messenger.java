package pj5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Messenger {
	private static final String FILENAME = "messages.ser";
	private static CopyOnWriteArrayList<Conversation> conversations = null;
	private static final Object staticMessengerLock = new Object();

	public static CopyOnWriteArrayList<Conversation> getConversations() {
		if (Messenger.conversations != null)
			return Messenger.conversations;

		ArrayList<Conversation> temp = new ArrayList<Conversation>();

		try {
			File f = new File(FILENAME);
			FileInputStream fis;
			ObjectInputStream ois;

			synchronized (Messenger.staticMessengerLock) {
				try {
					fis = new FileInputStream(f);
					ois = new ObjectInputStream(fis);

					while (true) {
						Conversation conversation = (Conversation) ois.readObject();
						temp.add(conversation);
					}
				} catch (EOFException ex) {
				} catch (FileNotFoundException ex2) {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Messenger.conversations = new CopyOnWriteArrayList<>(temp);
		return Messenger.conversations;
	}

	public static boolean sendNewMessage(User sender, User receiver, String message, Boolean disappearing,
									  		Customer customer, Store store) {
		try {
			Seller seller = store.getSeller();

			Conversation conv = findConversation(customer, seller, store);
			if (conv == null) {
				conv = new Conversation(customer, seller, store);
				CopyOnWriteArrayList<Conversation> temp = Messenger.getConversations();
				temp.add(conv);
			}
			conv.addMessage(sender, receiver, message, disappearing);

			Messenger.writeMessages();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static Conversation findConversation(Customer customer, Seller seller, Store store) {
		CopyOnWriteArrayList<Conversation> temp = Messenger.getConversations();
		for (Conversation conv : temp) {
			if (conv.equals(new Conversation(customer, seller, store)))
				return conv;
		}
		return null;
	}

	public static void writeMessages() {
		try {
			File f = new File(Messenger.FILENAME);

			synchronized (Messenger.staticMessengerLock) {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				for (Conversation conversation : Messenger.conversations) {
					oos.writeObject(conversation);
				}
				oos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Conversation> getConversationsForUser(User u) {
		CopyOnWriteArrayList<Conversation> temp = Messenger.getConversations();
		ArrayList<Conversation> results = new ArrayList<Conversation>();

		for (Conversation conversation : temp) {
			if (conversation.getCustomer().equals(u) || conversation.getSeller().equals(u)) {
				User other;
				if (conversation.getCustomer().equals(u))
					other = conversation.getCustomer();
				else
					other = conversation.getSeller();

				if (!(other.getBlockedUsers().contains(u) || u.getBlockedUsers().contains(other)))
					results.add(conversation);
			}
		}

		Comparator<Conversation> comparatorCustomer = new Comparator<Conversation>() {
			@Override
			public int compare(Conversation o1, Conversation o2) {
				if (o1.isReadCustomer() == o2.isReadCustomer())
					return 0;
				if (o1.isReadCustomer() && !o2.isReadCustomer())
					return 1;
				else
					return -1;
			}
		};
		Comparator<Conversation> comparatorSeller = new Comparator<Conversation>() {
			@Override
			public int compare(Conversation o1, Conversation o2) {
				if (o1.isReadSeller() == o2.isReadSeller())
					return 0;
				if (o1.isReadSeller() && !o2.isReadSeller())
					return 1;
				else
					return -1;
			}
		};

		if (u instanceof Customer)
			Collections.sort(results, comparatorCustomer);
		else
			Collections.sort(results, comparatorSeller);

		return results;
	}

	public static ArrayList<Message> getMessagesForUser(Conversation conversation, User user) {
		ArrayList<Message> temp =  conversation.getMessagesForUser(user);
		writeMessages(); //to write the read flags
		return temp;
	}

// void to boolean ?!
	public static boolean editMessage(Conversation conversation, Message m, String content, User user) {
		
		try {
			conversation.updateMessage(m, content, user);
			writeMessages();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static boolean deleteMessage(Conversation conversation, Message m, User current) {
		
		try {
			conversation.deleteMessage(m, current);
			writeMessages();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void deleteConversationsForUser(User user) {
		CopyOnWriteArrayList<Conversation> temp = Messenger.getConversations();
		ArrayList<Conversation> removeList = new ArrayList<Conversation>();

		for (Conversation m : temp) {
			if (m.getCustomer().equals(user) || m.getSeller().equals(user))
				removeList.add(m);
		}

		for (Conversation m : removeList) {
			temp.remove(m);
		}
		writeMessages();
	}

	public static boolean existsUnreadMessagesForUser(User user) {
		ArrayList<Conversation> temp = Messenger.getConversationsForUser(user);
		for (Conversation conversation : temp) {
			if (user instanceof Customer) {
				if (!conversation.isReadCustomer())
					return true;
			}
			if (user instanceof Seller) {
				if (!conversation.isReadSeller())
					return true;
			}
		}
		return false;
	}

	public static void addMessageToConversation(Conversation conversation, User sender, String message,
												boolean disappearing) {
		User receiver;
		if (sender instanceof Customer)
			receiver = conversation.getSeller();
		else
			receiver = conversation.getCustomer();

		conversation.addMessage(sender, receiver, message, disappearing);
		Messenger.writeMessages();
	}

}

