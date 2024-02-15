package me.ShermansWorld.alathramobs;

import me.ShermansWorld.alathramobs.util.MobsUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemsListener implements Listener {
	/**
	 * A list of players who've summoned shawn
	 */
	ArrayList<UUID> shawnSummoners = new ArrayList<>();


	/**
	 * Handles shawn summoning, along with ensuring the associated structure is properly built
	 * @param e the player interaction event
	 */
	@EventHandler
	public void bossSummoningItemUse(PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.HAND) {
			if(e.getClickedBlock()==null) return;
			if(e.getClickedBlock().getLocation().getWorld()==null) return;
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CRYING_OBSIDIAN
				&& e.getClickedBlock().getLocation().getWorld().getEnvironment() == World.Environment.NETHER) {
				//Structure 2D top-down representation
				//
				// G
				//GCG
				// G
				//
				// G = Gold Block, C = Crying Obsidian
				// Also makes there be atleast 2 air above the center block

				double clickedBlockX = e.getClickedBlock().getX();
				double clickedBlockY = e.getClickedBlock().getY();
				double clickedBlockZ = e.getClickedBlock().getZ();


				HashMap<Location, Material> locationMaterialPairs = new HashMap<>();
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX+1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX-1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ+1), Material.GOLD_BLOCK);
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ-1), Material.GOLD_BLOCK);
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+1, clickedBlockZ), Material.AIR);
				locationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+2, clickedBlockZ), Material.AIR);



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
						)) {
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
						|| (item.getItemMeta().getCustomModelData() == 14802 && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER)
						//Covers Paper with model data 14802, for the new textured item
					) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
