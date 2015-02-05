package com.minigamepalooza.src.gui;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.base.gui.PlayerGUI;
import com.minigamepalooza.base.player.PaloozaPlayer;
import com.minigamepalooza.src.specializations.Kit;

public class KitSelectionGUI extends PlayerGUI {
	public KitSelectionGUI(PaloozaPlayer player) {
		super(player, "Select a specialty", (int) (Math.ceil(Kit.KITS.size() / 9.0) * 9.0));
		
		for(Kit kit : Kit.KITS) {
			this.addItem(kit.getItem());
		}
	}

	@Override
	public void onClick(PaloozaPlayer player, ItemStack item) {
		for(Kit kit : Kit.KITS) {
			if(kit.getItem().equals(item)) {
				player.addData("specialty", kit);
				player.sendMessage(ChatColor.GREEN + "You have selected " + kit.getName());
				break;
			}
		}
	}
	
	public static KitSelectionGUI getGUI(PaloozaPlayer player) {
		return new KitSelectionGUI(player);
	}
}
