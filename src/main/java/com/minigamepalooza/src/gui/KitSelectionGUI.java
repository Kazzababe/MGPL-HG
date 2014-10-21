package com.minigamepalooza.src.gui;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.core.guis.PlayerGUI;
import com.minigamepalooza.core.player.GamePlayer;
import com.minigamepalooza.src.specializations.Kit;

public class KitSelectionGUI extends PlayerGUI {

	public KitSelectionGUI(GamePlayer player) {
		super(player, "Select a specialty", (int) (Math.ceil(Kit.KITS.size() / 9.0) * 9.0));
		
		for(Kit kit : Kit.KITS) {
			this.addItem(kit.getItem());
		}
	}

	@Override
	public void onClick(GamePlayer player, ItemStack item) {
		for(Kit kit : Kit.KITS) {
			if(kit.getItem().equals(item)) {
				player.addData("specialty", kit);
				player.sendMessage(ChatColor.GREEN + "You have selected " + kit.getName());
				break;
			}
		}
	}
	
	public static KitSelectionGUI getGUI(GamePlayer player) {
		return new KitSelectionGUI(player);
	}
}
