package com.avaj_launcher.simulator;

import com.avaj_launcher.massege_logger.masge_Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulator {
	public static void main(String[] args) {

		if (args.length <= 0)
		{
			System.out.println("Please enter one argument.");
			System.exit(2);
		}
		else
		{
			System.out.println("Input file : " + args[0]);
		}

		try
		{
			masge_Logger.setOutputFile("simulation.txt");
		} catch (IOException e)
		{
			System.out.println("Error : Can't open file simulation.txt for writing.");
			return ;
		}

		WeatherTower tower = new WeatherTower();
		ArrayList<Flyable> flyables = new ArrayList<>();

		try
		{
			FileReader freader = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(freader);

			String fline = br.readLine();
			int nbr_simulations;
			try
			{
				nbr_simulations = Integer.parseInt(fline);
			} catch (NumberFormatException e)
			{
				throw new SimulatorException("Format error : Please enter a valid  number for simulation");
			}
			System.out.println(nbr_simulations + " simulations to run.\n...");

			String current_line;

			while ((current_line = br.readLine()) != null)
			{
				String array[] = current_line.split(" ");
				if (array.length != 5)
					throw new SimulatorException("Format error : Error Each line must have at least five fields.");
				try
				{
					flyables.add(AircraftFactory.newAircraft(array[0], array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
				}
				catch (NumberFormatException e)
				{
					throw new SimulatorException ("Format error : Expected a number [" + current_line + "]");
				}
			}

			for (Flyable flyable: flyables)
				flyable.registerTower(tower);

			for (int i = 0; i < nbr_simulations; i++)
				tower.changeWeather();
			System.out.println(nbr_simulations + "/" + nbr_simulations + " simulations ran.\nYou can find the results in simulation.txt.");
		}
		catch (IOException e)
		{
			System.out.println("Error in the Input/Output.");
		}
		catch (SimulatorException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
