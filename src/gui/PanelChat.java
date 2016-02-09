package gui;

import general.ActionPerformer;
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

	private final static String	TOP_RIGHT_PANEL_TEXT			= "Conversation list";
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

	private final static Color 	BOTTOM_LEFT_PANEL_COLOR			= Color.WHITE;
	private final static Color 	BOTTOM_LEFT_PANEL_TEXT_COLOR	= Color.BLACK;
	private final static String	BOTTOM_LEFT_PANEL_FONT_NAME		= "Arial";
	private final static int	BOTTOM_LEFT_PANEL_FONT_TYPE		= Font.HANGING_BASELINE;
	private final static int	BOTTOM_LEFT_PANEL_FONT_SIZE		= 15;
	private final static String	BOTTOM_LEFT_PANEL_SEND_BUTTON	= "Send";

	private final static String	BOTTOM_RIGHT_PANEL_TEXT			= "List of participantst";
	private final static Color 	BOTTOM_RIGHT_PANEL_COLOR		= Color.RED;
	private final static Color 	BOTTOM_RIGHT_PANEL_TEXT_COLOR	= Color.BLACK;
	private final static String	BOTTOM_RIGHT_PANEL_FONT_NAME	= "Arial";
	private final static int	BOTTOM_RIGHT_PANEL_FONT_TYPE	= Font.ITALIC | Font.BOLD | Font.HANGING_BASELINE;
	private final static int	BOTTOM_RIGHT_PANEL_FONT_SIZE	= 20;
	private final static String	BOTTOM_RIGHT_PANEL_ADD_BUTTON	= "Add conversation";

	private final static double	PARTITION_HEIGHT_TOP_LEFT		= 1./10.;
	private final static double	PARTITION_HEIGHT_MIDDLE_LEFT	= 7.5/10.;
	private final static double	PARTITION_HEIGHT_TOP_RIGHT		= 5./10.;
	private final static double	PARTITION_HEIGHT_MIDDLE_RIGHT	= 2./10.;
	private final static double	PARTITION_WIDTH_TO_SEND			= 8./10.;
	private final static double	PARTITION_WIDTH					= 7./10.;

	private JTextPane	toSendMessagePanel;
	private JTextPane	conversationHistoryPanel;
	private JSplitPane	frameOrganizerTopLeft;
	private JSplitPane	frameOrganizerToSend;
	private JSplitPane	frameOrganizerMiddleLeft;
	private JSplitPane	frameOrganizerTopRight;
	private JSplitPane	frameOrganizerRight;
	private JSplitPane	frameOrganizerMain;
	private JButton		sendButton;
	private JButton		addConversationButton;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public PanelChat()
	{
		super();
		JTextPane topLeftPanel			= new JTextPane();
		JTextPane topRightPanel			= new JTextPane();
		JTextPane bottomRightPanel		= new JTextPane();
		this.toSendMessagePanel			= new JTextPane();
		this.conversationHistoryPanel	= new JTextPane();
		JPanel middleLeftPanel			= new JPanel();
		this.sendButton					= new JButton(BOTTOM_LEFT_PANEL_SEND_BUTTON);
		this.addConversationButton		= new JButton(BOTTOM_RIGHT_PANEL_ADD_BUTTON);
		Font topLeftPanelFont			= new Font(TOP_LEFT_PANEL_FONT_NAME,	TOP_LEFT_PANEL_FONT_TYPE,		TOP_LEFT_PANEL_FONT_SIZE);
		Font topRightPanelFont			= new Font(TOP_RIGHT_PANEL_FONT_NAME,	TOP_RIGHT_PANEL_FONT_TYPE,		TOP_RIGHT_PANEL_FONT_SIZE);
		Font middleLeftPanelFont		= new Font(MIDDLE_LEFT_PANEL_FONT_NAME,	MIDDLE_LEFT_PANEL_FONT_TYPE,	MIDDLE_LEFT_PANEL_FONT_SIZE);
		Font bottomLeftPanelFont		= new Font(BOTTOM_LEFT_PANEL_FONT_NAME,	BOTTOM_LEFT_PANEL_FONT_TYPE,	BOTTOM_LEFT_PANEL_FONT_SIZE);
		Font bottomRightPanelFont		= new Font(BOTTOM_RIGHT_PANEL_FONT_NAME,BOTTOM_RIGHT_PANEL_FONT_TYPE,	BOTTOM_RIGHT_PANEL_FONT_SIZE);

		topLeftPanel.setText(TOP_LEFT_PANEL_TEXT);											// Init the top left panel
		topLeftPanel.setFont(topLeftPanelFont);
		topLeftPanel.setBackground(TOP_LEFT_PANEL_COLOR);
		StyledDocument docTopLeft = topLeftPanel.getStyledDocument();						//		Center the text
		SimpleAttributeSet attributsTL = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsTL, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsTL, TOP_LEFT_PANEL_TEXT_COLOR);
		docTopLeft.setParagraphAttributes(0, docTopLeft.getLength(), attributsTL, false);

		topRightPanel.setText(TOP_RIGHT_PANEL_TEXT);										// Init the top right panel
		topRightPanel.setFont(topRightPanelFont);
		topRightPanel.setBackground(TOP_RIGHT_PANEL_COLOR);
		StyledDocument docTopRight= topRightPanel.getStyledDocument();						//		Center the text
		SimpleAttributeSet attributsTR = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsTR, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsTR, TOP_RIGHT_PANEL_TEXT_COLOR);
		docTopRight.setParagraphAttributes(0, docTopRight.getLength(), attributsTR, false);

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

		bottomRightPanel.setText(BOTTOM_RIGHT_PANEL_TEXT);									// Init the bottom right panel
		bottomRightPanel.setFont(bottomRightPanelFont);
		bottomRightPanel.setBackground(BOTTOM_RIGHT_PANEL_COLOR);
		StyledDocument docBottomRight = bottomRightPanel.getStyledDocument();				//		Center the text
		SimpleAttributeSet attributsBR = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributsBR, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributsBR, BOTTOM_RIGHT_PANEL_TEXT_COLOR);
		docBottomRight.setParagraphAttributes(0, docBottomRight.getLength(), attributsBR, false);

		this.sendButton.addActionListener(new ActionPerformer(this, "TODO"));				// Init the buttons
		this.addConversationButton.addActionListener(new ActionPerformer(this, "TODO"));

		this.setLayout(new GridLayout(1, 1));												// Init the frame
		this.frameOrganizerTopLeft		= new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, topLeftPanel, middleLeftPanel);
		this.frameOrganizerToSend		= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,toSendMessagePanel, sendButton);
		this.frameOrganizerMiddleLeft	= new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, frameOrganizerTopLeft, frameOrganizerToSend);
		this.frameOrganizerTopRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT,	true, topRightPanel, addConversationButton);
		this.frameOrganizerRight		= new JSplitPane(JSplitPane.VERTICAL_SPLIT,	true, this.frameOrganizerTopRight, bottomRightPanel);
		this.frameOrganizerMain			= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,frameOrganizerMiddleLeft, frameOrganizerRight);

		this.frameOrganizerTopLeft	.setDividerSize(10);
		this.frameOrganizerToSend	.setDividerSize(10);
		this.frameOrganizerMiddleLeft.setDividerSize(10);
		this.frameOrganizerRight	.setDividerSize(10);
		this.frameOrganizerMain		.setDividerSize(10);
		this.frameOrganizerTopRight	.setDividerSize(3);
		this.frameOrganizerMain		.setDividerSize(3);
		this.frameOrganizerToSend	.setDividerSize(3);
		this.frameOrganizerMain.setEnabled(false);
		this.add(frameOrganizerMain);
	}

