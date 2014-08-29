package net.itca.htmw.kinship.core;

import net.itca.htmw.kinship.interfaces.DemonFunctions;
import net.itca.htmw.kinship.interfaces.Observer;

/**
 * Simple, self-contained entity that jumps into action
 * when triggered. (Triggers are inscriptions on the bulletin board).
 * @author Dylan
 *
 */
public class Demon implements Observer
{
	public DemonFunctions demonBehaviour;
	
	public Demon(DemonFunctions behaviour)
	{
		demonBehaviour = behaviour;
	}

	@Override
	public void update() 
	{
		System.out.println("Demon-activated");
		if(demonBehaviour.isTriggered())
		{
			//System.out.println("Demon-triggered");
			demonBehaviour.respond();
			Bulletin b = Bulletin.getBulletin();
			b.printBulletin();
		}

	}
}
