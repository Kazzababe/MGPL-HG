package com.minigamepalooza.src.listeners;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.base.api.Title;
import com.minigamepalooza.base.items.Items;
import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.core.game.events.GameStartEvent;
import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.specializations.Kit;
import com.minigamepalooza.src.timers.PreGameCooldown;

public class GameListeners implements Listener {

	@EventHandler
	public void onGameStart(GameStartEvent event) {
		Game game = event.getGame();
		if(game.getName() == HungerGames.NAME) {
			Title title = Title.builder().title(ChatColor.BOLD + "" + ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Welcome to the Arena tributes").sub(ChatColor.BOLD + "" + ChatColor.RED + "Good luck and may the odds be ever in your favor").fadeOut(20 * 1).stay(20 * 7).build();
			for(Player player : Bukkit.getOnlinePlayers()) {
				GamePlayer gamePlayer = HungerGames.getPlayer(player);
				
				gamePlayer.reset();
				if(!gamePlayer.hasData("specialty")) {
					gamePlayer.addData("specialty", Kit.WARRIOR);
				}
				Kit kit = (Kit) gamePlayer.get("specialty");
				
				player.setWalkSpeed(0.2F * (float) kit.getSpeedModifier());
				player.setMaxHealth(kit.getHealth());
				player.setHealth(player.getMaxHealth());
				
				title.send(gamePlayer);
			}
		}
		HungerGames.PREGAME_COOLDOWN = true;
		
		new PreGameCooldown().runTaskTimer(HungerGames.getInstance(), 20L, 20L);
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		if(!HungerGames.GAME_READY) {
			event.disallow(Result.KICK_OTHER, "The game is still being setup, try joining later");
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		new BukkitRunnable() {
			public void run() {
				player.getInventory().setItem(0, Items.getItem("SelectionGUI").getItem());
				player.updateInventory();
			}
		}.runTaskLater(HungerGames.getInstance(), 10L);
	}
	
	public void onBlockPhysics(BlockPhysicsEvent event) {
		Material mat = event.getBlock().getType();
		if(mat == Material.STATIONARY_WATER || mat == Material.STATIONARY_LAVA) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		Block block = event.getToBlock();
		if(block.getType() == Material.WATER || block.getType() == Material.LAVA) {
			event.setCancelled(true);
		}
	}
}
