package com.minigamepalooza.src.specializations;

import org.bukkit.inventory.ItemStack;

public class KitHunter extends Kit {

	public KitHunter(String name, int cost, ItemStack item) {
		super(name, cost, item);
		
		this.setBowDamageModifier(1.25);
		this.setHealth(36);
	}
}
