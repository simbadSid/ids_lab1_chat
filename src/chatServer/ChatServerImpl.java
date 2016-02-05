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
		return "CreateUser";
	}

	public ChatServerAnswer CreateConversation(String userName, String convName) throws RemoteException
	{
		throw new RuntimeException("Not implelented yet");
	}

	@Override
	public ChatServerAnswer JoinConversation(String userName, String convName) throws RemoteException
	{
		throw new RuntimeException("Not implelented yet");
	}

}
