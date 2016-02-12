package chatClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import chatServer.ChatServerInterface;







@SuppressWarnings("serial")
public class PanelLogin extends JPanel
{
// --------------------------------------------
// Attributes:
// --------------------------------------------
	private final static double	PARTITION_HEIGHT_TOP		= 1.5/10.;
	private final static double	PARTITION_HEIGHT_MAIN		= 9./10.;
	private final static String	TOP_PANEL_TEXT				= "Login Panel";
	private final static Color 	TOP_PANEL_COLOR				= Color.BLACK;
	private final static Color 	TOP_PANEL_TEXT_COLOR		= Color.WHITE;
	private final static String	TOP_PANEL_FONT_NAME			= "Arial";
	private final static int	TOP_PANEL_FONT_TYPE			= Font.ITALIC | Font.BOLD | Font.HANGING_BASELINE;
	private final static int	TOP_PANEL_FONT_SIZE			= 20;

	private final static Color	BOTTOM_PANEL_COLOR			= Color.LIGHT_GRAY;
	private final static int	BOTTOM_PANEL_NBR_LIGN		= 10;
	private final static int	BOTTOM_PANEL_NBR_COLOMN		= 2;
	private final static int	BOTTOM_PANEL_NAME_HEIGHT	= BOTTOM_PANEL_NBR_LIGN/5;
	private final static int	BOTTOM_PANEL_PASSWORD_HEIGHT= BOTTOM_PANEL_NAME_HEIGHT + 1;

	private final static String	NAME_LABEL_TEXT				= "User name";
	private final static String	NAME_LABEL_FONT_NAME		= "Arial";
	private final static int	NAME_LABEL_FONT_TYPE		= Font.BOLD | Font.HANGING_BASELINE;
	private final static int	NAME_LABEL_FONT_SIZE		= 20;

	private final static String	PASSWORD_LABEL_TEXT			= "Password";
	private final static String	PASSWORD_LABEL_FONT_NAME	= "Arial";
	private final static int	PASSWORD_LABEL_FONT_TYPE	= Font.BOLD | Font.HANGING_BASELINE;
	private final static int	PASSWORD_LABEL_FONT_SIZE	= 20;

	private JSplitPane	frameOrganizerTop;
	private JSplitPane	frameOrganizerMain;
	private JTextField	nameTextField;
	private JTextField	passwordTextField;
	private JButton		loginButton;
	private JButton		creatAccountButton;
	private JButton		cancelButton;

	private ChatServerInterface	server;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public PanelLogin(ChatServerInterface server)
	{
		super();
		this.server				= server;
		JTextPane topPanel		= new JTextPane();
		JPanel bottomPanel		= new JPanel();
		JPanel buttonPanel		= new JPanel();
		this.nameTextField		= new JTextField();
		this.passwordTextField	= new JTextField();
		this.loginButton			= new JButton("Login");
		this.creatAccountButton	= new JButton("Creat account");
		this.cancelButton		= new JButton("Cancel");
		Font topPanelFont		= new Font(TOP_PANEL_FONT_NAME, TOP_PANEL_FONT_TYPE, TOP_PANEL_FONT_SIZE);
		Font nameLabelFont		= new Font(NAME_LABEL_FONT_NAME, NAME_LABEL_FONT_TYPE, NAME_LABEL_FONT_SIZE);
		Font passwordLabelFont	= new Font(PASSWORD_LABEL_FONT_NAME, PASSWORD_LABEL_FONT_TYPE, PASSWORD_LABEL_FONT_SIZE);
		JTextPane nameLabel		= new JTextPane();
		JTextPane passwordLabel	= new JTextPane();

		topPanel.setText(TOP_PANEL_TEXT);												// Init the top panel
		topPanel.setFont(topPanelFont);
		topPanel.setBackground(TOP_PANEL_COLOR);
		StyledDocument doc = topPanel.getStyledDocument();								//		Center the text
		SimpleAttributeSet attributs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributs, TOP_PANEL_TEXT_COLOR);
		doc.setParagraphAttributes(0, doc.getLength(), attributs, false);

		bottomPanel.setBackground(BOTTOM_PANEL_COLOR);									// Init the bottom panel
		bottomPanel.setLayout(new GridLayout(BOTTOM_PANEL_NBR_LIGN, BOTTOM_PANEL_NBR_COLOMN));
		nameLabel.setText(NAME_LABEL_TEXT);
		nameLabel.setFont(nameLabelFont);
		passwordLabel.setText(PASSWORD_LABEL_TEXT);
		passwordLabel.setFont(passwordLabelFont);
		for (int i=0; i<BOTTOM_PANEL_NBR_LIGN; i++)
		{
			if (i == BOTTOM_PANEL_NAME_HEIGHT)
			{
				bottomPanel.add(nameLabel);
				bottomPanel.add(this.nameTextField);
			}
			else if (i == BOTTOM_PANEL_PASSWORD_HEIGHT)
			{
				bottomPanel.add(passwordLabel);
				bottomPanel.add(this.passwordTextField);
			}
			else
			{
				bottomPanel.add(new JPanel());
				bottomPanel.add(new JPanel());
			}
		}

		buttonPanel.add(loginButton);													// Init the button
		buttonPanel.add(creatAccountButton);
		buttonPanel.add(cancelButton);

		this.loginButton.addActionListener(new ActionListenerLogin(this, this.server));
		this.creatAccountButton.addActionListener(new ActionListenerCreate(this, this.server));
		this.cancelButton.addActionListener(new ActionListenerCancel());

		this.setLayout(new GridLayout(1, 1));											// Init the frame
		this.frameOrganizerTop = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, topPanel, bottomPanel);
		this.frameOrganizerMain= new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, frameOrganizerTop, buttonPanel);
		this.frameOrganizerMain.setDividerSize(3);
		this.frameOrganizerTop.setDividerSize(3);
		this.frameOrganizerMain.setEnabled(false);
		this.frameOrganizerTop.setEnabled(false);
		this.add(frameOrganizerMain);
	}

// --------------------------------------------
// Local methods:
// --------------------------------------------
	@Override
	public void setSize(int width, int height)
	{
		double dividerHeightTop		= PARTITION_HEIGHT_TOP * height;
		double dividerHeightMain	= PARTITION_HEIGHT_MAIN * height;

		super.setSize(width, height);
		this.frameOrganizerTop.setDividerLocation((int) dividerHeightTop);
		this.frameOrganizerMain.setDividerLocation((int) dividerHeightMain);
	}

	public String getUserName()
	{
		return this.nameTextField.getText();
	}

	public String getPassword()
	{
		return this.passwordTextField.getText();
	}
}