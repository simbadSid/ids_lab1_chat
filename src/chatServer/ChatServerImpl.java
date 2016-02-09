package chatServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;


public class ChatServerImpl implements ChatServerInterface
{
// ----------------------------------
// Attributes:
// ----------------------------------
	private HashMap<String, User>			userSet;
	private HashMap<String, Conversation>	conversationSet;

// ----------------------------------
// Builder
// ----------------------------------
	public ChatServerImpl ()
	{
		this.userSet			= new HashMap<String, User>();
		this.conversationSet	= new HashMap<String, Conversation>();
	}

// ----------------------------------
// Local methods
// ----------------------------------
	@Override
	public ChatServerAnswer CreateUser(String userName, String password) {
		if (this.userSet.containsKey(userName))	{
			return ChatServerAnswer.SERVER_USER_ALREADY_EXIST;
		}
		User newUser = new User(userName, password);
		this.userSet.put(userName, newUser);
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer CreateConversation(String userName, String convName) {
		if (!this.userSet.containsKey(userName)) {
			return ChatServerAnswer.SERVER_USER_UNKNOWN;
		}
		if (this.conversationSet.containsKey(convName))	{
			return ChatServerAnswer.SERVER_CONVERSATION_ALREADY_EXIST;
		}
		String fileName = User.userConversationsDir + convName;
		Scanner in;
		try {
			in = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			this.conversationSet.put(convName, new Conversation(convName, userName));
			return ChatServerAnswer.SERVER_OK;
		}
		this.conversationSet.put(convName, new Conversation(convName, userName, in));

		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer JoinConversation(String userName, String convName) {
		if (!this.userSet.containsKey(userName)) {
			return ChatServerAnswer.SERVER_USER_UNKNOWN;
		}
		if (!this.conversationSet.containsKey(convName)) {
			return ChatServerAnswer.SERVER_CONVERSATION_UNKNOWN;
		}

		Conversation	conv	= this.conversationSet.get(convName);
		User			user	= this.userSet.get(userName);

		user.addConversation(convName);
		conv.addUser(user.getUserName());
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer AddMessage(String message, String convName, String userName) {
		if (!this.conversationSet.containsKey(convName)) {
			return ChatServerAnswer.SERVER_CONVERSATION_UNKNOWN;
		}
		Conversation currConv = this.conversationSet.get(convName);
		if (!currConv.isUserInConversation(userName)) {
			return ChatServerAnswer.SERVER_USER_UNKNOWN;
		}
		currConv.addMessage(message, userName);
		return ChatServerAnswer.SERVER_OK;
	}
}
