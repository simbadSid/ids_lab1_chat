package chatServer;





public enum ChatServerAnswer
{
// ----------------------------------
// Attributes
// ----------------------------------
	SERVER_OK							(1),
	SERVER_USER_ALREADY_EXIST			(2),
	SERVER_CONVERSATION_ALREADY_EXIST	(3),
	SERVER_INTERNAL_ERROR				(4),
	SERVER_USER_UNKNOWN					(5),
	SERVER_CONVERSATION_UNKNOWN			(6);

	public int answer = -1;

// ----------------------------------
// Builder
// ----------------------------------
	private ChatServerAnswer(int answer)
	{
		this.answer = answer;
	}

	public String toString()
	{
		switch(this)
		{
			case SERVER_OK:							return "SERVER ANSWER: OK";
			case SERVER_USER_ALREADY_EXIST:			return "SERVER ANSWER: USER ALREADY EXIST";
			case SERVER_CONVERSATION_ALREADY_EXIST: return "SERVER ANSWER: CONVERSATION ALREADY EXIST";
			case SERVER_INTERNAL_ERROR:				return "SERVER ANSWER: INTERNAL ERROR";
			case SERVER_USER_UNKNOWN:				return "SERVER ANSWER: USER UNKNOWN";
			case SERVER_CONVERSATION_UNKNOWN:		return "SERVER ANSWER: CONVERSATION UNKNOWN";
			default: throw new RuntimeException("Unhandeled answer type: " + answer);
		}
	}
}
