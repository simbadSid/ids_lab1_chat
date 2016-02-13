package gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;






public class ActionListenerSend implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerSend(GuiController gui, ChatServerInterface server)
	{
		this.gui	= gui;
		this.server	= server;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String message		= gui.getWrittenMessage();
		String conversation	= gui.getConversationName();
		String userName		= gui.getUserName();
		String title		= "Login error";
		ChatServerAnswer serverAnswer = null;

		try
		{
			serverAnswer = server.AddMessage(userName, message, conversation);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}
		if (serverAnswer == ChatServerAnswer.SERVER_OK)
		{
			this.gui.removeWrittenMessage();
		}
		else
		{
			this.gui.printServerError(serverAnswer, title);
		}
	}
}