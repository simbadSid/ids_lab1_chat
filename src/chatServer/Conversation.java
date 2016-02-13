package chatServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashSet;


public class Conversation implements Serializable
{
// ----------------------------------
// Attributes
// ----------------------------------
	private static final long	serialVersionUID		= 1L;	// Serial key

	private String conversationName;
	private HashSet<String> userSet;
	private LinkedList<String> history;
	private final Lock lock = new ReentrantLock();

// ----------------------------------
// Builder
// ----------------------------------
	public Conversation(String conversationName, String userName)
	{
		this.conversationName = new String(conversationName);
		this.userSet = new HashSet<String>();
		this.userSet.add(userName);
		this.history = new LinkedList<String>();
	}

	public Conversation(String conversationName, String userName, Scanner in)
	{
		this.conversationName = new String(conversationName);
		this.userSet = new HashSet<String>();
		this.userSet.add(userName);
		this.history = new LinkedList<String>();
		this.addHistory(in);
	}

	// Clone methodes
	public Conversation()
	{
	}
	public void cloneConversation(Conversation conversation)
	{
		this.conversationName	= new String(conversation.conversationName);
		this.userSet			= new HashSet<String>(conversation.userSet);
		this.history			= new LinkedList<String>(conversation.history);
	}

// ----------------------------------
// Getter
// ----------------------------------
	public String getConversationName() {
		return new String(this.conversationName);
	}

	public HashSet<String>	getUserName() {
		return new HashSet<String>(this.userSet);
	}

// ----------------------------------
// Local methods
// ----------------------------------
	public void	addUser(String userName) {
		if (!this.isUserInConversation(userName)) {
			this.userSet.add(userName);
		}
	}

	public void	addMessage(String message, String userName) {
		if (!this.isUserInConversation(userName)) {
			return;
		}
		lock.lock();
		BufferedWriter out;
		try {
			out = new BufferedWriter(
				new FileWriter(User.userConversationsDir + this.conversationName, true)
			);
			out.write(message);
			out.close();
		} catch (IOException e) {
			lock.unlock();
			e.printStackTrace();
		}
		this.history.add(message);
		lock.unlock();
	}

	public void addHistory(Scanner in) {
		while (in.hasNextLine()) {
            this.history.add(in.nextLine());
        }
        in.close();
	}

	public boolean isUserInConversation(String userName) {
		return this.userSet.contains(userName);
	}

}
