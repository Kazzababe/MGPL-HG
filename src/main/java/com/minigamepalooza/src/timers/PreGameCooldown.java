package com.minigamepalooza.src.timers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.HungerGames;

public class PreGameCooldown extends BukkitRunnable {
	private int time = 0;

	@Override
	public void run() {
		switch(this.time) {
			case 0:
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				for(Player player : Bukkit.getOnlinePlayers()) {
					player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
				}
				break;
			case 9:
				for(Player player : Bukkit.getOnlinePlayers()) {
					player.playSound(player.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
					player.sendMessage("LET THE GAMES BEGIN!");
				}
				HungerGames.PREGAME_COOLDOWN = false;
				this.cancel();
				
				new MakeAllDaBootysDrop().runTaskTimer(HungerGames.getInstance(), 2L * 1, 2L * 1);
				
				break;
		}
		this.time++;
	}
}
