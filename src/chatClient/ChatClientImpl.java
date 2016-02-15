package chatClient;

import gui.GuiController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import chatServer.ChatServerInterface;
import chatServer.Conversation;







public class ChatClientImpl implements ChatClientInterface, Runnable
{
// ----------------------------------
// Attributes
// ----------------------------------
	private static final long	serialVersionUID = 1L;	// Serial key
	private GuiController		gui;

// ----------------------------------
// Builder
// ----------------------------------
	@Override
	public void run()
	{
	}

	public ChatClientImpl(ChatServerInterface server)
	{
		ChatClientInterface	client_stub = null;
		try
		{
			client_stub	= (ChatClientInterface) UnicastRemoteObject.exportObject(this, 0);
		}
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}

		// Init the graphical interface
		this.gui			= new GuiController(server, client_stub);
		this.gui.setCurrentPanel(GuiController.PANEL_LOGIN_ID);
	}

// ----------------------------------
// Local public methodes
// ----------------------------------
	@Override
	public void updateCurrentConversationParticipants(Conversation conv) throws RemoteException
	{
		if (!gui.getConversationName().equals(conv.getConversationName())) return;
		this.gui.setCurrentConversation(conv);
	}

	@Override
	public void updateCurrentConversationHistory(Conversation conv) throws RemoteException 
	{
		this.gui.addExchangedMessage(conv);
	}
}