package com.minigamepalooza.src.gui;

import org.bukkit.Material;

import com.minigamepalooza.core.items.Item;
import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.core.utils.ItemBuilder;

public class KitSelectionItem extends Item {

	public KitSelectionItem() {
		super(new ItemBuilder(Material.BOOK).setTitle("Select your specialty"));
	}

	@Override
	public boolean onItemClick(GamePlayer player) {
		player.getPlayer().openInventory(KitSelectionGUI.getGUI(player).getInventory());
		return false;
	}
}
