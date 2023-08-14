package me.ShermansWorld.alathramobs.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.mobs.MobExecutor;

public class MobsUtil {

	static MobExecutor mobManager;

	public static void init() {
		mobManager = MythicBukkit.inst().getMobManager();
	}

	public static ActiveMob spawnMob(String mobName, Location loc) {
		return mobManager.spawnMob(mobName, loc);
	}

	public static ActiveMob spawnMob(String mobName, Location loc, double level) {
		return mobManager.spawnMob(mobName, loc, level);
	}

	public static MobExecutor getMobManager() {
		return mobManager;
	}

	public static ActiveMob spawnWaterMobNearPlayer(int distanceFactor, Player player, String mob) {

		// GET RANDOM DISTANCE FROM PLAYER

		// *20+10: random number between 10 and 30
		double radius = distanceFactor * Math.random() + 10;
		double angle = Math.toRadians(360 * Math.random());

		// x and z as distances
		int x = (int) (Math.sin(angle) * radius);
		int z = (int) (Math.cos(angle) * radius);

		// ADD DISTANCE TO PLAYER LOCATION TO PRODUCE RAND LOCATION
		x = player.getLocation().getBlockX() + x;
		z = player.getLocation().getBlockZ() + z;
		World world = player.getWorld();
		// CALCULATE Y FOR LOCATION

		// start with player's y loation
		int y = player.getLocation().getBlockY();
		// get the material of the player's location and 5 blocks above it
		Material locationBlockTypes[] = new Material[5];
		for (int i = 0; i < locationBlockTypes.length; i++) {
			locationBlockTypes[i] = player.getWorld().getBlockAt(x, y + i, z).getType();
		}
		// only spawn the mob if all blocks are air (ideally prevents spawning in
		// underground cave/room)
		while (locationBlockTypes[0] != Material.AIR && locationBlockTypes[1] != Material.AIR
				&& locationBlockTypes[2] != Material.AIR && locationBlockTypes[3] != Material.AIR
				&& locationBlockTypes[4] != Material.AIR) {
			for (int i = 0; i < locationBlockTypes.length; i++) {
				locationBlockTypes[i] = player.getWorld().getBlockAt(x, y + i, z).getType();
			}
			y++;
		}
		locationBlockTypes[0] = player.getWorld().getBlockAt(x, y, z).getType();
		while (locationBlockTypes[0] == Material.AIR) {
			y--;
			locationBlockTypes[0] = player.getWorld().getBlockAt(x, y, z).getType();
		}
		y++;

		// if mob cannot spawn in water, try again with smaller radius
		Location spawnLocation = new Location(world, x, y, z);

		if (player.getWorld().getBlockAt(spawnLocation).getType() != Material.WATER
				|| TownyUtil.isLocationInTown(spawnLocation) || (!Util.isLocationInMap(spawnLocation))) {
			distanceFactor -= 5;
			if (distanceFactor < 0) {
				// failed to spawn mob in water after multiple attempts, give up.
				return null;
			}

			// Retry spawning with smaller radius
			spawnWaterMobNearPlayer(distanceFactor, player, mob);
			return null;
		}

		// spawn mob
		switch (mob) {
		case "Legendary_Cod":
			player.sendMessage(Util.color("&a&lA Legendary Cod has spawned nearby"));
			break;
		case "MSO_GiantSquid":
			player.sendMessage(Util.color("&a&lA Giant Squid has spawned nearby"));
			break;
		default:
			player.sendMessage(Util.color("&4&lA shark has spawned nearby"));
			break;
		}
		return spawnMob(mob, spawnLocation);

	}

	public static ActiveMob spawnLandMobNearPlayer(int distanceFactor, Player player, String mob) {

		// GET RANDOM DISTANCE FROM PLAYER

		// *20+10: random number between 10 and 30
		double radius = distanceFactor * Math.random() + 10;
		double angle = Math.toRadians(360 * Math.random());

		// x and z as distances
		int x = (int) (Math.sin(angle) * radius);
		int z = (int) (Math.cos(angle) * radius);

		// ADD DISTANCE TO PLAYER LOCATION TO PRODUCE RAND LOCATION
		x = player.getLocation().getBlockX() + x;
		z = player.getLocation().getBlockZ() + z;
		World world = player.getWorld();
		// CALCULATE Y FOR LOCATION

		// get sea level location
		int y = 62;

		// if mob cannot spawn in water, try again with smaller radius
		Location spawnLocation = new Location(world, x, y, z);

		if (TownyUtil.isLocationInTown(spawnLocation) || (!Util.isLocationInMap(spawnLocation))) {
			distanceFactor -= 5;
			if (distanceFactor < 0) {
				// failed to spawn mob in water after multiple attempts, give up.
				return null;
			}

			// Retry spawning with smaller radius
			spawnLandMobNearPlayer(distanceFactor, player, mob);
			return null;
		}

		while (spawnLocation.getBlock().getType() != Material.AIR) {
			spawnLocation.setY((double) spawnLocation.getBlockY() + 1);
		}

		// spawn mob
		switch (mob) {
		case "elephant":
			player.sendMessage(Util.color("&e&lAn elephant has spawned nearby"));
			break;
		case "asianelephant":
			player.sendMessage(Util.color("&e&lAn elephant has spawned nearby"));
			break;
		case "deer_male":
			player.sendMessage(Util.color("&e&lA deer has spawned nearby"));
			break;
		case "deer_female":
			player.sendMessage(Util.color("&e&lA deer has spawned nearby"));
			break;
		case "Fire_Giant":
			player.sendMessage(Util.color("&4&lA FIRE GIANT has spawned nearby! Oh no..."));
			break;
		case "Ice_Giant":
			player.sendMessage(Util.color("&4&lAn ICE GIANT has spawned nearby! Oh no..."));
			break;
		case "Strong_Giant":
			player.sendMessage(Util.color("&4&lA STRONG GIANT has spawned nearby! Oh no..."));
			break;
		default:
			player.sendMessage(Util.color("&e&lA special mob has spawned nearby"));
			break;
		}
		return spawnMob(mob, spawnLocation);

	}

}
