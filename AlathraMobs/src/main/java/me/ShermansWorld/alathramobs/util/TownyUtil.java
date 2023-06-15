package me.ShermansWorld.alathramobs.util;

import org.bukkit.Location;

import com.palmergames.bukkit.towny.object.WorldCoord;

public class TownyUtil {
	public static boolean isLocationInTown(Location location) {
		if (WorldCoord.parseWorldCoord(location).isWilderness()) {
			return false;
		}
		return true;
	}
}
