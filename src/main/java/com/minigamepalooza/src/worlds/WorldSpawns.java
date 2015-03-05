package com.minigamepalooza.src.worlds;

import java.util.Arrays;
import java.util.List;

public enum WorldSpawns {
	HUNGER("hunger", new Coordinates[] {new Coordinates(-256, 0, -256), new Coordinates(383, 150, 383)}, new Coordinates(0, 0, 0), new Coordinates(10, 58, -19), new Coordinates(6, 58, -21), new Coordinates(3, 58, -24), new Coordinates(1, 58, -27), new Coordinates(-3, 58, -29), new Coordinates(-6, 58, -33), new Coordinates(-8, 58, -37), new Coordinates(-6, 58, -41), new Coordinates(-4, 58, -45), 
					 new Coordinates(-1, 58, -48), new Coordinates(2, 58, -51), new Coordinates(6, 58, -54), new Coordinates(10, 58, -55), new Coordinates(14, 58, -54), new Coordinates(18, 58, -51), new Coordinates(21, 58, -48), new Coordinates(24, 58, -45), new Coordinates(27, 58, -41), 
					 new Coordinates(28, 58, -37), new Coordinates(27, 58, -33), new Coordinates(24, 58, -29), new Coordinates(20, 58, -26), new Coordinates(17, 58, -24), new Coordinates(14, 58, -21)), 
	CALDERA("caldera", new Coordinates[] {new Coordinates(-2074, 54, 706), new Coordinates(-1355, 256, 20)}, new Coordinates(-1756, 58, 409), new Coordinates(-1718, 158, 446), new Coordinates(-1695, 160, 442), 
					   new Coordinates(-1692, 160, 422), new Coordinates(-1693, 163, 405), new Coordinates(-1702, 161, 385), new Coordinates(-1696, 167, 365), new Coordinates(-1710, 165, 365), new Coordinates(-1732, 163, 363), 
					   new Coordinates(-1745, 162, 359), new Coordinates(-1764, 158, 364), new Coordinates(-1778, 159, 369), new Coordinates(-1794, 161, 379), new Coordinates(-1802, 164, 393), new Coordinates(-1808, 162, 405), 
					   new Coordinates(-1804, 158, 421), new Coordinates(-1802, 158, 434), new Coordinates(-1794, 162, 443), new Coordinates(-1786, 161, 447), new Coordinates(-1768, 163, 454), new Coordinates(-1756, 166, 462), 
					   new Coordinates(-1736, 163, 465), new Coordinates(-1718, 157, 461));
	
	private String worldName;
	private Coordinates feastLocation;
	private List<Coordinates> locations;
	private Coordinates[] dimensions;
	
	private WorldSpawns(String worldName, Coordinates[] dimensions, Coordinates feastLocation, Coordinates... locations) {
		this.worldName = worldName;
		this.feastLocation = feastLocation;
		this.dimensions = dimensions;
		this.locations = Arrays.asList(locations);
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public Coordinates[] getDimensions() {
		return this.dimensions;
	}
	
	public Coordinates getFeastLocation() {
		return this.feastLocation;
	}
	
	public List<Coordinates> getLocations() {
		return this.locations;
	}
	
	public static WorldSpawns getByName(String name) {
		for(WorldSpawns spawn : values()) {
			if(spawn.getWorldName().equals(name)) {
				return spawn;
			}
		}
		return null;
	}
}
