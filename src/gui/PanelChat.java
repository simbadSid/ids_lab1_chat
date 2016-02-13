package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import chatServer.ChatServerInterface;
import chatServer.Conversation;






@SuppressWarnings("serial")
public class PanelChat extends JPanel
{
// --------------------------------------------
// Attributes:
// --------------------------------------------
	private final static String	TOP_LEFT_PANEL_TEXT				= "Chat Panel";
	private final static Color 	TOP_LEFT_PANEL_COLOR			= Color.BLACK;
	private final static Color 	TOP_LEFT_PANEL_TEXT_COLOR		= Color.WHITE;
	private final static String	TOP_LEFT_PANEL_FONT_NAME		= "Arial";
	private final static int	TOP_LEFT_PANEL_FONT_TYPE		= Font.ITALIC | Font.BOLD | Font.HANGING_BASELINE;
	private final static int	TOP_LEFT_PANEL_FONT_SIZE		= 20;

	private final static String	TOP_RIGHT_PANEL_TEXT			= "Current conversation: ";
	private final static Color 	TOP_RIGHT_PANEL_COLOR			= Color.GREEN;
	private final static Color 	TOP_RIGHT_PANEL_TEXT_COLOR		= Color.BLACK;
	private final static String	TOP_RIGHT_PANEL_FONT_NAME		= "Arial";
	private final static int	TOP_RIGHT_PANEL_FONT_TYPE		= Font.ITALIC | Font.BOLD | Font.HANGING_BASELINE;
	private final static int	TOP_RIGHT_PANEL_FONT_SIZE		= 20;

	private final static Color 	MIDDLE_LEFT_PANEL_COLOR			= Color.WHITE;
	private final static Color 	MIDDLE_LEFT_PANEL_TEXT_COLOR	= Color.BLACK;
	private final static String	MIDDLE_LEFT_PANEL_FONT_NAME		= "Arial";
	private final static int	MIDDLE_LEFT_PANEL_FONT_TYPE		= Font.ITALIC | Font.HANGING_BASELINE;
	private final static int	MIDDLE_LEFT_PANEL_FONT_SIZE		= 15;

	private final static String	INFO_FONT_NAME					= "Arial";
	private final static int	INFO_FONT_TYPE					= Font.BOLD | Font.HANGING_BASELINE;
	private final static int	INFO_FONT_SIZE					= 15;

	private final static Color 	BOTTOM_LEFT_PANEL_COLOR			= Color.WHITE;
	private final static Color 	BOTTOM_LEFT_PANEL_TEXT_COLOR	= Color.BLACK;
	private final static String	BOTTOM_LEFT_PANEL_FONT_NAME		= "Arial";
	private final static int	BOTTOM_LEFT_PANEL_FONT_TYPE		= Font.HANGING_BASELINE;
	private final static int	BOTTOM_LEFT_PANEL_FONT_SIZE		= 15;
	private final static String	BOTTOM_LEFT_PANEL_SEND_BUTTON	= "Send";

	private final static String	BOTTOM_RIGHT_PANEL_TEXT			= "List of participantst:";
	private final static Color 	BOTTOM_RIGHT_PANEL_COLOR		= Color.RED;
	private final static Color 	BOTTOM_RIGHT_PANEL_TEXT_COLOR	= Color.BLACK;
	private final static String	BOTTOM_RIGHT_PANEL_FONT_NAME	= "Arial";
	private final static int	BOTTOM_RIGHT_PANEL_FONT_TYPE	= Font.ITALIC | Font.BOLD | Font.HANGING_BASELINE;
	private final static int	BOTTOM_RIGHT_PANEL_FONT_SIZE	= 20;
	private final static String	BOTTOM_RIGHT_PANEL_CREATE_BUTTON= "Create conversation";
	private final static String	BOTTOM_RIGHT_PANEL_SWITCH_BUTTON= "Switch conversations";
	private final static String	BOTTOM_RIGHT_PANEL_LOGOUT_BUTTON= "Logout";
	private final static int	BOTTOM_RIGHT_PANEL_NBR_LABELS	= 8;

