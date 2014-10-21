package com.minigamepalooza.src.worlds;

import java.util.Arrays;
import java.util.List;

public enum WorldSpawns {
	HUNGER("hunger", new Coordinates(0, 0, 0), new Coordinates(10, 58, -19), new Coordinates(6, 58, -21), new Coordinates(3, 58, -24), new Coordinates(1, 58, -27), new Coordinates(-3, 58, -29), new Coordinates(-6, 58, -33), new Coordinates(-8, 58, -37), new Coordinates(-6, 58, -41), new Coordinates(-4, 58, -45), 
					 new Coordinates(-1, 58, -48), new Coordinates(2, 58, -51), new Coordinates(6, 58, -54), new Coordinates(10, 58, -55), new Coordinates(14, 58, -54), new Coordinates(18, 58, -51), new Coordinates(21, 58, -48), new Coordinates(24, 58, -45), new Coordinates(27, 58, -41), 
					 new Coordinates(28, 58, -37), new Coordinates(27, 58, -33), new Coordinates(24, 58, -29), new Coordinates(20, 58, -26), new Coordinates(17, 58, -24), new Coordinates(14, 58, -21));
	
	private String worldName;
	private Coordinates feastLocation;
	private List<Coordinates> locations;
	
	private WorldSpawns(String worldName, Coordinates feastLocation, Coordinates... locations) {
		this.worldName = worldName;
		this.feastLocation = feastLocation;
		this.locations = Arrays.asList(locations);
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public Coordinates getFeastLocation() {
		return this.feastLocation;
	}
	
	public List<Coordinates> getLocations() {
		return this.locations;
	}
}
