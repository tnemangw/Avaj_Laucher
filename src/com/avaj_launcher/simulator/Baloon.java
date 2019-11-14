package com.avaj_launcher.simulator;

public final class Baloon extends Aircraft implements Flyable
{
	protected String actualWeather = null;
	private WeatherTower weatherTower = null;

	Baloon(String arg_name, Coordinates arg_coordinates)
	{
		super(arg_name, arg_coordinates);
		this.type = "Baloon";
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
			this.log("I have the sun in my eyes, it's not cool");
		else if (actualWeather.equals("SNOW"))
			this.log("It's snowing, we're gonna crash !");
		else if (actualWeather.equals("RAIN"))
			this.log("The experience is a bit ruined. You ever been in a hot air balloon under rain ?!");
		else if (actualWeather.equals("FOG"))
			this.log("There is such a view up there... Too bad i can't see anything....");
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
			newCoords = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
		else if (this.actualWeather.equals("RAIN"))
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
		else if (this.actualWeather.equals("FOG"))
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
		else
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
		moving(newCoords);
	}

	protected void landing()
	{
		this.log("landing. - Coordinates : (" + coordinates.getLongitude() + ", "
				+ coordinates.getLatitude() + ", "
				+ coordinates.getHeight()
				+ ") (lat, long, height).");
		this.weatherTower.unregister	(this);
	}
}
