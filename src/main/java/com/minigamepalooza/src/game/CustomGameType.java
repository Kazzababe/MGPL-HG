package com.minigamepalooza.src.game;

import com.minigamepalooza.core.game.Game;
import com.minigamepalooza.core.game.GameType;
import com.minigamepalooza.core.player.Winner;
import com.minigamepalooza.src.HungerGames;

public class CustomGameType implements GameType {

	@Override
	public boolean isGameOver(Game game) {
		return HungerGames.GAME_ENDED;
	}

	@Override
	public Winner getWinner(Game game) {
		return game.getPlayers().get(0);
	}
}
