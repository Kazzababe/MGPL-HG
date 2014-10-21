package com.minigamepalooza.src;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.minigamepalooza.core.GameSettings;
import com.minigamepalooza.core.PaloozaPlugin;
import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.core.game.Games;
import com.minigamepalooza.core.items.Items;
import com.minigamepalooza.src.gui.KitSelectionItem;
import com.minigamepalooza.src.listeners.DamageListeners;
import com.minigamepalooza.src.listeners.GameListeners;
import com.minigamepalooza.src.listeners.PlayerInteractListeners;
import com.minigamepalooza.src.worlds.WorldSpawns;

public class HungerGames extends PaloozaPlugin {
	private static HungerGames instance;
	
	public static final String NAME = "HG";
	public static boolean PREGAME_COOLDOWN = false;
	
	@Override
	public Game getNewGame() {
		World world = Bukkit.getServer().getWorld("hunger");
		
		Location[] locations = null;
		for(WorldSpawns spawns : WorldSpawns.values()) {
			if(spawns.getWorldName().equals(world.getName())) {
				locations = new Location[spawns.getLocations().size()];
				for(int i = 0; i < locations.length; i++) {
					locations[i] = spawns.getLocations().get(i).getLocation(world);
				}
				break;
			}
		}
		
		return new Game(NAME, new Location(world, 10, 165, -36), locations);
	}

	@Override
	public Games onPluginEnable() {
		instance = this;
		
		GameSettings.respawn = false;
		GameSettings.pvp = true;
		GameSettings.hunger = false;
		GameSettings.maxPlayers = 24;
		GameSettings.minPlayers = 1;
		GameSettings.friendlyMobs = true;
		GameSettings.breakableBlocks.addAll(Arrays.asList(Material.LEAVES, Material.GLASS, Material.STAINED_GLASS, Material.STAINED_GLASS_PANE, Material.CROPS));
		
		Items.registerItem("SelectionGUI", new KitSelectionItem());
		
		this.registerListener(new PlayerInteractListeners());
		this.registerListener(new GameListeners());
		this.registerListener(new DamageListeners());
		
		return Games.HungerGames;
	}
	
	public static HungerGames getInstance() {
		return instance;
	}
}
