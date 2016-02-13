package chatClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.SwingUtilities;
import chatServer.ChatServerInterface;







public class ChatClient
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	DEFAULT_REMOTE_HOST_REFERENCE	= "ChatServer";
	public static final String	DEFAULT_REMOTE_HOST_IP			= "127.0.0.1";

	public static final String	PARAMETER_HOST_REFERENCE		= "-rhr";
	public static final String	PARAMETER_HOST_IP				= "-rhip";
	public static final int		NBR_PARAMETER					= 2;

// ----------------------------------
// Main methods
// ----------------------------------
	public static void main(String[] args)
	{
		String remoteHostReference	= null;
		String remoteHostIp			= null;

		// Parse parameters
		if (args.length > NBR_PARAMETER) printUsage(true);
		for (int i=0; i<args.length; i++)
		{
			if		(args[i].equals(PARAMETER_HOST_REFERENCE))	remoteHostReference = args[i+1];
			else if (args[i].equals(PARAMETER_HOST_IP))			remoteHostIp		= args[i+1];
			else printUsage(true);
			i ++;
		}

		if (remoteHostReference == null)remoteHostReference	= DEFAULT_REMOTE_HOST_REFERENCE;
		if (remoteHostIp == null)		remoteHostIp		= DEFAULT_REMOTE_HOST_IP;

		// Init connection with server
		ChatServerInterface server	= initConnectionWithServer(remoteHostReference, remoteHostIp);
		if (server == null) return;

		ChatClientImpl client = new ChatClientImpl(server);
    	SwingUtilities.invokeLater(client);
	}

	private static ChatServerInterface initConnectionWithServer(String remoteHostReference, String remoteHostIp)
	{
		Registry registry = null;
		ChatServerInterface	serverInterface = null;

		try
		{
			// Get remote object reference
			registry		= LocateRegistry.getRegistry(remoteHostIp);
			serverInterface	= (ChatServerInterface)registry.lookup(remoteHostReference);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return serverInterface;
	}

	private static void printUsage(boolean exit)
	{
		System.out.println("Usage options:");
		System.out.printf("\t-%s <Reference of the remote server>", PARAMETER_HOST_REFERENCE);
		System.out.printf("\t-%s <Ip address of the remote server>", PARAMETER_HOST_IP);

		if (exit) System.exit(0);
	}

}
