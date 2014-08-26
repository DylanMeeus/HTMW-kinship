package net.itca.core.htmw.kinship.demontypes;

import java.util.ArrayList;

import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.core.Demon;
import net.itca.htmw.kinship.interfaces.DemonFunctions;

/**
 * Demon that looks for the "uncle-of" inscription in 
 * the 'goal' part of the bulletin board. 
 * If(yes) appropriate response:
 * Figure out parents, Figure out Siblings of Parents, filter for gender
 * 
 * @author Dylan
 *
 */

// Problem in book?!
// The "uncleof" demon will always be triggered, repeatedly, because the goal is a constant on the board.
// To solve this, I'll disable this demon (unsubscribe) from stream.
public class UncleOf implements DemonFunctions
{
	String trigger = "uncle-of";
	Bulletin bulletin = Bulletin.getBulletin();
	
	public UncleOf()
	{

	}

	@Override
	public boolean isTriggered()
	{
		ArrayList<String> goals = bulletin.getGoals();
		String key = "";
		boolean isTriggered = false;
		// No repetition because the key will equal the last subscription in the goal section.
		// Probably not the best way, but it follows the books explanation.
		for(String goal : goals)
		{
			String[] split = goal.split(" ");
			if(split.length >= 3) // If the length is less than 3, it is a gender statement.
			{
				key = split[1];
			}
			if(goal.substring(goal.length()-3).equals("yes") && goals.size()==1)
			{
				return false; // The goal contains a "yes", and is the only goal on the bulletin board
			}
		}
		
		if(trigger.equals(key) && goals.size()==1)
		{
			isTriggered = true;
		}
		return isTriggered;
	}

	@Override
	public void respond() 
	{
		bulletin.addGoal("Find Me's parents");
		bulletin.addGoal("Find parent's siblings");
		bulletin.addGoal("Distinguish Uncles/Aunts");
	}
}