// --------------------------------------------
// Local Methods:
// --------------------------------------------
	public void setSize(int width, int height)
	{
		double dividerHeightTL		= PARTITION_HEIGHT_TOP_LEFT		* (double)height;
		double dividerHeightML		= PARTITION_HEIGHT_MIDDLE_LEFT	* (double)height;
		double dividerHeightTR		= PARTITION_HEIGHT_TOP_RIGHT	* (double)height;
		double dividerHeightMR		= PARTITION_HEIGHT_MIDDLE_RIGHT	* (double)height;
		double dividerWidthMain		= PARTITION_WIDTH				* (double)width;
		double dividerWidthToSend	= PARTITION_WIDTH_TO_SEND		* dividerWidthMain;

		super.setSize(width, height);
		this.frameOrganizerTopLeft		.setDividerLocation((int)dividerHeightTL);
		this.frameOrganizerMiddleLeft	.setDividerLocation((int)dividerHeightML);
		this.frameOrganizerToSend		.setDividerLocation((int)dividerWidthToSend);
		this.frameOrganizerRight		.setDividerLocation((int)dividerHeightMR);
		this.frameOrganizerTopRight		.setDividerLocation((int)dividerHeightTR);
		this.frameOrganizerMain			.setDividerLocation((int)dividerWidthMain);
	}
}
