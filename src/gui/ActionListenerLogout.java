package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;





public class ActionListenerLogout implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerLogout(GuiController gui, ChatServerInterface server)
	{
		this.gui		= gui;
		this.server		= server;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String userName	= gui.getUserName();
		String title	= "Logout error";
		ChatServerAnswer serverAnswer = null;

		try
		{
			serverAnswer = server.LogoutUser(userName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}

		if (serverAnswer == ChatServerAnswer.SERVER_OK)
		{
			this.gui.setCurrentPanel(GuiController.PANEL_LOGIN_ID);
		}
		else
		{
			this.gui.printServerError(serverAnswer, title);
		}
	}
}
