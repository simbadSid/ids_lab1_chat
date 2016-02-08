package chatServer;

import java.io.File;
import java.util.LinkedList;





public class Conversation
{
// ----------------------------------
// Attributes
// ----------------------------------
	private static final String	conversationsDir = "ressource/conversations/";

	private String				conversationName;
	private LinkedList<User>	userList;

// ----------------------------------
// Builder
// ----------------------------------
	public Conversation(String conversationName)
	{
		this.conversationName	= new String(conversationName);
		this.userList			= new LinkedList<User>();
	}

// ----------------------------------
// Getter
// ----------------------------------
	public String			getConversationName()	{return new String(this.conversationName);}
	public LinkedList<User>	getUserNameList()		{return new LinkedList<User>(this.userList);}
	public void				addUser(User user)		{this.userList.add(user);}

// ----------------------------------
// Local methods
// ----------------------------------
	public void createConversationHistory()
	{
	}

}
