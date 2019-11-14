package com.avaj_launcher.simulator;

public final class Helicopter extends Aircraft implements Flyable
{
	protected String actualWeather = null;
	private WeatherTower weatherTower = null;

	Helicopter(String arg_name, Coordinates arg_coordinates)
	{
		super(arg_name, arg_coordinates);
		this.type = "Helicopter";
	}

	public void updateConditions()
	{
		if (this.weatherTower == null)
		{
			System.out.println("Error ");
			return ;
		}
		String newWeather = weatherTower.getWeather(coordinates);

		if (actualWeather == null || !actualWeather.equals(newWeather))
			actualWeather = newWeather;
		if (actualWeather.equals("SUN"))
			this.log("Wow, it's hot. WE CAN FLY");
		else if (actualWeather.equals("SNOW"))
			this.log("It's freezing !");
		else if (actualWeather.equals("RAIN"))
			this.log("HELP IT'S RAINING GROUND IS WET");
		else if (actualWeather.equals("FOG"))
			this.log("I can't see anything two meters in front of me..");
		this.orderMove();
	}

	public void registerTower(WeatherTower arg_weatherTower)
	{
		arg_weatherTower.register(this);
		this.weatherTower = arg_weatherTower;
	}

	private void orderMove()
	{
		Coordinates newCoords;
		if (this.actualWeather.equals("SUN"))
			newCoords = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
		else if (this.actualWeather.equals("RAIN"))
			newCoords = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
		else if (this.actualWeather.equals("FOG"))
			newCoords = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
		else
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
		moving(newCoords);
	}

	protected void landing()
	{
		this.log("landing. - Coordinates : (" + coordinates.getLongitude() + ", "
				+ coordinates.getLatitude() + ", "
				+ coordinates.getHeight()
				+ ") (lat, long, height).");
		this.weatherTower.unregister(this);
	}
}
