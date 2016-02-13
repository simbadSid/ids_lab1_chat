package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;






public class ActionListenerCreateUser implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;
	private ActionListenerLogin	loginListener;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerCreateUser(GuiController gui, ChatServerInterface server, ActionListenerLogin loginListener)
	{
		this.gui			= gui;
		this.server			= server;
		this.loginListener	= loginListener;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String userName	= gui.getUserName();
		String password	= gui.getUserPassword();
		String title	= "User create error";
		ChatServerAnswer serverAnswer = null;

		try
		{
			serverAnswer = server.CreateUser(userName, password);
			if (serverAnswer == ChatServerAnswer.SERVER_OK)
			{
				loginListener.actionPerformed(arg0);
				return;
			}
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
