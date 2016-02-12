package chatClient;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;






public class ActionListenerSend implements ActionListener
{
// ------------------------------------------
// Attributes:
// ------------------------------------------
	private PanelChat			panelChat;
	private ChatServerInterface	server;

// ------------------------------------------
// Builder:
// ------------------------------------------
	public ActionListenerSend(PanelChat panelChat, ChatServerInterface server)
	{
		this.panelChat	= panelChat;
		this.server		= server;
	}

// ------------------------------------------
// Local methods:
// ------------------------------------------
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String message		= panelChat.getWrittenMessage();
		String conversation	= panelChat.getConversationName();
		String userName		= panelChat.getUserName();

		try
		{
			ChatServerAnswer answer = server.AddMessage(message, conversation, userName);
			if (answer != ChatServerAnswer.SERVER_OK) throw new Exception();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
