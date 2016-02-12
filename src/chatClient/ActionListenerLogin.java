package chatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;





public class ActionListenerLogin implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private PanelLogin			panelLogin;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerLogin(PanelLogin panelLogin, ChatServerInterface server)
	{
		this.panelLogin	= panelLogin;
		this.server		= server;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String userName	= panelLogin.getUserName();
		String password	= panelLogin.getPassword();

		try
		{
			ChatServerAnswer answer = server.LoginUser(userName, password);
			if (answer != ChatServerAnswer.SERVER_OK)	throw new Exception();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
