package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chatClient.ChatClientInterface;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;





public class ActionListenerLogin implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;
	private ChatClientInterface	client;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerLogin(GuiController gui, ChatServerInterface server, ChatClientInterface client)
	{
		this.gui		= gui;
		this.server		= server;
		this.client		= client;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String userName	= gui.getUserName();
		String password	= gui.getUserPassword();
		String title	= "Login error";
		ChatServerAnswer serverAnswer = null;

		try
		{
			serverAnswer = server.LoginUser(userName, password, client);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}

		if (serverAnswer == ChatServerAnswer.SERVER_OK)
		{
			this.gui.setCurrentPanel(GuiController.PANEL_CHAT_ID);
		}
		else
		{
			this.gui.printServerError(serverAnswer, title);
		}
	}
}
