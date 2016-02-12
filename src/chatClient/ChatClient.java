package chatClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.SwingUtilities;
import chatServer.ChatServerInterface;







public class ChatClient implements Runnable
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	DEFAULT_REMOTE_HOST_REFERENCE	= "ChatServer";
	public static final String	DEFAULT_REMOTE_HOST_IP			= "127.0.0.1";

	public static final String	PARAMETER_HOST_REFERENCE		= "-rhr";
	public static final String	PARAMETER_HOST_IP				= "-rhip";
	public static final int		NBR_PARAMETER					= 2;

	private String				remoteHostReference;
	private String				remoteHostIp;
	private ChatServerInterface	server;
	private GuiController		gui;

// ----------------------------------
// Main methods
// ----------------------------------
	@Override
	public void run()
	{
		// Init the graphical interface
		this.gui			= new GuiController(server);
		this.gui.setCurrentPanel(GuiController.PANEL_CHAT_ID);
	}

	public static void main(String[] args)
	{
		ChatClient chatClient = new ChatClient();
		ChatServerInterface chatServer;

		chatClient.parseParameters(args);
		chatServer = chatClient.initConnectionWithServer();
		if (chatServer == null) return;
		chatClient.server = chatServer;
    	SwingUtilities.invokeLater(chatClient);
	}

	private static void printUsage(boolean exit)
	{
		System.out.println("Usage options:");
		System.out.printf("\t-%s <Reference of the remote server>", PARAMETER_HOST_REFERENCE);
		System.out.printf("\t-%s <Ip address of the remote server>", PARAMETER_HOST_IP);

		if (exit) System.exit(0);
	}

	private void parseParameters(String args[])
	{
		if (args.length > NBR_PARAMETER) printUsage(true);
		for (int i=0; i<args.length; i++)
		{
			if		(args[i].equals(PARAMETER_HOST_REFERENCE))	remoteHostReference = args[i+1];
			else if (args[i].equals(PARAMETER_HOST_IP))			remoteHostIp		= args[i+1];
			else printUsage(true);
			i ++;
		}

		if (remoteHostReference == null)	this.remoteHostReference	= DEFAULT_REMOTE_HOST_REFERENCE;
		if (remoteHostIp == null)			this.remoteHostIp			= DEFAULT_REMOTE_HOST_IP;
	}

	private ChatServerInterface initConnectionWithServer()
	{
		Registry registry = null;
		ChatServerInterface	serverInterface = null;

		try
		{
			// Get remote object reference
			registry		= LocateRegistry.getRegistry(this.remoteHostIp);
			serverInterface	= (ChatServerInterface)registry.lookup(this.remoteHostReference);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return serverInterface;
	}
}
