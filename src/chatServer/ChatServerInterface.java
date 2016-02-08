package chatServer;

import java.rmi.Remote;
import java.rmi.RemoteException;





public interface ChatServerInterface extends Remote
{
	public ChatServerAnswer CreateUser			(String userName, String password)	throws RemoteException;
	public ChatServerAnswer LoginUser			(String userName, String password)	throws RemoteException;
	public ChatServerAnswer CreateConversation	(String userName, String convName)	throws RemoteException;
	public ChatServerAnswer JoinConversation	(String userName, String convName)	throws RemoteException;
	public ChatServerAnswer SendMessage			(String userName, String convName, String message)	throws RemoteException;

}
