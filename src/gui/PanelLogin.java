package gui;

import general.ActionPerformer;

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







@SuppressWarnings("serial")
public class PanelLogin extends JPanel
{
// --------------------------------------------
// Attributes:
// --------------------------------------------
	private final static double	PARTITION_HEIGHT			= 1.5/10.;
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
	private final static int	BOTTOM_PANEL_BUTTON_HEIGHT	= BOTTOM_PANEL_NBR_LIGN-BOTTOM_PANEL_NBR_LIGN/5;

	private final static String	NAME_LABEL_TEXT				= "User name";
	private final static String	NAME_LABEL_FONT_NAME		= "Arial";
	private final static int	NAME_LABEL_FONT_TYPE		= Font.BOLD | Font.HANGING_BASELINE;
	private final static int	NAME_LABEL_FONT_SIZE		= 20;

	private JSplitPane	frameOrganizerMain;
	private JPanel		bottomPanel;
	private JTextField	nameTextField;
	private JButton		okButton;
	private JButton		cancelButton;

// --------------------------------------------
// Builder:
// --------------------------------------------
	public PanelLogin()
	{
		super();
		JTextPane topPanel		= new JTextPane();
		this.bottomPanel		= new JPanel();
		this.nameTextField		= new JTextField();
		this.okButton			= new JButton("Ok");
		this.cancelButton		= new JButton("Cancel");
		Font topPanelFont		= new Font(TOP_PANEL_FONT_NAME, TOP_PANEL_FONT_TYPE, TOP_PANEL_FONT_SIZE);
		Font nameLabelFont		= new Font(NAME_LABEL_FONT_NAME, NAME_LABEL_FONT_TYPE, NAME_LABEL_FONT_SIZE);
		JTextPane nameLabel		= new JTextPane();

		topPanel.setText(TOP_PANEL_TEXT);												// Init the top panel
		topPanel.setFont(topPanelFont);
		topPanel.setBackground(TOP_PANEL_COLOR);
		StyledDocument doc = topPanel.getStyledDocument();									//		Center the text
		SimpleAttributeSet attributs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attributs, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attributs, TOP_PANEL_TEXT_COLOR);
		doc.setParagraphAttributes(0, doc.getLength(), attributs, false);

		this.bottomPanel.setBackground(BOTTOM_PANEL_COLOR);									// Init the bottom panel
		this.bottomPanel.setLayout(new GridLayout(BOTTOM_PANEL_NBR_LIGN, BOTTOM_PANEL_NBR_COLOMN));
		nameLabel.setText(NAME_LABEL_TEXT);
		nameLabel.setFont(nameLabelFont);
		for (int i=0; i<BOTTOM_PANEL_NBR_LIGN; i++)
		{
			if (i == BOTTOM_PANEL_NAME_HEIGHT)
			{
				this.bottomPanel.add(nameLabel);
				this.bottomPanel.add(this.nameTextField);
			}
			else if (i == BOTTOM_PANEL_BUTTON_HEIGHT)
			{
				this.bottomPanel.add(okButton);
				this.bottomPanel.add(cancelButton);
			}
			else
			{
				this.bottomPanel.add(new JPanel());
				this.bottomPanel.add(new JPanel());
			}
		}

		this.okButton.addActionListener(new ActionPerformer(this, "TODO"));					// Init the button
		this.cancelButton.addActionListener(new ActionPerformer(this, "TODO"));

		this.setLayout(new GridLayout(1, 1));												// Init the frame
		this.frameOrganizerMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, topPanel, bottomPanel);
		this.frameOrganizerMain.setDividerSize(10);
		this.frameOrganizerMain.setDividerSize(3);
		this.frameOrganizerMain.setEnabled(false);
		this.add(frameOrganizerMain);
	}

// --------------------------------------------
// Local methods:
// --------------------------------------------
	public void setSize(int width, int height)
	{
		double dividerHeight = PARTITION_HEIGHT * (double)height;

		super.setSize(width, height);
		this.frameOrganizerMain.setDividerLocation((int) dividerHeight);
	}
}