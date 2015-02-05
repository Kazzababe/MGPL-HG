package com.minigamepalooza.src.timers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.minigamepalooza.base.player.PaloozaPlayer;
import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.worlds.Coordinates;

public class BeginDeathmatch extends BukkitRunnable {

	@Override
	public void run() {
		HungerGames.FEAST_STARTED = true;
		HungerGames.PREDM_COOLDOWN = true;
		int count = 0;
		for(PaloozaPlayer player : HungerGames.getPlayers()) {
			player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
			player.getPlayer().teleport(HungerGames.DEATHMATCH_LOCATION.clone().add(Coordinates.DM_SPAWNS.get(count).getVector()));
			count++;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				HungerGames.PREDM_COOLDOWN = false;
				for(PaloozaPlayer player : HungerGames.getPlayers()) {
					Player p = player.getPlayer();
					Location loc = HungerGames.DEATHMATCH_LOCATION.clone();
					loc.setY(p.getLocation().getY() + 5);
					
					Vector vec = loc.toVector().subtract(p.getLocation().toVector()).multiply(0.075);
					p.setVelocity(vec);
				}
			}
		}.runTaskLater(HungerGames.getInstance(), 20L * 2);
	}
}
