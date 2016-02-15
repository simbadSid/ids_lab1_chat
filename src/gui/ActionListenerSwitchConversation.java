package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;
import chatServer.Conversation;






public class ActionListenerSwitchConversation implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private GuiController		gui;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerSwitchConversation(GuiController gui, ChatServerInterface server)
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
		String title	= "Conversation create error";
		String[] convNameList;

		try
		{
			convNameList = server.getConversations(userName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}
		if (convNameList == null)
		{
			this.gui.printServerError(ChatServerAnswer.SERVER_USER_UNKNOWN, title);
			return;
		}

		if (convNameList.length == 0)
		{
			String msg	= "There currently are no conversations";
			JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		String convName = gui.askUserToPickConversationName(convNameList);
		if (convName == null) return;
		Conversation conversation;
		try
		{
			conversation = server.JoinConversation(userName, convName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.gui.printConnexionError(title);
			return;
		}
		if (conversation == null)
		{
			this.gui.printServerError(ChatServerAnswer.SERVER_INTERNAL_ERROR, title);
		}
		else
		{
			this.gui.setCurrentConversation(conversation);
		}
	}
}
