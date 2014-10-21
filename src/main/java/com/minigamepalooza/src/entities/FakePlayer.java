package com.minigamepalooza.src.entities;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R3.EntityPlayer;
import net.minecraft.server.v1_7_R3.PacketPlayOutBed;
import net.minecraft.server.v1_7_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_7_R3.PacketPlayOutRelEntityMove;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakePlayer {
	private World world;
	
	private int id;
	
	private int locX;
	private int locY;
	private int locZ;
	
	private FakePlayer(Player player, boolean keepInventory) {
		int id = -player.getEntityId();
		EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
		
		this.locX = (int) Math.floor(nmsPlayer.locX);
		this.locY = (int) Math.floor(nmsPlayer.locY);
		this.locZ = (int) Math.floor(nmsPlayer.locZ);
		
		PacketPlayOutNamedEntitySpawn spawnPacket = new PacketPlayOutNamedEntitySpawn(nmsPlayer);
		
		try {
			Field field = spawnPacket.getClass().getDeclaredField("a");
			field.setAccessible(true);
			field.set(spawnPacket, id);
			field.setAccessible(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(Player p : player.getWorld().getPlayers()) {
			nmsPlayer = ((CraftPlayer) p).getHandle();
			nmsPlayer.playerConnection.sendPacket(spawnPacket);
		}
		
		this.id = id;
		this.world = player.getWorld();
	}
	
	public int getEntityId() {
		return this.id;
	}
	
	public Location getLocation() {
		return new Location(this.world, this.locX, this.locY, this.locZ);
	}
	
	public void layDown() {
		Location location = this.getLocation();
		
		PacketPlayOutBed sleepPacket = new PacketPlayOutBed();
		PacketPlayOutRelEntityMove movePacket = new PacketPlayOutRelEntityMove(this.id, (byte) 0,  (byte) (16), (byte) 0);
		
		try {
			Field idField = sleepPacket.getClass().getDeclaredField("a");
			Field xField = sleepPacket.getClass().getDeclaredField("b");
			Field yField = sleepPacket.getClass().getDeclaredField("c");
			Field zField = sleepPacket.getClass().getDeclaredField("d");
			
			idField.setAccessible(true);
			xField.setAccessible(true);
			yField.setAccessible(true);
			zField.setAccessible(true);
			
			idField.set(sleepPacket, this.getEntityId());
			xField.set(sleepPacket, location.getBlockX());
			yField.set(sleepPacket, location.getBlockY());
			zField.set(sleepPacket, location.getBlockZ());
			
			idField.setAccessible(false);
			xField.setAccessible(false);
			yField.setAccessible(false);
			zField.setAccessible(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for(Player player : this.world.getPlayers()) {
			EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
			
			nmsPlayer.playerConnection.sendPacket(sleepPacket);
			nmsPlayer.playerConnection.sendPacket(movePacket);
		}
	}
	
	public static FakePlayer spawnPlayer(Player player, boolean keepInventory) {
		FakePlayer fakePlayer = new FakePlayer(player, keepInventory);
		return fakePlayer;
	}
}
