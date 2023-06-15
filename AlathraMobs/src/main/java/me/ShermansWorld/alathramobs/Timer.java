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

public class Timer {

	private Random rand = new Random();

	private BukkitTask sharkTimer;
	private ArrayList<Player> validSharkSpawnpointPlayers = new ArrayList<Player>();
	private ArrayList<Boolean> validSharkSpawnpointIsTropical = new ArrayList<Boolean>();

	private BukkitTask elephantTimer;
	private ArrayList<Player> validElephantSpawnpointPlayers = new ArrayList<Player>();
	private ArrayList<Boolean> validElephantSpawnpointIsJungle = new ArrayList<Boolean>();
	
	private BukkitTask deerTimer;
	private ArrayList<Player> validDeerSpawnpointPlayers = new ArrayList<Player>();

	public void startSharkTimer() {
		sharkTimer = new BukkitRunnable() {
			public void run() {
				validSharkSpawnpointPlayers.clear();
				double chance = Math.random();
				boolean hammerheadSharkSpawn = false;
				boolean sharkSpawn = false;
				if (chance >= 0.5) {
					hammerheadSharkSpawn = true;
				} else {
					sharkSpawn = true;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (hammerheadSharkSpawn) {
							if (BiomeUtil.getHammerheadSharkTemperateBiomes().contains(BiomeUtil.getBiomeName(p))) {
								if (!Util.isLocationUnderground(p.getLocation())
										&& !TownyUtil.isLocationInTown(p.getLocation())) {
									validSharkSpawnpointPlayers.add(p);
									validSharkSpawnpointIsTropical.add(false);
								}
							} else if (BiomeUtil.getHammerheadSharkTropicalBiomes().contains(BiomeUtil.getBiomeName(p))
									&& !TownyUtil.isLocationInTown(p.getLocation())) {
								if (!Util.isLocationUnderground(p.getLocation())) {
									validSharkSpawnpointPlayers.add(p);
									validSharkSpawnpointIsTropical.add(true);
								}
							}
						} else if (sharkSpawn) {
							if (BiomeUtil.getSharkTemperateBiomes().contains(BiomeUtil.getBiomeName(p))
									&& !TownyUtil.isLocationInTown(p.getLocation())) {
								if (!Util.isLocationUnderground(p.getLocation())) {
									validSharkSpawnpointPlayers.add(p);
									validSharkSpawnpointIsTropical.add(false);
								}
							} else if (BiomeUtil.getSharkTropicalBiomes().contains(BiomeUtil.getBiomeName(p))
									&& !TownyUtil.isLocationInTown(p.getLocation())) {
								if (!Util.isLocationUnderground(p.getLocation())) {
									validSharkSpawnpointPlayers.add(p);
									validSharkSpawnpointIsTropical.add(true);
								}
							}
						}
					}
				}

				if (validSharkSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validSharkSpawnpointPlayers.size());

				if (hammerheadSharkSpawn) {
					if (validSharkSpawnpointIsTropical.get(randIdx)) {
						MobsUtil.spawnWaterMobNearPlayer(10, validSharkSpawnpointPlayers.get(randIdx),
								"MSO_Shark");
					} else {
						MobsUtil.spawnWaterMobNearPlayer(10, validSharkSpawnpointPlayers.get(randIdx),
								"MSO_Shark_2");
					}
				} else if (sharkSpawn) {
					if (validSharkSpawnpointIsTropical.get(randIdx)) {
						MobsUtil.spawnWaterMobNearPlayer(10, validSharkSpawnpointPlayers.get(randIdx),
								"MSO_HammerheadShark_2");
					} else {
						MobsUtil.spawnWaterMobNearPlayer(10, validSharkSpawnpointPlayers.get(randIdx),
								"MSO_HammerheadShark");
					}
				}

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.sharkTimerInterval * 20);
	}

	public void stopSharkTimer() {
		sharkTimer.cancel();
	}

	public void startElephantTimer() {
		elephantTimer = new BukkitRunnable() {
			public void run() {
				validElephantSpawnpointPlayers.clear();
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (BiomeUtil.getElephantJungleBiomes().contains(BiomeUtil.getBiomeName(p))
								&& !TownyUtil.isLocationInTown(p.getLocation())) {
							if (!Util.isLocationUnderground(p.getLocation())) {
								validElephantSpawnpointPlayers.add(p);
								validElephantSpawnpointIsJungle.add(true);
							}
						} else if (BiomeUtil.getElephantSavannahBiomes().contains(BiomeUtil.getBiomeName(p))) {
							if (!Util.isLocationUnderground(p.getLocation())
									&& !TownyUtil.isLocationInTown(p.getLocation())) {
								validElephantSpawnpointPlayers.add(p);
								validElephantSpawnpointIsJungle.add(false);
							}
						}
					}
				}

				if (validElephantSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validElephantSpawnpointPlayers.size());

				if (validElephantSpawnpointIsJungle.get(randIdx)) {
					MobsUtil.spawnLandMobNearPlayer(10, validElephantSpawnpointPlayers.get(randIdx),
							"asianelephant");
				} else {
					MobsUtil.spawnLandMobNearPlayer(10, validElephantSpawnpointPlayers.get(randIdx), "elephant");
				}

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.elephantTimerInterval * 20);

	}

	public void stopElephantTimer() {
		elephantTimer.cancel();
	}
	
	public void startDeerTimer() {
		deerTimer = new BukkitRunnable() {
			public void run() {
				validDeerSpawnpointPlayers.clear();
				double chance = Math.random();
				boolean maleDeerSpawn = true;
				if (chance >= 0.5) {
					maleDeerSpawn = true;
				} else {
					maleDeerSpawn = false;
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getWorld().getName().contentEquals("World-o")) {
						if (BiomeUtil.getDeerBiomes().contains(BiomeUtil.getBiomeName(p))
								&& !TownyUtil.isLocationInTown(p.getLocation())) {
							if (!Util.isLocationUnderground(p.getLocation())) {
								validDeerSpawnpointPlayers.add(p);
							}
						}
					}
				}

				if (validDeerSpawnpointPlayers.isEmpty()) {
					return;
				}

				int randIdx = rand.nextInt(validDeerSpawnpointPlayers.size());
				if (maleDeerSpawn) {
					MobsUtil.spawnLandMobNearPlayer(10, validDeerSpawnpointPlayers.get(randIdx),
							"deer_male");
				} else {
					MobsUtil.spawnLandMobNearPlayer(10, validDeerSpawnpointPlayers.get(randIdx), "deer_female");
				}

			}
		}.runTaskTimer(AlathraMobs.getInstance(), 0L, Config.deerTimerInterval * 20);

	}
	
	public void stopDeerTimer() {
		deerTimer.cancel();
	}

}
