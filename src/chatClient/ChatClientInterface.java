package chatClient;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import chatServer.Conversation;





public interface ChatClientInterface extends Remote, Serializable
{
	public void	updateCurrentConversationParticipants	(Conversation conv)	throws RemoteException;
	public void	updateCurrentConversationHistory		()					throws RemoteException;
}
