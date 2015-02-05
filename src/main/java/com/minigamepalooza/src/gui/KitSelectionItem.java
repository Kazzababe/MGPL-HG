package com.minigamepalooza.src.gui;

import org.bukkit.Material;

import com.minigamepalooza.base.items.Item;
import com.minigamepalooza.base.player.PaloozaPlayer;
import com.minigamepalooza.base.utils.ItemBuilder;

public class KitSelectionItem extends Item {

	public KitSelectionItem() {
		super(new ItemBuilder(Material.BOOK).setTitle("Select your specialty"));
	}

	@Override
	public boolean onItemClick(PaloozaPlayer player) {
		player.getPlayer().openInventory(KitSelectionGUI.getGUI(player).getInventory());
		return false;
	}
}
