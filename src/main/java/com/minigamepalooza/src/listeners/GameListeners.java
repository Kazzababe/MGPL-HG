package com.minigamepalooza.src.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.core.events.game.GameStartEvent;
import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.core.items.Items;
import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.specializations.Kit;
import com.minigamepalooza.src.timers.PreGameCooldown;

public class GameListeners implements Listener {

	@EventHandler
	public void onGameStart(GameStartEvent event) {
		Game game = event.getGame();
		if(game.getName() == HungerGames.NAME) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				GamePlayer gamePlayer = HungerGames.getPlayer(player);
				
				gamePlayer.reset();
				if(!gamePlayer.hasData("specialty")) {
					gamePlayer.addData("specialty", Kit.WARRIOR);
				} else {
					Kit kit = (Kit) gamePlayer.get("specialty");
					player.setWalkSpeed(0.2F * (float) kit.getSpeedModifier());
				}
			}
		}
		HungerGames.PREGAME_COOLDOWN = true;
		
		new PreGameCooldown().runTaskTimer(HungerGames.getInstance(), 20L, 20L);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				player.getInventory().setItem(0, Items.getItem("SelectionGUI").getItem());
				player.updateInventory();
			}
		}.runTaskLater(HungerGames.getInstance(), 10L);
	}
}
