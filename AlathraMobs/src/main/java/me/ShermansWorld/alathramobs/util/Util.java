package me.ShermansWorld.alathramobs.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Util {

	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static String chatLabel() {
		return color("&e[&cAlathraMobs&e]&r ");
	}

	public static String chatLabelConsole() {
		return "[AlathraMobs]";
	}

	public static boolean isLocationInMap(Location location) {
		
		// If the mob spawns outside the world border
		if (location.getBlockX() > 6450 || location.getBlockX() < -6450 || location.getBlockZ() > 6450
				|| location.getBlockZ() < -6450) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isLocationUnderground(Location location) {
		if (location.getBlockY() < 60) {
			return true;
		} else {
			return false;
		}
	}
}
