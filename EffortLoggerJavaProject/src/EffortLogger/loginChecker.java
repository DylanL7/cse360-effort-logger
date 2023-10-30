package EffortLogger;

//Code written by Justin Koehle
public class loginChecker {

	//Compares the input string to our test usernames
	public boolean verify (String input)
	{
		if (input.equals("admin")||input.equals("admin2"))
		{
			return true;
		}
		else
			return false;
	}
}