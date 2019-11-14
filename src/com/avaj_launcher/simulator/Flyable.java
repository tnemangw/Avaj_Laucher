package com.avaj_launcher.simulator;

import com.avaj_launcher.simulator.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
	String getDescription();
}
