package com.minigamepalooza.src.loot;

import com.minigamepalooza.src.HungerGames;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LootUtility {
	public static final List<List<Loot>> lootTables = new ArrayList<List<Loot>>();
	public static final List<Loot> weaponLoot = new ArrayList<Loot>();
	public static final List<Loot> foodLoot = new ArrayList<Loot>();
	public static final List<Loot> armorLoot = new ArrayList<Loot>();
	public static final List<Loot> feastLoot = new ArrayList<Loot>();
	
	public static List<Loot> getRandomLootTable() {
		return lootTables.get(HungerGames.getRandom().nextInt(lootTables.size()));
	}
	
	public static void fillChest(Block block, List<Loot> lootTable) {
		Inventory inventory = ((Chest) block.getState()).getBlockInventory();
		inventory.clear();
		
		int inventorySlots = HungerGames.getRandom().nextInt(3) + 5;
		while(inventorySlots > 0) {
			int slot = getRandomInventorySlot(inventory);
			inventory.setItem(slot, getRandomItemFromLootTable(lootTable).getRandomItem());
			
			inventorySlots--;
		}
	}
	
	private static int getRandomInventorySlot(Inventory inventory) {
		Random random = HungerGames.getRandom();
		int slot = random.nextInt(inventory.getSize());
		while(inventory.getItem(slot) != null) {
			slot = random.nextInt(inventory.getSize());
		}
		return slot;
	}
	
	private static Loot getRandomItemFromLootTable(List<Loot> lootTable) {
		double totalWeight = 0;
		for(Loot loot : lootTable) {
			totalWeight += loot.getWeight();
		}
		
		double threshold = Math.random() * totalWeight;
		double currentWeight = 0;
		
		for(Loot loot : lootTable) {
			currentWeight += loot.getWeight();
			if(currentWeight >= threshold) {
				return loot;
			}
		}
		return null;
	}
	
	static {
		weaponLoot.add(new Loot(new ItemStack(Material.STONE_SWORD), 1, 1, 18));
		weaponLoot.add(new Loot(new ItemStack(Material.IRON_SWORD), 1, 1, 10));
		weaponLoot.add(new Loot(new ItemStack(Material.BOW), 1, 1, 12));
		weaponLoot.add(new Loot(new ItemStack(Material.ARROW), 4, 13, 8));
		weaponLoot.add(new Loot(new ItemStack(Material.WOOD_SWORD), 1, 1, 23));
		weaponLoot.add(new Loot(new ItemStack(Material.STONE_AXE), 1, 1, 17));
		weaponLoot.add(new Loot(new ItemStack(Material.GOLD_SWORD), 1, 1, 20));
		weaponLoot.add(new Loot(new ItemStack(Material.WOOD_AXE), 1, 1, 25));
		
		foodLoot.add(new Loot(new ItemStack(Material.APPLE), 1, 4, 16));
		foodLoot.add(new Loot(new ItemStack(Material.BREAD), 3, 7, 20));
		foodLoot.add(new Loot(new ItemStack(Material.RAW_BEEF), 2, 5, 25));
		foodLoot.add(new Loot(new ItemStack(Material.COOKED_CHICKEN), 1, 3, 11));
		foodLoot.add(new Loot(new ItemStack(Material.COOKED_FISH), 3, 5, 17));
		
		armorLoot.add(new Loot(new ItemStack(Material.LEATHER_CHESTPLATE), 1, 1, 30));
		armorLoot.add(new Loot(new ItemStack(Material.LEATHER_HELMET), 1, 1, 40));
		armorLoot.add(new Loot(new ItemStack(Material.LEATHER_LEGGINGS), 1, 1, 35));
		armorLoot.add(new Loot(new ItemStack(Material.LEATHER_BOOTS), 1, 1, 40));
		armorLoot.add(new Loot(new ItemStack(Material.GOLD_CHESTPLATE), 1, 1, 22));
		armorLoot.add(new Loot(new ItemStack(Material.GOLD_HELMET), 1, 1, 28));
		armorLoot.add(new Loot(new ItemStack(Material.GOLD_LEGGINGS), 1, 1, 26));
		armorLoot.add(new Loot(new ItemStack(Material.GOLD_BOOTS), 1, 1, 28));
		armorLoot.add(new Loot(new ItemStack(Material.CHAINMAIL_CHESTPLATE), 1, 1, 15));
		armorLoot.add(new Loot(new ItemStack(Material.CHAINMAIL_HELMET), 1, 1, 18));
		armorLoot.add(new Loot(new ItemStack(Material.CHAINMAIL_LEGGINGS), 1, 1, 17));
		armorLoot.add(new Loot(new ItemStack(Material.CHAINMAIL_BOOTS), 1, 1, 18));
		armorLoot.add(new Loot(new ItemStack(Material.IRON_CHESTPLATE), 1, 1, 5));
		armorLoot.add(new Loot(new ItemStack(Material.IRON_HELMET), 1, 1, 12));
		armorLoot.add(new Loot(new ItemStack(Material.IRON_LEGGINGS), 1, 1, 9));
		armorLoot.add(new Loot(new ItemStack(Material.IRON_BOOTS), 1, 1, 12));
		
		feastLoot.add(new Loot(new ItemStack(Material.DIAMOND_CHESTPLATE), 1, 1, 15));
		feastLoot.add(new Loot(new ItemStack(Material.IRON_CHESTPLATE), 1, 1, 50));
		feastLoot.add(new Loot(new ItemStack(Material.DIAMOND_SWORD), 1, 1, 15));
		feastLoot.add(new Loot(new ItemStack(Material.IRON_SWORD), 1, 1, 40));
		feastLoot.add(new Loot(new ItemStack(Material.DIAMOND_LEGGINGS), 1, 1, 20));
		feastLoot.add(new Loot(new ItemStack(Material.DIAMOND_AXE), 1, 1, 20));
		
		lootTables.add(weaponLoot);
		lootTables.add(foodLoot);
		lootTables.add(armorLoot);
	}
}
