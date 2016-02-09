package chatServer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


public class User
{
// ----------------------------------
// Attributes
// ----------------------------------
	public static final String	userConversationsDir = "resource/";

	private String				userName;
	private String				password;
	private LinkedList<String>	converstionNameList;

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
	public LinkedList<String>	converstionNameList()	{return new LinkedList<String>(this.converstionNameList);}

// ----------------------------------
// Local methods
// ----------------------------------
	public void addConversation(String conversationName)
	{
		String fileName = userConversationsDir + conversationName;

		this.converstionNameList.add(new String(conversationName));
		try
		{
			new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}

}