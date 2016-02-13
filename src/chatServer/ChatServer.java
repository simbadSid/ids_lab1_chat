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
// TODO
System.setProperty("java.rmi.server.hostname","10.2.1.125");
//TODO
			registry.bind(remoteObjectReference, server_stub);

/*
			System.out.println(serverImpl.CreateUser("User1", "Pass1"));
			System.out.println(serverImpl.CreateConversation("User1", "ConvName"));
			System.out.println(serverImpl.CreateUser("User2", "Pass2"));
			System.out.println(serverImpl.JoinConversation("User2", "ConvName"));
			System.out.println(serverImpl.AddMessage("Message1", "ConvName", "User1"));
*/
			System.out.println("Server ready");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}