package com.avaj_launcher.simulator;

public final class JetPlane extends Aircraft implements Flyable
{
	protected String actualWeather = null;
	private WeatherTower weatherTower = null;

	JetPlane(String arg_name, Coordinates arg_coordinates)
	{
		super(arg_name, arg_coordinates);
		this.type = "JetPlane";
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
			this.log("IT'S SUNNY AND I AM GOING FAAAAST WOOOOOOOOOOW !!!!!!!!!!");
		else if (actualWeather.equals("SNOW"))
			this.log("It's beautiful !!!!");
		else if (actualWeather.equals("RAIN"))
			this.log("Well i can't see anything because of the rain but i guess it's cool to fly in a JetPlane anyway !");
		else if (actualWeather.equals("FOG"))
			this.log("Is there something in front of me ? My radar isn't working ! Do i have something to honk ?! Oh yes this ! WOW IT WAS THE EJECTABLE SEAT WOOOOOOW");
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
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
		else if (this.actualWeather.equals("RAIN"))
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
		else if (this.actualWeather.equals("FOG"))
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
		else
			newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
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