	private static final String	PARTICIPANTS_OVEROLD			= "...";
	private static final String	PARTICIPANTS_HEADER				= "-> ";

	private final static double	PARTITION_HEIGHT_TOP_LEFT		= 1./10.;
	private final static double	PARTITION_HEIGHT_MIDDLE_LEFT	= 8.5/10.;
	private final static double	PARTITION_HEIGHT_RIGHT			= 3./10.;
	private final static double	PARTITION_WIDTH_TO_SEND			= 6./10.;
	private final static double	PARTITION_WIDTH					= 7./10.;

	private JTextPane	toSendMessagePanel;
	private JTextPane	conversationHistoryPanel;
	private JTextPane	conversationNamePanel;
	private JTextPane[]	conversationParticipantPanel;
	private JSplitPane	frameOrganizerTopLeft;
	private JSplitPane	frameOrganizerToSend;
	private JSplitPane	frameOrganizerMiddleLeft;
	private JSplitPane	frameOrganizerRight;
	private JSplitPane	frameOrganizerMain;
	private JButton		sendButton;
	private JButton		createConversationButton;
	private JButton		switchConversationButton;
	private JButton		logoutButton;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public PanelChat(GuiController gui, ChatServerInterface server)
	{
		super();
		JTextPane topLeftPanel			= new JTextPane();
		JPanel topRightPanel			= new JPanel();
		JPanel bottomRightPanel			= new JPanel();
		JTextPane topRightLabel			= new JTextPane();
		JTextPane bottomRightLabel		= new JTextPane();
		this.conversationNamePanel		= new JTextPane();
		this.toSendMessagePanel			= new JTextPane();
		this.conversationHistoryPanel	= new JTextPane();
		JPanel middleLeftPanel			= new JPanel();
		this.sendButton					= new JButton(BOTTOM_LEFT_PANEL_SEND_BUTTON);
		this.createConversationButton	= new JButton(BOTTOM_RIGHT_PANEL_CREATE_BUTTON);
		this.switchConversationButton	= new JButton(BOTTOM_RIGHT_PANEL_SWITCH_BUTTON);
		this.logoutButton				= new JButton(BOTTOM_RIGHT_PANEL_LOGOUT_BUTTON);
		Font topLeftPanelFont			= new Font(TOP_LEFT_PANEL_FONT_NAME,	TOP_LEFT_PANEL_FONT_TYPE,		TOP_LEFT_PANEL_FONT_SIZE);
		Font topRightPanelFont			= new Font(TOP_RIGHT_PANEL_FONT_NAME,	TOP_RIGHT_PANEL_FONT_TYPE,		TOP_RIGHT_PANEL_FONT_SIZE);
		Font middleLeftPanelFont		= new Font(MIDDLE_LEFT_PANEL_FONT_NAME,	MIDDLE_LEFT_PANEL_FONT_TYPE,	MIDDLE_LEFT_PANEL_FONT_SIZE);
		Font bottomLeftPanelFont		= new Font(BOTTOM_LEFT_PANEL_FONT_NAME,	BOTTOM_LEFT_PANEL_FONT_TYPE,	BOTTOM_LEFT_PANEL_FONT_SIZE);
		Font bottomRightPanelFont		= new Font(BOTTOM_RIGHT_PANEL_FONT_NAME,BOTTOM_RIGHT_PANEL_FONT_TYPE,	BOTTOM_RIGHT_PANEL_FONT_SIZE);
		Font infoFont					= new Font(INFO_FONT_NAME,				INFO_FONT_TYPE,					INFO_FONT_SIZE);

		topLeftPanel.setText(TOP_LEFT_PANEL_TEXT);											// Init the top left panel
		topLeftPanel.setFont(topLeftPanelFont);
		topLeftPanel.setBackground(TOP_LEFT_PANEL_COLOR);
		topLeftPanel.setEditable(false);
		StyledDocument docTopLeft = topLeftPanel.getStyledDocument();						//		Center the text
		SimpleAttributeSet attributsTL = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsTL, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsTL, TOP_LEFT_PANEL_TEXT_COLOR);
		docTopLeft.setParagraphAttributes(0, docTopLeft.getLength(), attributsTL, false);

