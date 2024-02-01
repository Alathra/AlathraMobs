package me.ShermansWorld.alathramobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.ShermansWorld.alathramobs.util.BiomeUtil;
import me.ShermansWorld.alathramobs.util.MobsUtil;
import me.ShermansWorld.alathramobs.util.TownyUtil;
import me.ShermansWorld.alathramobs.util.Util;

public class Timers {

	private Random rand = new Random();

	private BukkitTask fireGiantTimer;
	private ArrayList<Player> validFireGiantSpawnpointPlayers = new ArrayList<Player>();

	private BukkitTask iceGiantTimer;
	private ArrayList<Player> validIceGiantSpawnpointPlayers = new ArrayList<Player>();

	private BukkitTask strongGiantTimer;
	private ArrayList<Player> validStrongGiantSpawnpointPlayers = new ArrayList<Player>();

	public void startFireGiantTimer() {
		fireGiantTimer = new BukkitRunnable() {
			public void run() {
				validFireGiantSpawnpointPlayers.clear();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (BiomeUtil.getFireGiantBiomes().contains(BiomeUtil.getBiomeName(p))
								&& !TownyUtil.isLocationInTown(p.getLocation())) {
							if (!Util.isLocationUnderground(p.getLocation())) {
								validFireGiantSpawnpointPlayers.add(p);
							}
						}
					}
				}

				if (validFireGiantSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validFireGiantSpawnpointPlayers.size());
				MobsUtil.spawnLandMobNearPlayer(10, validFireGiantSpawnpointPlayers.get(randIdx), "Fire_Giant_Spawner");

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.fireGiantTimerInterval * 20);

	}

	public void stopFireGiantTimer() {
		fireGiantTimer.cancel();
	}
	
	public void startIceGiantTimer() {
		iceGiantTimer = new BukkitRunnable() {
			public void run() {
				validIceGiantSpawnpointPlayers.clear();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (BiomeUtil.getIceGiantBiomes().contains(BiomeUtil.getBiomeName(p))
								&& !TownyUtil.isLocationInTown(p.getLocation())) {
							if (!Util.isLocationUnderground(p.getLocation())) {
								validIceGiantSpawnpointPlayers.add(p);
							}
						}
					}
				}

				if (validIceGiantSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validIceGiantSpawnpointPlayers.size());
				MobsUtil.spawnLandMobNearPlayer(10, validIceGiantSpawnpointPlayers.get(randIdx), "Ice_Giant_Spawner");

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.iceGiantTimerInterval * 20);

	}

	public void stopIceGiantTimer() {
		iceGiantTimer.cancel();
	}
	
	public void startStrongGiantTimer() {
		strongGiantTimer = new BukkitRunnable() {
			public void run() {
				validStrongGiantSpawnpointPlayers.clear();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (BiomeUtil.getStrongGiantBiomes().contains(BiomeUtil.getBiomeName(p))
								&& !TownyUtil.isLocationInTown(p.getLocation())) {
							if (!Util.isLocationUnderground(p.getLocation())) {
								validStrongGiantSpawnpointPlayers.add(p);
							}
						}
					}
				}

				if (validStrongGiantSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validStrongGiantSpawnpointPlayers.size());
				MobsUtil.spawnLandMobNearPlayer(10, validStrongGiantSpawnpointPlayers.get(randIdx), "Strong_Giant_Spawner");

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.strongGiantTimerInterval * 20);

	}

	public void stopStrongGiantTimer() {
		strongGiantTimer.cancel();
	}

}
