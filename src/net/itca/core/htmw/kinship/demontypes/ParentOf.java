package net.itca.core.htmw.kinship.demontypes;

import java.util.ArrayList;

import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.DemonFunctions;

public class ParentOf implements DemonFunctions
{
	String trigger = "Find parents";
	String foundGoal;
	Bulletin bulletin = Bulletin.getBulletin();
	ArrayList<String> goals;
	public ParentOf()
	{
		
	}
	@Override
	public boolean isTriggered()
	{
		goals = bulletin.getGoals();
		boolean triggered = false;
		for(String goal : goals)
		{
			String[] split = goal.split(" ");
			if(split[0].equals("Find") && split[2].equals("parents"))
			{
				foundGoal = goal;
				triggered = true;
			}
		}
		
		return triggered;
	}
	@Override
	public void respond() 
	{
		String[] split = foundGoal.split(" ");
		String person = split[1]; // Person = the 'person' of whom we want to find the parents.
		int limit = person.indexOf("'");
		person = person.substring(0,limit); // to filter the " 's of me"
		
		// Find all the parents
		ArrayList<String> LTMem = bulletin.getLongTermMemory();
		for(String memory : LTMem)
		{	
			String[] memsplit = memory.split(" ");
			if(memsplit.length >= 3) // If it's less, it's a gender statement
			{
				if(memsplit[1].equals("parent-of") && memsplit[2].equals(person))
				{
					bulletin.addShortTermMemory(memory);
				}
			}
		}
		
		bulletin.removeGoal(foundGoal);
		// Alter the bulletin board
		// Copy parents to short-term memory
		// Remove goal
	}
	
}
