package com.avaj_launcher.simulator;

public class WeatherProvider
{
	private WeatherProvider() { }

	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static final String weather[] = {"RAIN", "FOG", "SUN", "SNOW"};

	public static WeatherProvider getProvider() {
		return (WeatherProvider.weatherProvider);
	}

	public String getCurrentWeather(Coordinates coordinates)
	{
		int i = (coordinates.getLongitude() + coordinates.getHeight() + coordinates.getLatitude()) % 20;
		if (i < 5)
			return weather[0];
		else if (i < 10)
			return weather[1];
			else if (i < 15)
			return weather[2];
		else
			return weather[3];
	}
}