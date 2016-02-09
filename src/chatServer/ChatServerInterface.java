package chatServer;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatServerInterface extends Remote
{
	public ChatServerAnswer CreateUser(String userName, String password);
	public ChatServerAnswer CreateConversation(String userName, String convName);
	public ChatServerAnswer JoinConversation(String userName, String convName);
	public ChatServerAnswer AddMessage(String message, String convName, String userName);
}
