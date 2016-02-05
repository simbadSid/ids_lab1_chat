package chatServer;

import java.util.LinkedList;




public class User
{
// ----------------------------------
// Attributes
// ----------------------------------
	private String				userName;
	private LinkedList<String>	converstionNameList;
	private String				userConversationsDir;

// ----------------------------------
// Builder
// ----------------------------------
	public User(String userName)
	{
		this.userName				= new String(userName);
		this.converstionNameList	= new LinkedList<String>();
	}

// ----------------------------------
// Getter/Setter
// ----------------------------------
	public String				getUserName()			{return new String(this.userName);}
	public LinkedList<String>	converstionNameList()	{return new LinkedList<String>(this.converstionNameList);}

// ----------------------------------
// Local methods
// ----------------------------------
	public void createUserConversationDir()
	{
asdflj
//TODO		
	}
}