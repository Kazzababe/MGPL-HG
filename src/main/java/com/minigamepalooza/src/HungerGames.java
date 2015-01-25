package com.minigamepalooza.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.paulbgd.blocks.api.BlocksAPI;
import me.paulbgd.blocks.api.block.Blocks;
import me.paulbgd.blocks.api.block.loader.SchematicFormat;
import me.paulbgd.blocks.api.block.paster.Paster;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.base.game.Games;
import com.minigamepalooza.base.items.Items;
import com.minigamepalooza.core.GameSettings;
import com.minigamepalooza.core.PaloozaPlugin;
import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.src.entities.FakePlayer;
import com.minigamepalooza.src.game.CustomGameType;
import com.minigamepalooza.src.gui.KitSelectionItem;
import com.minigamepalooza.src.listeners.DamageListeners;
import com.minigamepalooza.src.listeners.GameListeners;
import com.minigamepalooza.src.listeners.PlayerInteractListeners;
import com.minigamepalooza.src.worlds.WorldSpawns;

public class HungerGames extends PaloozaPlugin {
	private static HungerGames instance;
	
	public static final String NAME = "HG";
	public static boolean PREGAME_COOLDOWN = false;
	public static boolean FEAST_STARTED = false;
	public static boolean GAME_ENDED = false;
	public static boolean GAME_READY = false;
	
	public static World WORLD;
	public static WorldSpawns WORLD_SPAWN;
	public static Location DEATHMATCH_LOCATION;
	
	public static List<Block> BLOCKS = new ArrayList<Block>();
	public static final Set<FakePlayer> DEAD_PLAYERS = new HashSet<FakePlayer>();
	
	@Override
	public Game getNewGame() {
		WORLD = Bukkit.getServer().getWorld("hunger");
		WORLD_SPAWN = WorldSpawns.getByName(WORLD.getName());
		
		Location[] locations = new Location[WORLD_SPAWN.getLocations().size()];
		for(int i = 0; i < locations.length; i++) {
			locations[i] = WORLD_SPAWN.getLocations().get(i).getLocation(WORLD);
		}
		
		return new Game(NAME, new Location(WORLD, 100000, 165, 100000), locations);
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
		GameSettings.breakableBlocks.addAll(Arrays.asList(Material.LEAVES, Material.GLASS, Material.STAINED_GLASS, Material.STAINED_GLASS_PANE));
		GameSettings.gameType = new CustomGameType();
		
		Items.registerItem("SelectionGUI", new KitSelectionItem());
		
		this.registerListener(new PlayerInteractListeners());
		this.registerListener(new GameListeners());
		this.registerListener(new DamageListeners());
		
		new BukkitRunnable() {
			@Override
			public void run() {
				Blocks deathmatch = null;
				try {
					deathmatch = BlocksAPI.loadResource("dm.schematic", new SchematicFormat(), HungerGames.getInstance());
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				try {
					if(deathmatch != null) {
						HungerGames.DEATHMATCH_LOCATION = HungerGames.WORLD_SPAWN.getFeastLocation().getLocation(HungerGames.WORLD).clone().add(1000, 0, 1000);
						HungerGames.DEATHMATCH_LOCATION.setY(200);
						deathmatch.paste(HungerGames.DEATHMATCH_LOCATION.getBlock(), Paster.SIMPLE_PASTER, Bukkit.getConsoleSender(), true, false, () -> {
							getLogger().info("Deathmatch schematic pasted");
							HungerGames.GAME_READY = true;
						});
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}.runTaskLater(this, 1L);
		
		return Games.HungerGames;
	}
	
	public static HungerGames getInstance() {
		return instance;
	}
}
