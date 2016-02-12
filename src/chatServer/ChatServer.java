package chatServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;





public class ChatServer
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	remoteObjectReference	= "ChatServer";
	public static final int		remoteServerPort		= 0;

// ----------------------------------
// Builder
// ----------------------------------

// ----------------------------------
// Main methods
// ----------------------------------
	public static void main(String[] args)
	{
		try
		{
			ChatServerImpl		serverImpl	= new ChatServerImpl();
			ChatServerInterface	server_stub	= (ChatServerInterface) UnicastRemoteObject.exportObject(serverImpl, remoteServerPort);
			Registry			registry	= LocateRegistry.getRegistry();
			registry.bind(remoteObjectReference, server_stub);

			System.out.println("Server ready");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}