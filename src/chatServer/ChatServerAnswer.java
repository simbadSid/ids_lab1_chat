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
}
