package me.ShermansWorld.alathramobs.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	/**
	 * Gets a list of players within a certain radius of a location.
	 *
	 * @param location The center location.
	 * @param radius   The radius to search within.
	 * @return A list of players within the radius.
	 */
	public static List<Player> getPlayersInRadius(Location location, double radius) {
		try {
			List<Player> playersInRadius = new ArrayList<>();
			for (Entity entity : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
				if (entity instanceof Player) {
					playersInRadius.add((Player) entity);
				}
			}
			return playersInRadius;
		} catch (NullPointerException e) {
			return Collections.emptyList();
		}
	}
}
