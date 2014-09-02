package net.itca.htmw.kinship.core;

import java.util.ArrayList;

import net.itca.htmw.kinship.interfaces.Observable;
import net.itca.htmw.kinship.interfaces.Observer;

/**
 * Accessible anywhere - The bulletin board has three different sections
 * representing Long-term and short-term memory. The short term memory also has a
 * 'Goal' section.
 * (Bulletin = singleton)
 * @author Dylan
 */

public class Bulletin implements Observable
{
	// Bulletin can be observed by demons.
	private ArrayList<Observer> demons;
	public static Bulletin bulletin;
	private ArrayList<String> longTermMemory;
	private ArrayList<String> shortTermMemory;
	private ArrayList<String> goals;
	
	private Bulletin()
	{
		longTermMemory = new ArrayList<String>();
		shortTermMemory = new ArrayList<String>();
		demons = new ArrayList<Observer>();
		goals = new ArrayList<String>();
	}
	
	public static Bulletin getBulletin()
	{
		if(bulletin == null)
		{
			bulletin = new Bulletin();
		}
		return bulletin;
	}
	
	public void addLongTermMemory(String memory)
	{
		longTermMemory.add(memory);
		notifyObservers();
	}
	
	public void addShortTermMemory(String memory)
	{
		shortTermMemory.add(memory);
	}
	
	public void addGoal(String goal)
	{
		goals.add(goal);
		notifyObservers();
	}
	
	public void removeGoal(String goal)
	{
		goals.remove(goal);
	}
	
	public void printBulletin()
	{
		System.out.println("\nLong-term memory\n------------------------");
		longTermMemory.forEach(s -> System.out.println(s));
		System.out.println("\nShort-term memory\n------------------------");
		shortTermMemory.forEach(s -> System.out.println(s));
		System.out.println("\nGoals\n------------------------");
		goals.forEach(s -> System.out.println(s));
	}

	@Override
	public void addObserver(Observer o)
	{
		demons.add(o);
	}

	@Override
	public void removeObserver(Observer o) 
	{
		demons.remove(o);
	}

	@Override
	public void notifyObservers()
	{
		demons.forEach(demon -> demon.update());
	}
	
	public ArrayList<String> getLongTermMemory()
	{
		return longTermMemory;
	}
	
	public ArrayList<String> getShortTermMemory()
	{
		return shortTermMemory;
	}
	
	public ArrayList<String> getGoals()
	{
		return goals;
	}
}
