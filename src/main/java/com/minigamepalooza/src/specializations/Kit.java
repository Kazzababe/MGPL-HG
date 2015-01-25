package com.minigamepalooza.src.specializations;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.base.utils.ItemBuilder;

public class Kit {
	public static final List<Kit> KITS = new ArrayList<Kit>();
	public static final Kit WARRIOR = new KitWarrior("Warrior", 1000, new ItemBuilder(Material.IRON_SWORD).setTitle("Warrior").addLore("Deals 10% more damage with swords and axes").build());
	public static final Kit BRUTE = new KitBrute("Brute", 1000, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setTitle("Brute").addLore("Takes 10% less damage while wearing armour").build());
	public static final Kit HUNTER = new KitHunter("Hunter", 1000, new ItemBuilder(Material.BOW).setTitle("Hunter").addLore("Arrows fired deal 10% more damage").build());
	public static final Kit RUNNER = new KitRunner("Runner", 1000, new ItemBuilder(Material.LEATHER_BOOTS).setTitle("Runner").addLore("Move 10% faster").build());
	
	private double swordDamageModifier = 1.0;
	private double axeDamageModifier = 1.0;
	private double bowDamageModifier = 1.0;
	private double armourModifier = 1.0;
	private double speedModifier = 1.0;
	private double health = 20.0;
	
	private String name;
	private ItemStack item;
	
	private int cost;
	
	public Kit(String name, int cost, ItemStack item) {
		this.name = name;
		this.cost = cost;
		this.item = item;
		
		KITS.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	protected void setSwordDamageModifier(double modifier) {
		this.swordDamageModifier = modifier;
	}
	
	protected void setAxeDamageModifier(double modifier) {
		this.axeDamageModifier = modifier;
	}
	
	protected void setBowDamageModifier(double modifier) {
		this.bowDamageModifier = modifier;
	}
	
	protected void setArmourModifier(double modifier) {
		this.armourModifier = modifier;
	}
	
	protected void setSpeedModifier(double modifier) {
		this.speedModifier = modifier;
	}
	
	protected void setHealth(double health) {
		this.health = health;
	}
	
	public double getSwordDamageModifier() {
		return this.swordDamageModifier;
	}
	
	public double getAxeDamageModifer() {
		return this.axeDamageModifier;
	}
	
	public double getBowDamageModifier() {
		return this.bowDamageModifier;
	}
	
	public double getArmourModifier() {
		return this.armourModifier;
	}
	
	public double getSpeedModifier() {
		return this.speedModifier;
	}
	
	public double getHealth() {
		return this.health;
	}
}
