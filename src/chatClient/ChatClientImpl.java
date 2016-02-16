package chatClient;

import gui.GuiController;
import java.rmi.RemoteException;
import chatServer.ChatServerInterface;
import chatServer.Conversation;







public class ChatClientImpl implements ChatClientInterface, Runnable
{
// ----------------------------------
// Attributes
// ----------------------------------
	private static final long	serialVersionUID = 1L;	// Serial key
	private ChatServerInterface	server;
	private GuiController		gui;

// ----------------------------------
// Builder
// ----------------------------------
	@Override
	public void run()
	{
		// Init the graphical interface
		this.gui			= new GuiController(server, this);
		this.gui.setCurrentPanel(GuiController.PANEL_LOGIN_ID);
	}

	public ChatClientImpl(ChatServerInterface server)
	{
		this.server = server;
	}

// ----------------------------------
// Local public methodes
// ----------------------------------
	@Override
	public void updateCurrentConversationParticipants(Conversation conv) throws RemoteException
	{
		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		this.gui.setCurrentConversation(conv);
	}

	@Override
	public void updateCurrentConversationHistory() throws RemoteException 
	{
		throw new RuntimeException("Not implemented yet");
	}
}