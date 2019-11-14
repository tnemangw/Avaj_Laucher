package com.avaj_launcher.simulator;

public class Coordinates {
	private final int longitude;
	private final int latitude;
	private final int height;

	Coordinates(int arg_longitude, int arg_latitude, int arg_height)
	{
		this.longitude = arg_longitude;
		this.latitude = arg_latitude;
		this.height = arg_height;
	}

	public int getLongitude()
	{
		return (this.longitude);
	}

	public int getLatitude()
	{
		return (this.latitude);
	}

	public int getHeight()
	{
		return (this.height);
	}

}
