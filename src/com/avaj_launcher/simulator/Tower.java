package com.avaj_launcher.simulator;


import com.avaj_launcher.massege_logger.masge_Logger;

import java.util.ArrayList;

public abstract class Tower {
	private ArrayList<Flyable> observer = new ArrayList<Flyable>();

	public void register(Flyable flyable)
	{
		observer.add(flyable);
		masge_Logger.log("Tower says: " + flyable.getDescription() + " registered to weather tower.");
	}

	public  void unregister(Flyable flyable)
	{
		observer.remove(flyable);
		masge_Logger.log("Tower says: " + flyable.getDescription() + " unregistered from weather tower.");
	}

	protected void conditionsChanged()
	{
		for(int i = 0; i < observer.size(); i++)
		{
			observer.get(i).updateConditions();
		}
	}
}
