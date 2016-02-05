package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;





public class GuiController implements ComponentListener
{
// ----------------------------------
// Attributes
// ----------------------------------
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
	public GuiController()
	{
		this.loginPanel	= new PanelLogin();
		this.chatPanel	= new PanelChat();

		this.frame		= new JFrame(FRAME_NAME);

		this.frame.setSize(FRAME_WIDTH_DEFAULT, FRAME_HEIGHT_DEFAULT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.addComponentListener(this);
		this.frame.setVisible(true);
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

	public void componentResized(ComponentEvent e)
	{
		int width 	= e.getComponent().getWidth();
		int height 	= e.getComponent().getHeight();
		this.currentPan.setSize(width, height);
	}

	public void componentHidden(ComponentEvent arg0){}
	public void componentMoved(ComponentEvent arg0) {}
	public void componentShown(ComponentEvent arg0)	{}
}