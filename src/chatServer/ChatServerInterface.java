package chatServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import chatClient.ChatClientInterface;
import chatServer.ChatServerAnswer;


public interface ChatServerInterface extends Remote
{
	public ChatServerAnswer CreateUser			(String userName, String password) throws RemoteException ;
	public ChatServerAnswer LoginUser			(String userName, String password, ChatClientInterface client) throws RemoteException;
	public ChatServerAnswer LogoutUser			(String userName) throws RemoteException;
	public ChatServerAnswer CreateConversation	(String userName, String convName) throws RemoteException ;
	public Conversation		JoinConversation	(String userName, String convName) throws RemoteException ;
	public ChatServerAnswer AddMessage			(String userName, String message, String convName) throws RemoteException ;
	public String[]			getConversations	(String userName) throws RemoteException ;
}
