package chatServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

import chatClient.ChatClientInterface;




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
	public ChatServerAnswer LoginUser(String userName, String password, ChatClientInterface client) throws RemoteException
	{
		User user = this.userSet.get(userName);
		if (user == null)	return ChatServerAnswer.SERVER_USER_UNKNOWN;

		user.setClient(client);
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public String[] getConversations(String userName) throws RemoteException
	{
		User user = this.userSet.get(userName);
		if (user == null)	return null;

		int size = this.conversationSet.keySet().size();
		String[] res = new String[size];
		int i = 0;
		for (String convName: this.conversationSet.keySet())
		{
			res[i] = convName;
			i ++;
		}

		return res;
	}

	@Override
	public ChatServerAnswer LogoutUser(String userName) throws RemoteException
	{
		User user = this.userSet.get(userName);
		if (user == null)	return ChatServerAnswer.SERVER_USER_UNKNOWN;

		user.setClient(null);
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer CreateConversation(String userName, String convName) throws RemoteException {
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
	public Conversation JoinConversation(String userName, String convName)  throws RemoteException {
		if (!this.userSet.containsKey(userName)) {
			return null;
		}
		if (!this.conversationSet.containsKey(convName)) {
			return null;
		}

		Conversation	conv	= this.conversationSet.get(convName);
		User			user	= this.userSet.get(userName);

		user.addConversation(convName);
		user.setCurrentConversation(convName);
		conv.addUser(user.getUserName());

		for (String otherUser:conv.getUserName())// Update the state of all the participants
		{
			if (otherUser.equals(userName)) continue;
			user = this.userSet.get(otherUser);
			user.getClient().updateCurrentConversationParticipants(conv);
		}
		return conv;
	}

	public ChatServerAnswer AddMessage(String userName, String message, String convName) throws RemoteException
	{
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
