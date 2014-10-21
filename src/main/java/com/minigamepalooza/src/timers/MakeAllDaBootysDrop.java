package com.minigamepalooza.src.timers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.HungerGames;

public class MakeAllDaBootysDrop extends BukkitRunnable {
	private int radius = Integer.MAX_VALUE;
	private List<Block> blocks = new ArrayList<Block>();
	
	private Location spawn;
	
	public MakeAllDaBootysDrop() {
		this.spawn = HungerGames.WORLD_SPAWN.getFeastLocation().getLocation(HungerGames.WORLD);
		this.radius = getDistance(HungerGames.BLOCKS.get(0));
		this.blocks = new ArrayList<Block>(HungerGames.BLOCKS);
	}
	
	@Override
	public void run() {
		int maxIterations = 100;
		int iterations = 0;
		
		Set<Block> toRemove = new HashSet<Block>();
		for(int i = 0; i < this.blocks.size(); i++) {
			if(iterations >= maxIterations) {
				break;
			}
			int distance = getDistance(this.blocks.get(i));
			if(distance >= this.radius) {
				this.makeDaBootyDrop(this.blocks.get(i));
				toRemove.add(this.blocks.get(i));
			} else {
				this.radius -= 2;
				break;
			}
			iterations++;
		}
		this.blocks.removeAll(toRemove);
	}
	
	@SuppressWarnings("deprecation")
	private void makeDaBootyDrop(Block block) {
		//block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
		block.setType(Material.AIR);
	}
	
	private int getDistance(Block b1) {
		Location l = b1.getLocation();
		return (int) Math.sqrt((l.getX() - this.spawn.getX()) * (l.getX() - this.spawn.getX()) + (l.getZ() - this.spawn.getZ()) * (l.getZ() - this.spawn.getZ()));
	}
}
