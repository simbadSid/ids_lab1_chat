package general;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;






public class ActionPerformer implements ActionListener
{
// --------------------------------------------
// Attributes
// --------------------------------------------
	private Object targetObject;
	private String action;

// --------------------------------------------
// Builder
// --------------------------------------------
	public ActionPerformer(Object target, String action)
	{
		this.targetObject	= target;
		this.action			= action;
	}

// --------------------------------------------
// Getters / setters
// --------------------------------------------
	public Object getTarget()	{return this.targetObject;}
	public String getAction()	{return this.action;}

// --------------------------------------------
// ActionListener interface
// --------------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		Class<?> targetClass = this.targetObject.getClass();
		Method method;

		try
		{
			method = targetClass.getDeclaredMethod(this.action);
			method.invoke(this.targetObject);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			System.exit(0);
		}
	}
}