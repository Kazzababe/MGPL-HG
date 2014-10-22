package com.minigamepalooza.src.timers;

import com.minigamepalooza.src.HungerGames;
import java.util.Collections;
import java.util.Comparator;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.util.CraftMagicNumbers;
import org.bukkit.scheduler.BukkitRunnable;

public class GetBlocksToFall extends BukkitRunnable {

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        Location c1 = HungerGames.WORLD_SPAWN.getDimensions()[0].getLocation(HungerGames.WORLD),
                 c2 = HungerGames.WORLD_SPAWN.getDimensions()[1].getLocation(HungerGames.WORLD);
        World world = ((CraftWorld) c1.getWorld()).getHandle();
        System.out.println(c1 + ":" + c2);
        for(int x = c1.getBlockX(); x < c2.getBlockX(); x++) {
            for(int z = c1.getBlockZ(); z < c2.getBlockZ(); z++) {
                for(int i = 0; i < 256; i++) {
                    int type = CraftMagicNumbers.getId(world.getType(x, i, z));
                    if(!c1.getWorld().getBlockAt(x, i, z).getChunk().isLoaded()) {
                    	c1.getWorld().getBlockAt(x, i, z).getChunk().load(true);
                    }
                    if(type != 0) {
                        HungerGames.BLOCKS.add(c1.getWorld().getBlockAt(x, i, z));
                    }
                }
            }
        }

        final Location spawn = HungerGames.WORLD_SPAWN.getFeastLocation().getLocation(HungerGames.WORLD);
        Collections.sort(HungerGames.BLOCKS, new Comparator<Block>() {
            @Override
            public int compare(Block b1, Block b2) {
                double d1 = this.getDistance(b1), d2 = this.getDistance(b2);
                if (d1 > d2) {
                    return -1;
                } else if (d2 > d1) {
                    return 1;
                } else {
                    if (b1.getY() > b2.getY()) {
                        return 1;
                    } else if (b2.getY() > b1.getY()) {
                        return -1;
                    }
                }
                return 0;
            }

            private double getDistance(Block b1) {
                Location l = b1.getLocation();
                return ((l.getX() - spawn.getX()) * (l.getX() - spawn.getX())) + ((l.getZ() - spawn.getZ()) * (l.getZ() - spawn.getZ()));
            }
        });
        HungerGames.GAME_READY = true;
        System.out.println("[Hunger Games] The game is ready to play!");
    }
}
