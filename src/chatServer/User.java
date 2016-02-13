package chatServer;

import java.util.LinkedList;

import chatClient.ChatClientInterface;


public class User
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	userConversationsDir = "resource/";

	private String				userName;
	private String				password;
	private ChatClientInterface	client;
	private LinkedList<String>	converstionNameList;
	private String				currentConversation;

// ----------------------------------
// Builder
// ----------------------------------
	public User(String userName, String password)
	{
		this.userName				= new String(userName);
		this.password				= new String(password);
		this.converstionNameList	= new LinkedList<String>();
	}

// ----------------------------------
// Getter/Setter
// ----------------------------------
	public String				getUserName()			{return new String(this.userName);}
	public String				getPasswordName()		{return new String(this.password);}
	public ChatClientInterface	getClient()				{return this.client;}
	public LinkedList<String>	getConverstionNameList(){return new LinkedList<String>(this.converstionNameList);}
	public String				getCurrentConversation(){return new String(this.currentConversation);}

	public void setClient				(ChatClientInterface client)	{this.client = client;}
	public void setCurrentConversation	(String conversationName)		{this.currentConversation = new String(conversationName);}

// ----------------------------------
// Local methods
// ----------------------------------
	public void addConversation(String conversationName)
	{
		this.converstionNameList.add(new String(conversationName));
	}

}