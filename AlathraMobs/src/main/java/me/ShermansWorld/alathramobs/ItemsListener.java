package me.ShermansWorld.alathramobs;

import me.ShermansWorld.alathramobs.util.MobsUtil;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemsListener implements Listener {
	/**
	 * A list of players who've summoned shawn
	 */
	ArrayList<UUID> shawnSummoners = new ArrayList<>();
	ArrayList<UUID> blazeKingSummoners = new ArrayList<>();
	HashMap<Location, Integer> blazeKingActiveAltarLocations = new HashMap<>();


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
					|| e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER ) { // holding white wool or paper
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
					if(item.getItemMeta()==null){
						return;
					}
					// Looks for custom model data with a value of 301
					if (item.getItemMeta().hasCustomModelData() &&
						((item.getItemMeta().getCustomModelData() == 301 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL)
							//Covers White wool with model data 301, for compatibility with the original items
						|| (item.getItemMeta().getCustomModelData() == 14802 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER)
							//Covers Paper with model data 14802, for the new textured item
						)
						&& e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NETHER
						&& (e.getClickedBlock().getType() == Material.CRYING_OBSIDIAN)
					) {
						//Structure 2D top-down representation
						//
						// G
						//GCG
						// G
						//
						// G = Gold Block, C = Crying Obsidian
						// Also makes there be atleast 2 air above the center block

						HashMap<Location, Material> locationMaterialPairs = new HashMap<>();
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX+1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX-1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ+1), Material.GOLD_BLOCK);
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ-1), Material.GOLD_BLOCK);
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+1, clickedBlockZ), Material.AIR);
						locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+2, clickedBlockZ), Material.AIR);

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
					else if (
							item.getItemMeta().hasCustomModelData() &&
							item.getItemMeta().getCustomModelData() == 14803 &&
							item.getType() == Material.PAPER &&
							e.getClickedBlock().getType() == Material.TINTED_GLASS &&
							e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NETHER
					){
						if (blazeKingSummoners.contains(e.getPlayer().getUniqueId()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
							e.getPlayer().sendMessage("You have already sacrificed to the altar today...");
							e.setCancelled(true);
							return;
						}

						if (!blazeKingActiveAltarLocations.containsKey(e.getClickedBlock().getLocation())){
							blazeKingActiveAltarLocations.put(e.getClickedBlock().getLocation(), 0);
						}

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


	/**
	 * Checks a 3d area of locations to ensure that it matches a data defined structure
	 *
	 * @param structureMap A hashmap of y-level offsets and 2d char arrays
	 * @param materialHashMap
	 * @param centerLoc
	 * @param arrayCenterBlockX
	 * @param arrayCenterBlockZ
	 * @return
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

