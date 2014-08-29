package net.itca.htmw.kinship.core;

import net.itca.core.htmw.kinship.demontypes.DistinguishGender;
import net.itca.core.htmw.kinship.demontypes.GoalReached;
import net.itca.core.htmw.kinship.demontypes.ParentOf;
import net.itca.core.htmw.kinship.demontypes.SiblingOf;
import net.itca.core.htmw.kinship.demontypes.UncleOf;

/**
 * A class representing part of the mind. 
 * It acts as the main organ of the program in this case.
 * (setting up initial data etc)
 * @author Dylan
 *
 */

public class Mind
{
	Bulletin bulletin;
	public Mind()
	{
		bulletin = Bulletin.getBulletin();
		addInitialKin();
		System.out.println("Original mind-bulletin setup");
		bulletin.printBulletin();
		initializeDemons();
		addInitialGoal();
	}
	
	/**
	 * The initial kin is the initial setup used in the book.
	 * (page: 71)
	 */
	public void addInitialKin()
	{
		bulletin.addLongTermMemory("Abel parent-of Me");
		bulletin.addLongTermMemory("Abel is-male");
		bulletin.addLongTermMemory("Bella is-female");
		bulletin.addLongTermMemory("Bella parent-of Me");
		bulletin.addLongTermMemory("Claudia sibling-of Me");
		bulletin.addLongTermMemory("Claudia is-female");
		bulletin.addLongTermMemory("Duddie sibling-of Me");
		bulletin.addLongTermMemory("Duddie is-male");
		bulletin.addLongTermMemory("Edgar sibling-of Abel");
		bulletin.addLongTermMemory("Edgar is-male");
		bulletin.addLongTermMemory("Fanny sibling-of Abel");
		bulletin.addLongTermMemory("Fanny is-female");
		bulletin.addLongTermMemory("Gordie sibling-of Bella");
		bulletin.addLongTermMemory("Gordie is-male");
	}
	
	/**
	 * The initial question posed on the mind. 
	 * (Page: 71)
	 */
	public void addInitialGoal()
	{
		bulletin.addGoal("Gordie uncle-of Me?");
	}
	
	/**
	 * Spawns the demons (further explanation on it-ca.net)
	 */
	public void initializeDemons()
	{
		Demon d1 = new Demon(new GoalReached());
		bulletin.addObserver(d1);
		Demon d2 = new Demon(new UncleOf());
		bulletin.addObserver(d2);
		Demon d3 = new Demon(new ParentOf());
		bulletin.addObserver(d3);
		Demon d4 = new Demon(new SiblingOf());
		bulletin.addObserver(d4);
		Demon d5 = new Demon(new DistinguishGender());
		bulletin.addObserver(d5);
	}
}
