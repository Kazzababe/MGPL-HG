package com.minigamepalooza.src.specializations;

import org.bukkit.inventory.ItemStack;

public class KitRunner extends Kit {

	public KitRunner(String name, int cost, ItemStack item) {
		super(name, cost, item);
		
		this.setSpeedModifier(1.3);
		this.setHealth(32);
	}
}
