package com.minigamepalooza.src.specializations;

import org.bukkit.inventory.ItemStack;

public class KitWarrior extends Kit {

	public KitWarrior(String name, int cost, ItemStack item) {
		super(name, cost, item);
		
		this.setSwordDamageModifier(1.15);
		this.setAxeDamageModifier(1.15);
	}
}
