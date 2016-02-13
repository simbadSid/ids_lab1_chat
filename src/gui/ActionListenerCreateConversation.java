package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;






public class ActionListenerCreateConversation implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerCreateConversation(GuiController gui, ChatServerInterface server)
	{
		this.gui			= gui;
		this.server			= server;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	public void actionPerformed(ActionEvent arg0)
	{
		String userName	= gui.getUserName();
		String convName	= gui.askUserConversationName();
		String title	= "Conversation create error";
		ChatServerAnswer serverAnswer = null;

		if (convName == null) return;

		try
		{
			serverAnswer = server.CreateConversation(userName, convName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}

		if (serverAnswer == ChatServerAnswer.SERVER_OK)
		{
			this.gui.setCurrentConversation(convName);
		}
		else
		{
			this.gui.printServerError(serverAnswer, title);
		}
	}
}
