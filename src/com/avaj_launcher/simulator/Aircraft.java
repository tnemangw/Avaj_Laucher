package com.avaj_launcher.simulator;

import com.avaj_launcher.massege_logger.masge_Logger;

public abstract class   Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected String type;

	private static long idCounter = 0;

	protected Aircraft(String arg_name, Coordinates arg_coordinates)
	{
		this.name = arg_name;
		this.coordinates = arg_coordinates;
		this.id = this.nextId();
	}

	private long nextId()
	{
		Aircraft.idCounter++;
		return Aircraft.idCounter - 1;
	}

	protected void log(String message)
	{
		String toLog = getDescription() + ": " + message;
		masge_Logger.log(toLog);
	}

	public String getDescription()
	{
		return this.type + '*' + this.name + '(' + this.id + ")";
	}

	protected void moving(Coordinates coordinatess)
	{
		int	newLatitude;
		int	newLongitude;
		int	newHeight;

		if (coordinatess.getHeight() > 100)
			newHeight = 100;
		else if (coordinatess.getHeight() <= 0)
		{
			this.coordinates = new Coordinates(coordinatess.getLongitude(), coordinatess.getLatitude(), 0);
			landing();
			return ;
		}
		else
			newHeight = coordinatess.getHeight();

		if (coordinatess.getLatitude() < 0)
			newLatitude = 0;
		else
			newLatitude = coordinatess.getLatitude();

		if (coordinatess.getLongitude() < 0)
			newLongitude = 0;
		else
			newLongitude = coordinatess.getLongitude();

		this.coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
	}

	protected abstract void landing();
}
