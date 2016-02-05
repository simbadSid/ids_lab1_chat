package chatServer;

import java.util.LinkedList;





public class Conversation
{
// ----------------------------------
// Attributes
// ----------------------------------
	private String				conversationName;
	private LinkedList<String>	userNameList;

// ----------------------------------
// Builder
// ----------------------------------
	public Conversation(String conversationName)
	{
		this.conversationName	= new String(conversationName);
		this.userNameList		= new LinkedList<String>();
	}

// ----------------------------------
// Getter
// ----------------------------------
	public String				getConversationName()	{return new String(this.conversationName);}
	public LinkedList<String>	userNameList()			{return new LinkedList<String>(this.userNameList);}

// ----------------------------------
// Local methods
// ----------------------------------

}
