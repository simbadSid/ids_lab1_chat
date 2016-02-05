package chatClient;

import gui.GuiController;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.SwingUtilities;
import chatServer.ChatServer;
import chatServer.ChatServerInterface;







public class ChatClient implements Runnable
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	DEFAULT_REMOTE_HOST_IP	= "127.0.0.1";

	private String			remoteHostIP;
	private GuiController	gui;

// ----------------------------------
// Builder
// ----------------------------------
	public ChatClient(String remoteHostIP)
	{
		this.remoteHostIP	= new String(remoteHostIP);
	}

// ----------------------------------
// Main methods
// ----------------------------------
	@Override
	public void run()
	{
		try
		{
			// Get remote object reference
//			Registry			registry		= LocateRegistry.getRegistry(this.remoteHostIP);
//			ChatServerInterface	serverInterface	= (ChatServerInterface)registry.lookup(ChatServer.remoteObjectReference);

			// Init the graphical interface
			this.gui			= new GuiController();
			this.gui.setCurrentPanel(GuiController.PANEL_LOGIN_ID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		String host;

		if (args.length < 1)	host = DEFAULT_REMOTE_HOST_IP;
		else					host = args[0];

    	SwingUtilities.invokeLater(new ChatClient(host));
	}
}
