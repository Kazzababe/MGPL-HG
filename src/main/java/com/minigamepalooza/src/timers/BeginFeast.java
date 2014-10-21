package com.minigamepalooza.src.timers;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.loot.LootUtility;

public class BeginFeast extends BukkitRunnable {

	@Override
	public void run() {
		HungerGames.FEAST_STARTED = true;
		
		Location location = HungerGames.WORLD_SPAWN.getFeastLocation().getLocation(HungerGames.WORLD);
		for(int x = location.getBlockX() - 5; x <= location.getBlockX() + 5; x++) {
			for(int z = location.getBlockZ() - 5; z <= location.getBlockZ() + 5; z++) {
				for(int y = location.getBlockY() - 5; y <= location.getBlockY() + 5; y++) {
					Location newLocation = new Location(location.getWorld(), x, y, z);
					if(newLocation.getBlock().getState() instanceof Chest) {
						LootUtility.fillChest(newLocation.getBlock(), LootUtility.feastLoot);
					}
				}
			}
		}
	}
}
