package com.minigamepalooza.src.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.entities.FakePlayer;
import com.minigamepalooza.src.specializations.Kit;
import com.minigamepalooza.src.timers.BeginFeast;
import com.minigamepalooza.src.timers.RemoveDeadPlayer;

public class DamageListeners implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		Entity damaged = event.getEntity();
		
		if(damager instanceof Arrow) {
			GamePlayer shooter = HungerGames.getPlayer((Player) ((Arrow) damager).getShooter());
			if(shooter.hasData("specialty")) {
				Kit kit = (Kit) shooter.get("specialty");
				if(kit != null) {
					event.setDamage(event.getDamage() * kit.getBowDamageModifier());
				}
			}
		} else if(damager instanceof Player) {
			GamePlayer player = HungerGames.getPlayer((Player) damaged);
			if(player.hasData("specialty")) {
				Kit kit = (Kit) player.get("specialty");
				if(kit != null) {
					ItemStack item = player.getPlayer().getItemInHand();
					if(item != null) {
						if(item.getType().name().contains("AXE")) {
							event.setDamage(event.getDamage() * kit.getAxeDamageModifer());
						} else if(item.getType().name().contains("SWORD")) {
							event.setDamage(event.getDamage() * kit.getSwordDamageModifier());
						}
					}
				}
			}
		}
		if(damaged instanceof Player) {
			GamePlayer player = HungerGames.getPlayer((Player) damaged);
			if(player.hasData("pecialty")) {
				Kit kit = (Kit) player.get("specialty");
				if(kit != null) {
					event.setDamage(event.getDamage() / kit.getArmourModifier());
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDamage(EntityDamageEvent event) {
		System.out.println(event.getEntity() + ":" + event.isCancelled() + ":" + HungerGames.getGame().isRunning() + ":" + ((event.getEntity() instanceof Player)? HungerGames.getPlayer(((Player) event.getEntity())).isSpectating() : ""));
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event) {
		GamePlayer player = HungerGames.getPlayer(event.getEntity());
		
		if(!HungerGames.FEAST_STARTED) {
			if(HungerGames.getGame().getPlayers().size() <= 6) {
				for(GamePlayer p : HungerGames.getGame().getPlayers()) {
					p.sendMessage(ChatColor.RED + "Game Masters: " + ChatColor.GRAY + "A feast is about to begin at the center of the map");
				}
				new BeginFeast().runTaskLater(HungerGames.getInstance(), 100L);
			}
		}
		
		if(player.inGame()) {
			String killer = (player.getPlayer().getKiller() instanceof Player)? player.getPlayer().getKiller().getName() : event.getEntity().getLastDamageCause().getEntityType().name();
			for(GamePlayer p : HungerGames.getGame().getPlayers()) {
				p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + player.getName() + ChatColor.RESET + ChatColor.BLUE + " has been killed by " + ChatColor.GRAY + ChatColor.BOLD + killer + ChatColor.RESET + ChatColor.BLUE + " and eliminated");
			}
			
			FakePlayer fakePlayer = FakePlayer.spawnPlayer(event.getEntity(), true);
			HungerGames.DEAD_PLAYERS.add(fakePlayer);
			
			new RemoveDeadPlayer(fakePlayer).runTaskLater(HungerGames.getInstance(), 90L * 20L);
		}
	}
}
