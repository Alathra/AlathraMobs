package me.ShermansWorld.alathramobs.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.ShermansWorld.alathramobs.util.BiomeUtil;

public class CreatureSpawnListener implements Listener {
	@EventHandler
	public static void onCreatureSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.NATURAL) {
			return;
		}
		if (e.getEntity().getWorld() != Bukkit.getWorld("World-o")) {
			return;
		}
		Location loc = e.getEntity().getLocation();
		if (BiomeUtil.getCamelBiomes().contains(BiomeUtil.getBiomeName(loc))) {
			double chance = Math.random();
			// 1 in 100 chance
			if (chance > 0.99) {
				e.getEntity().getWorld().spawnEntity(loc, EntityType.CAMEL);
				e.setCancelled(true);
			}
		}

	}
}
