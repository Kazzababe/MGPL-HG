package com.minigamepalooza.src.specializations;

import org.bukkit.inventory.ItemStack;

public class KitBrute extends Kit {

	public KitBrute(String name, int cost, ItemStack item) {
		super(name, cost, item);
		
		this.setArmourModifier(1.25);
		this.setHealth(38);
	}
}
