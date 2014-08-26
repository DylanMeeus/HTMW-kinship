package net.itca.core.htmw.kinship.demontypes;

import java.util.ArrayList;

import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.DemonFunctions;

/**
 * Demon that is triggered by a sibling-of inscription in the goal area of the bulletin board.
 * The demon is only triggered IF and only IF the parents are known to the short-term memory!
 * @author Dylan
 *
 */
public class SiblingOf implements DemonFunctions
{
	Bulletin bulletin = Bulletin.getBulletin();
	String trigger = "Find siblings";
	String foundGoal;
	ArrayList<String> goals;
	ArrayList<String> STMem;
	public SiblingOf()
	{
		
	}
	@Override
	public boolean isTriggered() 
	{
		goals = bulletin.getGoals();
		STMem = bulletin.getShortTermMemory();
		boolean triggered = false;
		for(String goal : goals)
		{
			String[] split = goal.split(" ");
			if(split[0].equals("Find") && split[2].equals("siblings"))
			{
				foundGoal = goal;
				for(String STmemory : STMem)
				{
					String STMemsplit[] = STmemory.split(" ");
					if(STMemsplit[1].equals("parent-of"))
					{
						triggered = true;
					}
				}
			}
		} 
		return triggered;
	}

	@Override
	public void respond()
	{
		// Find the siblings of the parents in short-term memory.
		// Loop through LT memory and look for siblings of parents in ST memory.
		ArrayList<String> LTMem = bulletin.getLongTermMemory();
		
		// Find the parents whose siblings we seek (in short-term memory)
		ArrayList<String> parents = new ArrayList<String>();
		for(String memory : STMem)
		{
			String[] stmemsplit = memory.split(" ");
			if(stmemsplit[1].equals("parent-of"))
			{
				parents.add(stmemsplit[0]);
			}
		}
		
		for(String LTmemory : LTMem)
		{
			String[] ltmemsplit = LTmemory.split(" ");
			// index 1 will always contain something, it's the minimum LT memory statement
			if(ltmemsplit[1].equals("sibling-of"))
			{
				// index 2 might not exist (gender statement)
				if(ltmemsplit.length > 2)
				{
					if(parents.contains(ltmemsplit[2]))
					{
						// We now know that the found item is a sibling-of a parent in the short term memory
						bulletin.addShortTermMemory(LTmemory);
					}
				}
			}
		}
		bulletin.removeGoal(foundGoal);
	}

}
