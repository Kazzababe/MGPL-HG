package com.minigamepalooza.src.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.loot.LootUtility;

public class PlayerInteractListeners implements Listener {
	private Set<Location> filledChests = new HashSet<Location>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		GamePlayer player = HungerGames.getPlayer(event.getPlayer());
		
		Action action = event.getAction();
		
		if(!player.isSpectating()) {
			if(action == Action.RIGHT_CLICK_BLOCK) {
				Block block = event.getClickedBlock();
				if(block.getType() == Material.CHEST) {
					if(!this.filledChests.contains(block.getLocation())) {
						LootUtility.fillChest(block, LootUtility.getRandomLootTable());
						this.filledChests.add(block.getLocation());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Location to = event.getTo();
		Location from = event.getFrom();
		
		if(HungerGames.PREGAME_COOLDOWN) {
			if(!to.getBlock().equals(from.getBlock())) {
				event.setTo(from);
			}
		}
	}
}