		topRightLabel.setEditable(false);													// Init the top right panel
		topRightPanel.setLayout(new GridLayout(5, 1));
		topRightPanel.add(topRightLabel);
		topRightPanel.add(this.conversationNamePanel);
		topRightPanel.add(createConversationButton);
		topRightPanel.add(switchConversationButton);
		topRightPanel.add(logoutButton);

		topRightLabel.setText(TOP_RIGHT_PANEL_TEXT);
		topRightLabel.setFont(topRightPanelFont);
		topRightLabel.setBackground(TOP_RIGHT_PANEL_COLOR);
		StyledDocument docTopRight= topRightLabel.getStyledDocument();						//		Center the text
		SimpleAttributeSet attributsTR = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsTR, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsTR, TOP_RIGHT_PANEL_TEXT_COLOR);
		docTopRight.setParagraphAttributes(0, docTopRight.getLength(), attributsTR, false);

		this.conversationNamePanel.setFont(infoFont);
		this.conversationNamePanel.setEditable(false);
		this.conversationNamePanel.setBackground(TOP_RIGHT_PANEL_COLOR);

		this.conversationHistoryPanel.setFont(middleLeftPanelFont);							// Init the middle left panel
		this.conversationHistoryPanel.setBackground(MIDDLE_LEFT_PANEL_COLOR);
		StyledDocument docMiddleLeft = this.conversationHistoryPanel.getStyledDocument();	//		Center the text
		SimpleAttributeSet attributsML = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsML, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsML, MIDDLE_LEFT_PANEL_TEXT_COLOR);
		docMiddleLeft.setParagraphAttributes(0, docMiddleLeft.getLength(), attributsML, false);
		middleLeftPanel.setLayout(new GridLayout(1, 1));
		middleLeftPanel.add(this.conversationHistoryPanel);

		this.toSendMessagePanel.setFont(bottomLeftPanelFont);								// Init the bottom left panel
		this.toSendMessagePanel.setBackground(BOTTOM_LEFT_PANEL_COLOR);
		StyledDocument docBottomLeft = this.toSendMessagePanel.getStyledDocument();			//		Center the text
		SimpleAttributeSet attributsBL = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsBL, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsBL, BOTTOM_LEFT_PANEL_TEXT_COLOR);
		docBottomLeft.setParagraphAttributes(0, docBottomLeft.getLength(), attributsBL, false);

		bottomRightPanel.setLayout(new GridLayout(BOTTOM_RIGHT_PANEL_NBR_LABELS, 1));
		bottomRightPanel.add(bottomRightLabel);
		this.conversationParticipantPanel = new JTextPane[BOTTOM_RIGHT_PANEL_NBR_LABELS-1];
		for (int i=0; i<BOTTOM_RIGHT_PANEL_NBR_LABELS-1; i++)
		{
			this.conversationParticipantPanel[i] = new JTextPane();;
			this.conversationParticipantPanel[i].setFont(infoFont);
			this.conversationParticipantPanel[i].setEditable(false);
			this.conversationParticipantPanel[i].setBackground(BOTTOM_RIGHT_PANEL_COLOR);

			bottomRightPanel.add(this.conversationParticipantPanel[i]);
		}

		bottomRightLabel.setText(BOTTOM_RIGHT_PANEL_TEXT);									// Init the bottom right panel
		bottomRightLabel.setFont(bottomRightPanelFont);
		bottomRightLabel.setBackground(BOTTOM_RIGHT_PANEL_COLOR);
		bottomRightLabel.setEditable(false);
		StyledDocument docBottomRight = bottomRightLabel.getStyledDocument();				//		Center the text
		SimpleAttributeSet attributsBR = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsBR, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsBR, BOTTOM_RIGHT_PANEL_TEXT_COLOR);
		docBottomRight.setParagraphAttributes(0, docBottomRight.getLength(), attributsBR, false);

		this.sendButton.addActionListener(new ActionListenerSend(gui, server));				// Init the buttons
		this.createConversationButton.addActionListener(new ActionListenerCreateConversation(gui, server));
		this.switchConversationButton.addActionListener(new ActionListenerSwitchConversation(gui, server));
		this.logoutButton.addActionListener(new ActionListenerLogout(gui, server));

		this.setLayout(new GridLayout(1, 1));												// Init the frame
		this.frameOrganizerTopLeft		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, topLeftPanel, middleLeftPanel);
		this.frameOrganizerToSend		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,toSendMessagePanel, sendButton);
		this.frameOrganizerMiddleLeft	= new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, frameOrganizerTopLeft, frameOrganizerToSend);
		this.frameOrganizerRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT,	true, topRightPanel, bottomRightPanel);
		this.frameOrganizerMain			= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,frameOrganizerMiddleLeft, frameOrganizerRight);

		this.frameOrganizerTopLeft	.setDividerSize(10);
		this.frameOrganizerToSend	.setDividerSize(10);
		this.frameOrganizerMiddleLeft.setDividerSize(10);
		this.frameOrganizerRight	.setDividerSize(10);
		this.frameOrganizerMain		.setDividerSize(10);
		this.frameOrganizerMain		.setDividerSize(3);
		this.frameOrganizerToSend	.setDividerSize(3);
		this.frameOrganizerMain.setEnabled(false);
		this.add(frameOrganizerMain);
	}

