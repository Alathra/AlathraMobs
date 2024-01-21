package me.ShermansWorld.alathramobs;

import io.lumine.mythic.bukkit.MythicBukkit;
import me.ShermansWorld.alathramobs.util.MobsUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemsListener implements Listener {
	ArrayList<Player> shawnSummoners = new ArrayList<>();


	/**
	 * Handles shawn summoning
	 * @param e
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

				double clickedBlockX = e.getClickedBlock().getX();
				double clickedBlockY = e.getClickedBlock().getY();
				double clickedBlockZ = e.getClickedBlock().getZ();


				HashMap<Location, Material> LocationMaterialPairs = new HashMap<>();
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX+1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX-1, clickedBlockY, clickedBlockZ), Material.GOLD_BLOCK);
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ+1), Material.GOLD_BLOCK);
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY, clickedBlockZ-1), Material.GOLD_BLOCK);
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+1, clickedBlockZ), Material.AIR);
				LocationMaterialPairs.put(new Location(e.getClickedBlock().getWorld(), clickedBlockX, clickedBlockY+2, clickedBlockZ), Material.AIR);



				if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL) { // holding wool
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
					if(item.getItemMeta()==null){
						return;
					}
					if (item.getItemMeta().hasCustomModelData()) {
						if (item.getItemMeta().getCustomModelData() == 301) {
							if(shawnSummoners.contains(e.getPlayer()) && e.getPlayer().getGameMode() != GameMode.CREATIVE){
								e.getPlayer().sendMessage("You have already summoned them today...");
								e.setCancelled(true);
								return;
							}

							for(Map.Entry<Location, Material> locationMaterialEntry: LocationMaterialPairs.entrySet()){
								if(locationMaterialEntry.getKey().getBlock().getType() != locationMaterialEntry.getValue()){
									e.setCancelled(true);
									return;
								}
							}
							// Removing the item
							if (item.getAmount() > 1) {
								item.setAmount(item.getAmount() - 1);
								e.getPlayer().getInventory().setItemInMainHand(item);
							} else {
								e.getPlayer().getInventory().remove(item);
							}
							// Actually doing the summoning

							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
								Sound.ENTITY_SHEEP_HURT, 5F, 1F);
							e.getClickedBlock().getWorld().strikeLightningEffect(e.getClickedBlock().getLocation());
							e.getClickedBlock().getWorld().createExplosion(e.getClickedBlock().getLocation(), 5);

							Location shawnSummonLocation = e.getClickedBlock().getLocation();
							shawnSummonLocation.setY(shawnSummonLocation.getBlockY()+1);
							MobsUtil.spawnMob("Nether_Sheep_Red", shawnSummonLocation);
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(),
								Sound.ENTITY_SHEEP_HURT, 5F, 5F);

							shawnSummoners.add(e.getPlayer());
						}

					}
				}
			} else if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WHITE_WOOL) {
				 // holding wool
				ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
				if(item.getItemMeta()==null){
					return;
				}
				if (item.getItemMeta().hasCustomModelData()) { // tried to place the specific item without the appropriate structure
					if (item.getItemMeta().getCustomModelData() == 301) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
