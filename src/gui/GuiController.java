package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import chatClient.ChatClientInterface;
import chatServer.ChatServerAnswer;
import chatServer.ChatServerInterface;
import chatServer.Conversation;





public class GuiController implements ComponentListener, Serializable
{
// ----------------------------------
// Attributes
// ----------------------------------
	private static final long	serialVersionUID		= 1L;	// Serial key
	public static final int		PANEL_LOGIN_ID			= 0;
	public static final int		PANEL_CHAT_ID			= 1;

	private static final String	FRAME_NAME				= "RMI Chat";
	private static final int	FRAME_WIDTH_DEFAULT		= 400;
	private static final int	FRAME_HEIGHT_DEFAULT	= 700;
	private static final int	FRAME_WIDTH_LOGIN		= 400;
	private static final int	FRAME_HEIGHT_LOGIN		= 700;
	private static final int	FRAME_WIDTH_CHAT		= 1000;
	private static final int	FRAME_HEIGHT_CHAT		= 700;

	private PanelLogin			loginPanel;
	private PanelChat			chatPanel;

	private JFrame				frame;
	private JPanel				currentPan;

// ----------------------------------
// Builder
// ----------------------------------
	public GuiController(ChatServerInterface server, ChatClientInterface client)
	{
		this.frame		= new JFrame(FRAME_NAME);
		this.loginPanel	= new PanelLogin(this, server, client);
		this.chatPanel	= new PanelChat(this, server);

		this.frame.setSize(FRAME_WIDTH_DEFAULT, FRAME_HEIGHT_DEFAULT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.addComponentListener(this);
		this.frame.setVisible(true);
	}

// ----------------------------------
// Getter / Setter
// ----------------------------------
	public String getUserName()			{return this.loginPanel.getUserName();}
	public String getUserPassword()		{return this.loginPanel.getPassword();}
	public String getConversationName()	{return this.chatPanel.getConversationName();}
	public String getWrittenMessage()	{return this.chatPanel.getWrittenMessage();}

	public void setCurrentConversation(String convName)
	{
		if (this.currentPan != this.chatPanel) return;
		this.chatPanel.setCuurentConversation(convName, this.getUserName());
	}

	public void setCurrentConversation(Conversation conv)
	{
		if (this.currentPan != this.chatPanel) return;
		this.chatPanel.setCuurentConversation(conv);
	}
	public void removeWrittenMessage()
	{
		this.chatPanel.removeWrittenMessage();
	}

// ----------------------------------
// Locla methods
// ----------------------------------
	public void setCurrentPanel(int newPanelId)
	{
		int newWidth, newHeight;

		switch(newPanelId)
		{
			case PANEL_LOGIN_ID:
				this.currentPan	= loginPanel;
				newWidth		= FRAME_WIDTH_LOGIN;
				newHeight		= FRAME_HEIGHT_LOGIN;
				break;
			case PANEL_CHAT_ID:
				this.currentPan	= chatPanel;
				newWidth		= FRAME_WIDTH_CHAT;
				newHeight		= FRAME_HEIGHT_CHAT;
				break;
			default:				throw new RuntimeException("Unknown panel id: " + newPanelId);
		}
		this.frame.setContentPane(this.currentPan);
		this.frame.setSize(newWidth, newHeight);
		this.currentPan.setSize(this.frame.getWidth(), this.frame.getHeight());
	}

	public String askUserConversationName()
	{
		String title	= "Conversation creation";
		String message	= "Please enter a conversation name";
		String res		= "";

		while (res.equals(""))
		{
			res = JOptionPane.showInputDialog(null, message, title, JOptionPane.NO_OPTION);
			if		(res == null)		return null;
			else if (!res.equals(""))	return res;

			String errorTitle	= "Conversation creation erroe";
			String errorMessage	= "The conversation name size must be bigger than 1";
			JOptionPane.showMessageDialog(null, errorMessage, errorTitle, JOptionPane.INFORMATION_MESSAGE);
			continue;
		}
		return res;
	}

	public String askUserToPickConversationName(String[] convNameList)
	{
		String msg	= "Please chose a conversation name";
		String title= "Conversation switch";

		String res = (String)JOptionPane.showInputDialog(
				null,
				msg,
				title,
				JOptionPane.YES_OPTION,
				null,
				convNameList,
				convNameList[0]);
		return res;
	}

	public void printServerError(ChatServerAnswer serverAnswer, String errorTitle)
	{
		String msg = "" + serverAnswer;

		JOptionPane.showMessageDialog(null, msg, errorTitle, JOptionPane.INFORMATION_MESSAGE);
	}

	public void printConnexionError(String errorTitle)
	{
		String msg = "Connexion pipe has been broken";

		JOptionPane.showMessageDialog(null, msg, errorTitle, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		int width 	= e.getComponent().getWidth();
		int height 	= e.getComponent().getHeight();
		this.currentPan.setSize(width, height);
	}

	
	
	@Override
	public void componentHidden(ComponentEvent arg0){}
	@Override
	public void componentMoved(ComponentEvent arg0) {}
	@Override
	public void componentShown(ComponentEvent arg0)	{}

}
