package com.minigamepalooza.src.game;

import com.minigamepalooza.base.player.Players;
import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.core.game.types.GameType;
import com.minigamepalooza.src.HungerGames;

public class CustomGameType implements GameType {

	@Override
	public boolean isGameOver(Game game) {
		return game.getPlayers().size() <= 1;
	}

	@Override
	public Players getWinner(Game game) {
		return game.getPlayers().get(0);
	}
}
