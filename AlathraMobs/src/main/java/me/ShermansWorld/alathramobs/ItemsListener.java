package me.ShermansWorld.alathramobs;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.ShermansWorld.alathramobs.util.MobsUtil;
import me.ShermansWorld.alathramobs.util.TownyUtil;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;

public class ItemsListener implements Listener {
	/**
	 * A list of players who've summoned shawn
	 */
	ArrayList<UUID> shawnSummoners = new ArrayList<>();
	ArrayList<UUID> blazeLordSummoners = new ArrayList<>();
	ArrayList<UUID> regionalBossSummoners = new ArrayList<>();
	HashMap<Location, Integer> blazeLordActiveAltarLocations = new HashMap<>();
	final int blazeLordSummonerAmount = 3;


	/**
	 * Handles shawn summoning, along with ensuring the associated structure is properly built
	 * @param e the player interaction event
	 */
	@EventHandler
	public void bossSummoningItemUse(PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND) {
			if(e.getClickedBlock()==null) return;
			if(e.getClickedBlock().getLocation().getWorld()==null) return;
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK
				) {

				double clickedBlockX = e.getClickedBlock().getX();
				double clickedBlockY = e.getClickedBlock().getY();
				double clickedBlockZ = e.getClickedBlock().getZ();


				if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL
					|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER
					|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COPPER_INGOT) { // holding white wool, paper, or copper ingot
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
					if(item.getItemMeta()==null){
						return;
					}
					// Looks for custom model data with a value of 301
					if (isShawnItemAndBlock(e, item)
					) {
						summonShawn(e, clickedBlockX, clickedBlockY, clickedBlockZ, item);


					}
					else if (
						isBlazeLordItemAndBlock(e, item)
					) {
						summonBlazeLord(e, clickedBlockX, clickedBlockY, clickedBlockZ);
					}
					else if (
						item.getItemMeta().hasCustomModelData() &&
							item.getItemMeta().getCustomModelData()==3 &&
							item.getType() == Material.COPPER_INGOT &&
							e.getClickedBlock().getType() == Material.GLOWSTONE &&
							e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NORMAL &&
							e.getClickedBlock().getLocation().getWorld().getName().equals("World-o")
					) {
						if(regionalBossSummoners.contains(e.getPlayer().getUniqueId()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
							e.getPlayer().sendMessage("You have already summoned them today...");
							e.setCancelled(true);
							return;
						}

						try {
							if (!TownyUtil.isLocationInTown(e.getClickedBlock().getLocation()) || (TownyAPI.getInstance().getTownBlock(e.getClickedBlock().getLocation()) != null && TownyAPI.getInstance().getTownBlock(e.getClickedBlock().getLocation()).getTown().hasResident(e.getPlayer()))) {
								e.getPlayer().sendMessage("You need to be in your town to summon a regional boss.");
								return;
							}
						} catch (NotRegisteredException |  NullPointerException error) {
							e.getPlayer().sendMessage("You need to be in your town to summon a regional boss.");
							return;
						}

						//  B
						// BGB
						//  B
						//
						//

						char[][] layer0 = {
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', 'B', '*', '*', '*', '*'},
							{'*', '*', '*', 'B', 'G', 'B', '*', '*', '*'},
							{'*', '*', '*', '*', 'B', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'}
						};

						char[][] layer1 = {
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'},
							{'*', '*', '*', '*', '*', '*', '*', '*', '*'}
						};

						char[][] layer2 = {
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'}
						};

						char[][] layer3 = {
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'},
							{'A', 'A', 'A', 'A', 'A' ,'A', 'A', 'A', 'A'}
						};

						HashMap<Integer, char[][]> regionalBossStructure = new HashMap<>();
						for(int m = 10; m > 3; m--){
							regionalBossStructure.put(m, layer3);
						}
						regionalBossStructure.put(3, layer3);
						regionalBossStructure.put(2, layer2);
						regionalBossStructure.put(1, layer1);
						regionalBossStructure.put(0, layer0);



						HashMap<Character, Material> characterMaterialHashMap = new HashMap<>();
						characterMaterialHashMap.put('B', Material.BONE_BLOCK);
						characterMaterialHashMap.put('G', Material.GLOWSTONE);
						characterMaterialHashMap.put('A', Material.AIR);

						Location centerLocation = new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ);

						if(!structureCheck(regionalBossStructure, characterMaterialHashMap, centerLocation, 4, 4)){
							Bukkit.getLogger().log(Level.INFO, e.getPlayer().getDisplayName() + " tried to summon a regional boss, but the structure was incorrect.");
							e.getPlayer().sendMessage("The structure sits still.");
							return;
						}



						Location regionalBossSummonLocation = e.getClickedBlock().getLocation();
						regionalBossSummonLocation.setY(regionalBossSummonLocation.getBlockY()+1.0);

						RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
						RegionManager regions = container.get(WorldGuard.getInstance().getPlatform().getMatcher().getWorldByName(e.getClickedBlock().getWorld().getName()));
						if (regions == null) {
							Bukkit.getLogger().log(Level.SEVERE, "RegionManager is null in world, please ensure this is correct behavior in world " + e.getClickedBlock().getWorld().getName());

							return;
						}

						BlockVector3 regionalBlockVector = BlockVector3.at(regionalBossSummonLocation.getX(), regionalBossSummonLocation.getY(), regionalBossSummonLocation.getZ());

						ApplicableRegionSet set = regions.getApplicableRegions(regionalBlockVector);

						List<String> regionNames = new ArrayList<>();

						for (com.sk89q.worldguard.protection.regions.ProtectedRegion region : set) {
							regionNames.add(region.getId());
						}

						Bukkit.getLogger().log(Level.INFO, e.getPlayer().getDisplayName() + " summoned a regional boss in region " + regionNames.toString() + "at location " + regionalBossSummonLocation.toString());

						if(regionNames.contains("1-1")) {
							MobsUtil.spawnMob("rb_zombie_parent", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_zombie_parent for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("1-2")) {
							MobsUtil.spawnMob("rb_zombie_vomit", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_zombie_vomit for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("1-3")) {
							MobsUtil.spawnMob("rb_zombie_starving", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_zombie_starving for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("1-4")) {
							MobsUtil.spawnMob("rb_zombie_elusive", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_zombie_elusive for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("2-1")) {
							MobsUtil.spawnMob("rb_spider_swapping", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_spider_swapping for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("2-2")) {
							MobsUtil.spawnMob("rb_spider_swarm", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_spider_swarm for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("2-3")) {
							MobsUtil.spawnMob("rb_spider_invisible", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_spider_invisible for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("3-1")) {
							MobsUtil.spawnMob("rb_creeper_hunter", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_creeper_hunter for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("3-2")) {
							MobsUtil.spawnMob("rb_creeper_ticking_time", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_creeper_ticking_time for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("4-1")) {
							MobsUtil.spawnMob("rb_slime_dragon", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_slime_dragon for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("4-2")) {
							MobsUtil.spawnMob("rb_slime_stacked", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_slime_stacked for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("4-3")) {
							MobsUtil.spawnMob("rb_slime_vampire", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_slime_vampire for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("5-1")) {
							MobsUtil.spawnMob("rb_skeleton_bat_jocky", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_skeleton_bat_jocky for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("5-2")) {
							MobsUtil.spawnMob("rb_skeleton_ravager", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_skeleton_ravager for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("5-3")) {
							MobsUtil.spawnMob("rb_withered_skeleton", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_withered_skeleton for " + e.getPlayer().getDisplayName());
						} else if (regionNames.contains("5-4")) {
							MobsUtil.spawnMob("rb_decayed_skeleton", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_decayed_skeleton for " + e.getPlayer().getDisplayName());
						} else {
							MobsUtil.spawnMob("rb_default", regionalBossSummonLocation);
							Bukkit.getLogger().log(Level.INFO, "Spawned rb_default for " + e.getPlayer().getDisplayName());
						}

						if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
							e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
							regionalBossSummoners.add(e.getPlayer().getUniqueId());
						}


					}
				}
			} else if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL
				|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER ) { // cancels doing things with the wool in hand
				 // holding wool
				ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
				if(item.getItemMeta()==null){
					return;
				}
				if (item.getItemMeta().hasCustomModelData()) { // tried to place the specific item without the appropriate structure

					if ((item.getItemMeta().getCustomModelData() == 301 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL)
						//Covers White wool with model data 301, for compatibility with the original items
						|| ((item.getItemMeta().getCustomModelData() == 14802 || item.getItemMeta().getCustomModelData() == 14803) && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER)
						//Covers Paper with model data 14802, for the new textured item
					) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	private static boolean isShawnItemAndBlock(PlayerInteractEvent e, ItemStack item) {
		return item.getItemMeta().hasCustomModelData() &&
			((item.getItemMeta().getCustomModelData() == 301 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL)
				//Covers White wool with model data 301, for compatibility with the original items
				|| (item.getItemMeta().getCustomModelData() == 14802 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER)
				//Covers Paper with model data 14802, for the new textured item
			)
			&& e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NETHER
			&& (e.getClickedBlock().getType() == Material.CRYING_OBSIDIAN);
	}

	private static boolean isBlazeLordItemAndBlock(PlayerInteractEvent e, ItemStack item) {
		return item.getItemMeta().hasCustomModelData() &&
			item.getItemMeta().getCustomModelData() == 14803 &&
			item.getType() == Material.PAPER &&
			e.getClickedBlock().getType() == Material.TINTED_GLASS &&
			e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NETHER;
	}

	private void summonShawn(PlayerInteractEvent e, double clickedBlockX, double clickedBlockY, double clickedBlockZ, ItemStack item) {
		//Structure 2D top-down representation
		//
		// G
		//GCG
		// G
		//
		// G = Gold Block, C = Crying Obsidian
		// Also makes there be atleast 2 air above the center block

		HashMap<Location, Material> locationMaterialPairs = new HashMap<>();
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX +1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX -1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ +1), Material.GOLD_BLOCK);
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ -1), Material.GOLD_BLOCK);
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY +1, clickedBlockZ), Material.AIR);
		locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY +2, clickedBlockZ), Material.AIR);

		if(shawnSummoners.contains(e.getPlayer().getUniqueId()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.getPlayer().sendMessage("You have already summoned them today...");
			e.setCancelled(true);
			return;
		}
		// stops placing the wool without the structure
		for(Map.Entry<Location, Material> locationMaterialEntry: locationMaterialPairs.entrySet()){
			if(locationMaterialEntry.getKey().getBlock().getType() != locationMaterialEntry.getValue()){
				e.setCancelled(true);
				return;
			}
		}
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			// Removing the item
			if (item.getAmount() > 1) {
				item.setAmount(item.getAmount() - 1);
				e.getPlayer().getInventory().setItemInMainHand(item);
			} else {
				e.getPlayer().getInventory().remove(item);
			}
		}
		// Actually doing the summoning

		e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
			Sound.ENTITY_SHEEP_HURT, 5F, 1F);
		e.getClickedBlock().getWorld().strikeLightningEffect(e.getClickedBlock().getLocation());
		e.getClickedBlock().getWorld().createExplosion(e.getClickedBlock().getLocation(), 5);

		Location shawnSummonLocation = e.getClickedBlock().getLocation();
		shawnSummonLocation.setY(shawnSummonLocation.getBlockY()+1.0); // summons Shawn 1 block above the center of the structure
		MobsUtil.spawnMob("Nether_Sheep_Red", shawnSummonLocation);
		e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
			Sound.ENTITY_SHEEP_HURT, 5F, 5F);

		shawnSummoners.add(e.getPlayer().getUniqueId()); //stops player from summoning multiple times a restart
	}

	private void summonBlazeLord(PlayerInteractEvent e, double clickedBlockX, double clickedBlockY, double clickedBlockZ) {
		if (blazeLordSummoners.contains(e.getPlayer().getUniqueId()) && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.getPlayer().sendMessage("You have already sacrificed to the altar today...");
			e.setCancelled(true);
			return;
		}

		if (e.getClickedBlock().getLocation().getBlockY() < 40){
			e.getPlayer().sendMessage("Seek Higher ground.");
			return;
		}

		if (e.getClickedBlock().getLocation().getBlockY() > 130) {
			e.getPlayer().sendMessage("Seek Lower ground.");
			return;
		}

		if (!blazeLordActiveAltarLocations.containsKey(e.getClickedBlock().getLocation())){
			blazeLordActiveAltarLocations.put(e.getClickedBlock().getLocation(), 0);
		}

		char[][] layer4 = {
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'},
			{'A','A','A','A','A','A','A','A','A'}
		};

		char[][] layer3 = {
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'}
		};

		char[][] layer2 = {
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'}
		};

		char[][] layer1 = {
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'}
		};

		char[][] layer0 = {
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','L','A','L','A','A','A'},
				{'A','A','A','A','T','A','A','A','A'},
				{'A','A','A','L','A','L','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','A','A','A','A','A','A','A','A'}
		};

		char[][] layerN1 = {
				{'A','A','A','F','R','F','A','A','A'},
				{'A','F','A','A','A','A','A','F','A'},
				{'A','A','A','A','A','A','A','A','A'},
				{'F','A','A','E','D','E','A','A','F'},
				{'R','A','A','D','D','D','A','A','R'},
				{'F','A','A','E','D','E','A','A','F'},
				{'A','A','A','A','A','A','A','A','A'},
				{'A','F','A','A','A','A','A','F','A'},
				{'A','A','A','F','R','F','A','A','A'}
		};

		HashMap<Character, Material> characterMaterialHashMap = new HashMap<>();
		characterMaterialHashMap.put('A', Material.AIR);
		characterMaterialHashMap.put('R', Material.END_ROD);
		characterMaterialHashMap.put('L', Material.LIGHTNING_ROD);
		characterMaterialHashMap.put('T', Material.TINTED_GLASS);
		characterMaterialHashMap.put('E', Material.EMERALD_BLOCK);
		characterMaterialHashMap.put('D', Material.DIAMOND_BLOCK);
		characterMaterialHashMap.put('F', Material.SOUL_CAMPFIRE);

		HashMap<Integer, char[][]> blazeKingStructure = new HashMap<>();
		for(int m = 25; m > 4; m--){
			blazeKingStructure.put(m, layer4);
		}
		blazeKingStructure.put(4, layer4);
		blazeKingStructure.put(3, layer3);
		blazeKingStructure.put(2, layer2);
		blazeKingStructure.put(1, layer1);
		blazeKingStructure.put(0, layer0);
		blazeKingStructure.put(-1, layerN1);

		Location centerLocation = new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ);

		if(!structureCheck(blazeKingStructure, characterMaterialHashMap, centerLocation, 4, 4)){
			e.getPlayer().sendMessage("The structure sits still.");
			return;
		}

		blazeLordActiveAltarLocations.replace(e.getClickedBlock().getLocation(), blazeLordActiveAltarLocations.get(e.getClickedBlock().getLocation())+1);
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}

		if(blazeLordActiveAltarLocations.get(e.getClickedBlock().getLocation()) <= blazeLordSummonerAmount - 1){ // Structure is valid, but not enough power
			e.getPlayer().sendMessage("The structure's power grows... (%s/%d)".formatted(blazeLordActiveAltarLocations.get(e.getClickedBlock().getLocation()), blazeLordSummonerAmount));
			blazeLordSummoners.add(e.getPlayer().getUniqueId());
			return;
		}
		else if(blazeLordActiveAltarLocations.get(e.getClickedBlock().getLocation()) >= blazeLordSummonerAmount - 1){ // Structure is valid, enough players have sacrificed
			blazeLordActiveAltarLocations.remove(e.getClickedBlock().getLocation());
			blazeLordSummoners.add(e.getPlayer().getUniqueId());

			e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
				Sound.ENTITY_SHEEP_HURT, 5F, 1F);
			e.getClickedBlock().getWorld().strikeLightningEffect(e.getClickedBlock().getLocation());

			Bukkit.getScheduler().scheduleSyncDelayedTask(AlathraMobs.getInstance(), () -> e.getClickedBlock().getWorld().createExplosion(e.getClickedBlock().getLocation(), 6), 50L);

			Location blazeLordSummonLocation = e.getClickedBlock().getLocation();
			blazeLordSummonLocation.setY(blazeLordSummonLocation.getBlockY()+1.0); // summons The Blaze Lord 1 block above the center of the structure
			MobsUtil.spawnMob("Blaze_Lord", blazeLordSummonLocation);

			e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
				Sound.ENTITY_SHEEP_HURT, 5F, 5F);
		}
	}


	/**
	 * Checks a 3d area of locations to ensure that it matches a data defined structure
	 *
	 * @param structureMap A hashmap of y-level offsets and 2d char arrays used to define the structure
	 * @param materialHashMap Hashmap that maps characters to materials
	 * @param centerLoc the block at the center of the structure, the one used to activate the ritual
	 * @param arrayCenterBlockX Defines the x position of the center block in the array
	 * @param arrayCenterBlockZ Defines the z position of the center block in the array
	 * @return true if the structure is correct, false otherwise
	 */
	private boolean structureCheck(HashMap<Integer, char[][]> structureMap, HashMap<Character, Material> materialHashMap,
								   Location centerLoc, int arrayCenterBlockX, int arrayCenterBlockZ){
		if(!structureMap.containsKey(0)) throw new IllegalArgumentException("Origin Layer outside of structure Map");
		int length = 0;
		int index = 0;

		for(char[][] layer: structureMap.values()){
			if (index == 0){
				length = layer[0].length;
			}
			else {
				if (layer.length != length){
					throw new IllegalArgumentException("Layer Size is not equal");
				}
				for(char[] row : layer){
					if (row.length != length){
						throw new IllegalArgumentException("Layer Row Size is not a square.");
					}
				}
			}
			index++;
		}

		for (Map.Entry<Integer, char[][]> entry: structureMap.entrySet()){
			for(int x = 0; x < length; x++){
				for (int z = 0; z < length; z++){
					Location locationToBeChecked = new Location(centerLoc.getWorld(), centerLoc.getX()+getXOffset(length, arrayCenterBlockX, x), centerLoc.getY()+entry.getKey(), centerLoc.getZ()+getZOffset(length, arrayCenterBlockZ, z));
					if (entry.getValue()[x][z] == '*') continue; // * is a wildcard, for any material
					if (locationToBeChecked.getBlock().getType() != materialHashMap.get(entry.getValue()[x][z])){ // Checks that the location material matches the material on the spot on the 2d array
						return false;
					}
				}
			}
		}
		return true;
	}

	private int getXOffset(int length, int arrayCenterBlockX, int position){
		if (position >= length || position < 0 || arrayCenterBlockX >= length || arrayCenterBlockX < 0){
			throw new IllegalArgumentException("Position or Array Center outside length");
		}
		return position-arrayCenterBlockX;
	}

	private int getZOffset(int length, int arrayCenterBlockZ, int position){
		if (position >= length || position < 0 || arrayCenterBlockZ >= length || arrayCenterBlockZ < 0){
			throw new IllegalArgumentException("Position or Array Center outside length");
		}
		return position-arrayCenterBlockZ;
	}

}

