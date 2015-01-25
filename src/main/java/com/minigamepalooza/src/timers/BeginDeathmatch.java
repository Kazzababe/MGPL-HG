package com.minigamepalooza.src.timers;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.base.player.PaloozaPlayer;
import com.minigamepalooza.src.HungerGames;

public class BeginDeathmatch extends BukkitRunnable {

	@Override
	public void run() {
		HungerGames.FEAST_STARTED = true;
		for(PaloozaPlayer player : HungerGames.getPlayers()) {
			player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
			player.getPlayer().teleport(HungerGames.DEATHMATCH_LOCATION);
		}
	}
}
