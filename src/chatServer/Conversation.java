package chatServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conversation
{
// ----------------------------------
// Attributes
// ----------------------------------
	private String				conversationName;
	private LinkedList<User>	userList;
	private LinkedList<String>	history;
	private final Lock lock = new ReentrantLock();

// ----------------------------------
// Builder
// ----------------------------------
	public Conversation(String conversationName)
	{
		this.conversationName	= new String(conversationName);
		this.userList			= new LinkedList<User>();
		this.history			= new LinkedList<String>();
	}

	public Conversation(String conversationName, Scanner in)
	{
		this.conversationName	= new String(conversationName);
		this.userList			= new LinkedList<User>();
		this.history			= new LinkedList<String>();
		this.addHistory(in);
	}

// ----------------------------------
// Getter
// ----------------------------------
	public String getConversationName() {
		return new String(this.conversationName);
	}

	public LinkedList<User>	getUserNameList() {
		return new LinkedList<User>(this.userList);
	}

// ----------------------------------
// Local methods
// ----------------------------------
	public void	addUser(User user) {
		this.userList.add(user);
	}

	public void	addMessage(String message) {
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

}
