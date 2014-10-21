package com.minigamepalooza.src.loot;

import com.minigamepalooza.src.HungerGames;
import java.util.Random;
import org.bukkit.inventory.ItemStack;

public class Loot {
	private ItemStack item;
	
	private int min;
	private int max;
	
	private double weight;
	
	public Loot(ItemStack item, int min, int max, double weight) {
		this.item = item;
		
		this.min = min;
		this.max = max;
		
		this.weight = weight;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public ItemStack getRandomItem() {
		ItemStack item = this.item.clone();
		
		Random random = HungerGames.getRandom();
		item.setAmount(random.nextInt(this.max - this.min + 1) + this.min);
		
		return item;
	}
	
	public double getWeight() {
		return this.weight;
	}
}
