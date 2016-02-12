package chatServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chatServer.ChatServerAnswer;


public interface ChatServerInterface extends Remote
{
	public ChatServerAnswer CreateUser(String userName, String password) throws RemoteException ;
	public ChatServerAnswer LoginUser(String userName, String password) throws RemoteException;
	public ChatServerAnswer CreateConversation(String userName, String convName) throws RemoteException ;
	public ChatServerAnswer JoinConversation(String userName, String convName) throws RemoteException ;
	public ChatServerAnswer AddMessage(String message, String convName, String userName) throws RemoteException ;
}
