package com.minigamepalooza.src.timers;

import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.HungerGames;
import com.minigamepalooza.src.entities.FakePlayer;

public class RemoveDeadPlayer extends BukkitRunnable {
	private FakePlayer fakePlayer;
	
	public RemoveDeadPlayer(FakePlayer fakePlayer) {
		this.fakePlayer = fakePlayer;
	}

	@Override
	public void run() {
		this.fakePlayer.remove();
		HungerGames.DEAD_PLAYERS.remove(this.fakePlayer);
	}
}
