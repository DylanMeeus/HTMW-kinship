package net.itca.core.htmw.kinship.demontypes;

import java.util.ArrayList;

import net.itca.htmw.kinship.core.Bulletin;
import net.itca.htmw.kinship.interfaces.DemonFunctions;

public class DistinguishGender implements DemonFunctions
{
	Bulletin bulletin = Bulletin.getBulletin();
	String trigger = "Distinguish Uncles/Aunts";
	ArrayList<String> goals;
	
	public DistinguishGender()
	{
		
	}
	
	@Override
	public boolean isTriggered()
	{
		goals = bulletin.getGoals();
		return goals.contains(trigger);
	}

	@Override
	public void respond() 
	{
		// We can use the initial goal to figure out whether we look for uncles (male) or aunts (female)

		bulletin.removeGoal(trigger);	
		
		String gender = "";
		for(String goal : goals)
		{
			if(goal.contains("?")) // indicative of the question asked
			{
				gender = goal.contains("uncle-of")?"is-male":"is-female";
			}
		}
		
		// Now we look for the male siblings.
		ArrayList<String> STMem = bulletin.getShortTermMemory();
		ArrayList<String> LTMem = bulletin.getLongTermMemory();
		// We'll quickly load the siblings of the short-term memory, essentially put them in the short term memory of the program
		ArrayList<String> siblingsOfParents = new ArrayList<String>();
		for(String STmemory : STMem)
		{
			if(STmemory.contains("sibling-of"))
			{
				siblingsOfParents.add(STmemory.split(" ")[0]);
			}
		}
		
		
		ArrayList<String> tempLT = new ArrayList<String>();
		for(String LTmemory : LTMem)
		{
			String[] ltmemorysplit = LTmemory.split(" ");
			if(siblingsOfParents.contains(ltmemorysplit[0]) && ltmemorysplit[1].equals(gender))
			{
				// We now have the sibling in long term memory & know he is male. We can thus add the "uncle" relationship
			   	 tempLT.add(ltmemorysplit[0] + " " + (gender.equals("is-male")?"uncle-of":"aunt-of") + " Me");
				//bulletin.addLongTermMemory("Gordie uncle-of Me");
			}
		}
		for(String foundmem : tempLT)
		{	
			bulletin.addLongTermMemory(foundmem);
		}
	}

}