// --------------------------------------------
// Local Methods:
// --------------------------------------------
	@Override
	public void setSize(int width, int height)
	{
		double dividerHeightTL		= PARTITION_HEIGHT_TOP_LEFT		* height;
		double dividerHeightML		= PARTITION_HEIGHT_MIDDLE_LEFT	* height;
		double dividerHeightR		= PARTITION_HEIGHT_RIGHT		* height;
		double dividerWidthMain		= PARTITION_WIDTH				* width;
		double dividerWidthToSend	= PARTITION_WIDTH_TO_SEND		* dividerWidthMain;

		super.setSize(width, height);
		this.frameOrganizerTopLeft		.setDividerLocation((int)dividerHeightTL);
		this.frameOrganizerMiddleLeft	.setDividerLocation((int)dividerHeightML);
		this.frameOrganizerToSend		.setDividerLocation((int)dividerWidthToSend);
		this.frameOrganizerRight		.setDividerLocation((int)dividerHeightR);
		this.frameOrganizerMain			.setDividerLocation((int)dividerWidthMain);
	}

	public String getWrittenMessage()
	{
		return this.toSendMessagePanel.getText();
	}

	public void removeWrittenMessage()
	{
		this.toSendMessagePanel.setText("");
	}

	public String getConversationName()
	{
		return this.conversationNamePanel.getText();
	}

	public void setCuurentConversation(String convName, String currentParticipant)
	{
		this.conversationNamePanel.setText(convName);
		this.conversationParticipantPanel[0].setText(PARTICIPANTS_HEADER + currentParticipant);
		for (int j=1; j<conversationParticipantPanel.length; j++)
		{
			this.conversationParticipantPanel[j].setText("");
		}
	}

	public void setCuurentConversation(Conversation conv)
	{
		this.conversationNamePanel.setText(conv.getConversationName());

		int nbrUser = conv.getUserName().size();
		int i = 0;
		for (String user: conv.getUserName())
		{
			JTextPane label = this.conversationParticipantPanel[i];
			if ((i == conversationParticipantPanel.length-1) && (i < nbrUser-1))
			{
				label.setText(PARTICIPANTS_OVEROLD);
				break;
			}
			else
			{
				label.setText(PARTICIPANTS_HEADER + user);
			}
			i ++;
		}
		for (int j=i; j<conversationParticipantPanel.length; j++)
		{
			this.conversationParticipantPanel[i].setText("");
		}
	}
}