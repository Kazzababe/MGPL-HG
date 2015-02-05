package com.minigamepalooza.src.worlds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class Coordinates {
	public static final List<Coordinates> DM_SPAWNS = new ArrayList<Coordinates>();
	
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
	
	public Vector getVector() {
		return new Vector(this.x, this.y, this.z);
	}
	
	static {
		//DM_SPAWNS.add(new Coordinates(21, 4, 0));
		//DM_SPAWNS.add(new Coordinates(15, 4, 14));
		DM_SPAWNS.add(new Coordinates(1, 4, 20));
		DM_SPAWNS.add(new Coordinates(-13, 4, 14));
		DM_SPAWNS.add(new Coordinates(-19, 4, 0));
		DM_SPAWNS.add(new Coordinates(-13, 4, -14));
		DM_SPAWNS.add(new Coordinates(1, 4, -20));
		DM_SPAWNS.add(new Coordinates(15, 4, -14));
	}
}
