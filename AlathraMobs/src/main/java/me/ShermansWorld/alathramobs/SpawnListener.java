package me.ShermansWorld.alathramobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent;
import me.ShermansWorld.alathramobs.util.TownyUtil;


public class SpawnListener implements Listener {
	@EventHandler
	public static void checkForMobInTown(MythicMobSpawnEvent e) {
		if(TownyUtil.isLocationInTown(e.getLocation())) {
			e.setCancelled(true);
		}
	}
}
