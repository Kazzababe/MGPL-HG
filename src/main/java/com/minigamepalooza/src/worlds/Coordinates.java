package com.minigamepalooza.src.worlds;

import org.bukkit.Location;
import org.bukkit.World;

public class Coordinates {
	private int x;
	private int y;
	private int z;
	
	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public Location getLocation(World world) {
		return new Location(world, this.x + 0.5, this.y + 0.5, this.z + 0.5);
	}
}
