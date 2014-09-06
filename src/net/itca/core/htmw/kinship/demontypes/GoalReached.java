package net.itca.core.htmw.kinship.demontypes;

import java.util.ArrayList;

import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.DemonFunctions;

// behaviour that checks for reached goals
public class GoalReached implements DemonFunctions 
{
	// We check if the goal is reached everytime we recieve an update
	
	String trigger = "Gordie uncle-of Me?";
	Bulletin bulletin = Bulletin.getBulletin();
	
	public GoalReached()
	{
		
	}

	@Override
	public boolean isTriggered()
	{
		return bulletin.getGoals().contains(trigger);
	}

	@Override
	public void respond() 
	{
		// Check if the goal is met by checking Long-Term memory for goal.
		ArrayList<String> LTMemory = bulletin.getLongTermMemory();
		ArrayList<String> goals = bulletin.getGoals();
		
		if(LTMemory.contains(trigger.substring(0,trigger.length()-1)))
		{
			int memLoc = goals.indexOf(trigger);
			goals.remove(memLoc);
			bulletin.addGoal(trigger + ": yes");
		}
		
		
	}
}
