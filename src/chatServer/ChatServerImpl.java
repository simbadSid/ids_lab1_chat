package chatServer;

import java.rmi.RemoteException;
import java.util.HashMap;









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
	public ChatServerAnswer CreateUser(String userName, String password) throws RemoteException
	{
		if (this.userSet.containsKey(userName))	return ChatServerAnswer.SERVER_USER_ALREADY_EXIST;

		User newUser = new User(userName, password);
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer CreateConversation(String userName, String convName) throws RemoteException
	{
		if (!this.userSet.containsKey(userName))		return ChatServerAnswer.SERVER_USER_UNKNOWN;
		if (this.conversationSet.containsKey(convName))	return ChatServerAnswer.SERVER_CONVERSATION_ALREADY_EXIST;

		this.conversationSet.put(convName, new Conversation(convName));
		return ChatServerAnswer.SERVER_OK;
	}

	@Override
	public ChatServerAnswer JoinConversation(String userName, String convName) throws RemoteException
	{
		if (!this.userSet.containsKey(userName))		return ChatServerAnswer.SERVER_USER_UNKNOWN;
		if (!this.conversationSet.containsKey(convName))return ChatServerAnswer.SERVER_CONVERSATION_UNKNOWN;

		Conversation	conv	= this.conversationSet.get(convName);
		User			user	= this.userSet.get(userName);

		user.addConversation(convName);
		conv.addUser(user);
		
		
		
throw new RuntimeException("Not implelented yet");
	}

	@Override
	public ChatServerAnswer LoginUser(String userName, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatServerAnswer SendMessage(String userName, String convName,
			String message) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
