package com.minigamepalooza.src.timers;

import com.minigamepalooza.src.HungerGames;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class MakeAllDaBootysDrop extends BukkitRunnable {
    //private int radius = Integer.MAX_VALUE;
    private List<Block> blocks = new ArrayList<Block>();
    //private Location spawn;

    public MakeAllDaBootysDrop() {
        //this.spawn = HungerGames.WORLD_SPAWN.getFeastLocation().getLocation(HungerGames.WORLD);
        //this.radius = getDistance(HungerGames.BLOCKS.get(0));
        this.blocks = new ArrayList<Block>(HungerGames.BLOCKS);
    }

    @Override
    public void run() {
        int maxIterations = 125;
        int iterations = 0;
        
        while(!this.blocks.isEmpty() && iterations++ < maxIterations) {
        	makeDaBootyDrop(this.blocks.remove(0));
        }
        
        if(this.blocks.isEmpty()) {
        	this.cancel();
        }
    }

    @SuppressWarnings("deprecation")
    private void makeDaBootyDrop(Block block) {
    	//System.out.println(block);
        //block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
        block.setType(Material.AIR);
    }

    /*private int getDistance(Block b1) {
        Location l = b1.getLocation();
        return (int) Math.sqrt((l.getX() - this.spawn.getX()) * (l.getX() - this.spawn.getX()) + (l.getZ() - this.spawn.getZ()) * (l.getZ() - this.spawn.getZ()));
    }*/
}
