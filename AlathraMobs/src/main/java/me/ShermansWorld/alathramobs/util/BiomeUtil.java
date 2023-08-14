package me.ShermansWorld.alathramobs.util;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.ipecter.rtu.biomelib.RTUBiomeLib;

import me.ShermansWorld.alathramobs.Config;

public class BiomeUtil {
	
	private static ArrayList<String> biomes = new ArrayList<String>();
	private static ArrayList<String> hammerheadSharkTemperateBiomes = new ArrayList<String>();
	private static ArrayList<String> hammerheadSharkTropicalBiomes = new ArrayList<String>();
	private static ArrayList<String> sharkTemperateBiomes = new ArrayList<String>();
	private static ArrayList<String> sharkTropicalBiomes = new ArrayList<String>();
	private static ArrayList<String> elephantJungleBiomes = new ArrayList<String>();
	private static ArrayList<String> elephantSavannaBiomes = new ArrayList<String>();
	private static ArrayList<String> deerBiomes = new ArrayList<String>();
	private static ArrayList<String> fireGiantBiomes = new ArrayList<String>();
	private static ArrayList<String> iceGiantBiomes = new ArrayList<String>();
	private static ArrayList<String> strongGiantBiomes = new ArrayList<String>();
	private static ArrayList<String> legendaryCodBiomes = new ArrayList<String>();
	
	public static void init() {
		// all biomes
		biomes.add(Config.packExtension + "active_volcano_base");
		biomes.add(Config.packExtension + "active_volcano_base_edge");
		biomes.add(Config.packExtension + "active_volcano_pit");
		biomes.add(Config.packExtension + "active_volcano_pit_edge");
		biomes.add(Config.packExtension + "badlands_buttes");
		biomes.add(Config.packExtension + "bamboo_jungle_hills");
		biomes.add(Config.packExtension + "bamboo_jungle_mountains");
		biomes.add(Config.packExtension + "bamboo_ponds");
		biomes.add(Config.packExtension + "birch_forest_hills");
		biomes.add(Config.packExtension + "caldera_volcano_base");
		biomes.add(Config.packExtension + "caldera_volcano_base_edge");
		biomes.add(Config.packExtension + "caldera_volcano_pit");
		biomes.add(Config.packExtension + "caldera_volcano_pit_edge");
		biomes.add(Config.packExtension + "cerros_de_mavecure");
		biomes.add(Config.packExtension + "chaparral");
		biomes.add(Config.packExtension + "cold_deep_ocean");
		biomes.add(Config.packExtension + "cold_ocean");
		biomes.add(Config.packExtension + "coral_ocean");
		biomes.add(Config.packExtension + "dark_forest");
		biomes.add(Config.packExtension + "deep_ocean");
		biomes.add(Config.packExtension + "deep_ocean_trench");
		biomes.add(Config.packExtension + "desert");
		biomes.add(Config.packExtension + "dry_rocky_bumpy_mountains");
		biomes.add(Config.packExtension + "dry_temperate_mountains");
		biomes.add(Config.packExtension + "dry_temperate_white_mountains");
		biomes.add(Config.packExtension + "dry_temperate_white_mountains_river");
		biomes.add(Config.packExtension + "dry_wild_highlands");
		biomes.add(Config.packExtension + "eucalyptus_forest");
		biomes.add(Config.packExtension + "evergreen_forest");
		biomes.add(Config.packExtension + "flowering_forest");
		biomes.add(Config.packExtension + "flowering_forest_hills");
		biomes.add(Config.packExtension + "forest");
		biomes.add(Config.packExtension + "forest_hills");
		biomes.add(Config.packExtension + "frozen_beach");
		biomes.add(Config.packExtension + "frozen_deep_ocean");
		biomes.add(Config.packExtension + "frozen_marsh");
		biomes.add(Config.packExtension + "frozen_wetlands");
		biomes.add(Config.packExtension + "highlands");
		biomes.add(Config.packExtension + "ice_spikes");
		biomes.add(Config.packExtension + "iceberg_ocean");
		biomes.add(Config.packExtension + "jungle_hills");
		biomes.add(Config.packExtension + "jungle_mountains");
		biomes.add(Config.packExtension + "large_monsoon_mountains");
		biomes.add(Config.packExtension + "mangrove_swamp");
		biomes.add(Config.packExtension + "moorland");
		biomes.add(Config.packExtension + "mountains");
		biomes.add(Config.packExtension + "mountains_river");
		biomes.add(Config.packExtension + "mushroom_hills");
		biomes.add(Config.packExtension + "oak_savanna");
		biomes.add(Config.packExtension + "ocean");
		biomes.add(Config.packExtension + "palm_beach");
		biomes.add(Config.packExtension + "palm_forest");
		biomes.add(Config.packExtension + "plains");
		biomes.add(Config.packExtension + "prairie");
		biomes.add(Config.packExtension + "rainforest_hills");
		biomes.add(Config.packExtension + "redwood_forest_hills");
		biomes.add(Config.packExtension + "river");
		biomes.add(Config.packExtension + "rocky_bumpy_mountains");
		biomes.add(Config.packExtension + "rocky_sea_caves");
		biomes.add(Config.packExtension + "sakura_mountains");
		biomes.add(Config.packExtension + "salt_flats");
		biomes.add(Config.packExtension + "shrublands");
		biomes.add(Config.packExtension + "snowy_meadow");
		biomes.add(Config.packExtension + "snowy_plains");
		biomes.add(Config.packExtension + "snowy_terraced_mountains");
		biomes.add(Config.packExtension + "snowy_terraced_mountains_river");
		biomes.add(Config.packExtension + "steppe");
		biomes.add(Config.packExtension + "subtropical_ocean");
		biomes.add(Config.packExtension + "sunflower_plains");
		biomes.add(Config.packExtension + "swamp");
		biomes.add(Config.packExtension + "taiga");
		biomes.add(Config.packExtension + "taiga_hills");
		biomes.add(Config.packExtension + "temperate_mountains");
		biomes.add(Config.packExtension + "temperate_mountains_river");
		biomes.add(Config.packExtension + "tropical_deep_ocean");
		biomes.add(Config.packExtension + "tropical_ocean");
		biomes.add(Config.packExtension + "tundra_hills");
		biomes.add(Config.packExtension + "wild_bumpy_mountains");
		biomes.add(Config.packExtension + "wild_highlands");
		biomes.add(Config.packExtension + "wooded_buttes");
		biomes.add(Config.packExtension + "xeric_low_hills");
		biomes.add(Config.packExtension + "xeric_mountains ");
		
		// hammerhead shark biomes
		hammerheadSharkTemperateBiomes.add(Config.packExtension + "deep_ocean");
		hammerheadSharkTemperateBiomes.add(Config.packExtension + "deep_ocean_trench");
		hammerheadSharkTemperateBiomes.add(Config.packExtension + "ocean");
		hammerheadSharkTemperateBiomes.add(Config.packExtension + "subtropical_ocean");
		
		// tropical hammerhead shark biomes
		hammerheadSharkTropicalBiomes.add(Config.packExtension + "coral_ocean");
		hammerheadSharkTropicalBiomes.add(Config.packExtension + "tropical_ocean");
		hammerheadSharkTropicalBiomes.add(Config.packExtension + "tropical_deep_ocean");
		
		// shark biomes
		sharkTemperateBiomes.add(Config.packExtension + "cold_deep_ocean");
		sharkTemperateBiomes.add(Config.packExtension + "cold_ocean");
		sharkTemperateBiomes.add(Config.packExtension + "deep_ocean");
		sharkTemperateBiomes.add(Config.packExtension + "deep_ocean_trench");
		sharkTemperateBiomes.add(Config.packExtension + "ocean");
		sharkTemperateBiomes.add(Config.packExtension + "subtropical_ocean");
		
		// tropical shark biomes
		sharkTropicalBiomes.add(Config.packExtension + "coral_ocean");
		sharkTropicalBiomes.add(Config.packExtension + "tropical_ocean");
		sharkTropicalBiomes.add(Config.packExtension + "tropical_deep_ocean");
		
		// jungle elephant biomes
		elephantJungleBiomes.add(Config.packExtension + "bamboo_jungle_hills");
		elephantJungleBiomes.add(Config.packExtension + "bamboo_jungle_mountains");
		elephantJungleBiomes.add(Config.packExtension + "bamboo_ponds");
		elephantJungleBiomes.add(Config.packExtension + "jungle_hills");
		elephantJungleBiomes.add(Config.packExtension + "jungle_mountains");
		elephantJungleBiomes.add(Config.packExtension + "large_monsoon_mountains");
		elephantJungleBiomes.add(Config.packExtension + "wild_bumpy_mountains");
		elephantJungleBiomes.add(Config.packExtension + "wild_highlands");
		
		// savannah elephant biomes
		elephantSavannaBiomes.add(Config.packExtension + "oak_savanna");
		elephantSavannaBiomes.add(Config.packExtension + "plains");
		elephantSavannaBiomes.add(Config.packExtension + "moorland");
		elephantSavannaBiomes.add(Config.packExtension + "eucalyptus_forest");
		elephantSavannaBiomes.add(Config.packExtension + "savanna");
		elephantSavannaBiomes.add(Config.packExtension + "savanna_low_hills");
		elephantSavannaBiomes.add(Config.packExtension + "low_chaparral");
		elephantSavannaBiomes.add(Config.packExtension + "chaparral");
		
		// deer biomes
		deerBiomes.add(Config.packExtension + "birch_forest_hills");
		deerBiomes.add(Config.packExtension + "evergreen_forest");
		deerBiomes.add(Config.packExtension + "flowering_forest");
		deerBiomes.add(Config.packExtension + "flowering_forest_hills");
		deerBiomes.add(Config.packExtension + "forest");
		deerBiomes.add(Config.packExtension + "forest_hills");
		deerBiomes.add(Config.packExtension + "redwood_forest_hills");
		deerBiomes.add(Config.packExtension + "sakura_mountains");
		deerBiomes.add(Config.packExtension + "taiga");
		deerBiomes.add(Config.packExtension + "taiga_hills");
		
		// fire giant biomes
		fireGiantBiomes.add(Config.packExtension + "bamboo_jungle_hills");
		fireGiantBiomes.add(Config.packExtension + "bamboo_jungle_mountains");
		fireGiantBiomes.add(Config.packExtension + "chaparral");
		fireGiantBiomes.add(Config.packExtension + "desert");
		fireGiantBiomes.add(Config.packExtension + "dry_rocky_bumpy_mountains");
		fireGiantBiomes.add(Config.packExtension + "dry_temperate_mountains");
		fireGiantBiomes.add(Config.packExtension + "dry_wild_highlands");
		fireGiantBiomes.add(Config.packExtension + "eucalyptus_forest");
		fireGiantBiomes.add(Config.packExtension + "jungle_hills");
		fireGiantBiomes.add(Config.packExtension + "jungle_mountains");
		fireGiantBiomes.add(Config.packExtension + "oak_savanna");
		fireGiantBiomes.add(Config.packExtension + "palm_beach");
		fireGiantBiomes.add(Config.packExtension + "palm_forest");
		fireGiantBiomes.add(Config.packExtension + "rainforest_hills");
		fireGiantBiomes.add(Config.packExtension + "swamp");
		fireGiantBiomes.add(Config.packExtension + "wild_bumpy_mountains");
		fireGiantBiomes.add(Config.packExtension + "xeric_low_hills");
		fireGiantBiomes.add(Config.packExtension + "xeric_mountains");
		
		// ice giant biomes
		iceGiantBiomes.add(Config.packExtension + "dry_temperate_white_mountains");
		iceGiantBiomes.add(Config.packExtension + "evergreen_forest");
		iceGiantBiomes.add(Config.packExtension + "frozen_marsh");
		iceGiantBiomes.add(Config.packExtension + "ice_spikes");
		iceGiantBiomes.add(Config.packExtension + "moorland");
		iceGiantBiomes.add(Config.packExtension + "mountains");
		iceGiantBiomes.add(Config.packExtension + "mushroom_hills");
		iceGiantBiomes.add(Config.packExtension + "redwood_forest_hills");
		iceGiantBiomes.add(Config.packExtension + "salt_flats");
		iceGiantBiomes.add(Config.packExtension + "snowy_meadow");
		iceGiantBiomes.add(Config.packExtension + "snowy_plains");
		iceGiantBiomes.add(Config.packExtension + "snowy_terraced_mountains");
		iceGiantBiomes.add(Config.packExtension + "steppe");
		iceGiantBiomes.add(Config.packExtension + "taiga");
		iceGiantBiomes.add(Config.packExtension + "taiga_hills");
		iceGiantBiomes.add(Config.packExtension + "tundra_hills");
		
		// strong giant biomes
		strongGiantBiomes.add(Config.packExtension + "birch_forest_hills");
		strongGiantBiomes.add(Config.packExtension + "dark_forest");
		strongGiantBiomes.add(Config.packExtension + "flowering_forest");
		strongGiantBiomes.add(Config.packExtension + "flowering_forest_hills");
		strongGiantBiomes.add(Config.packExtension + "forest");
		strongGiantBiomes.add(Config.packExtension + "forest_hills");
		strongGiantBiomes.add(Config.packExtension + "highlands");
		strongGiantBiomes.add(Config.packExtension + "large_monsoon_mountains");
		strongGiantBiomes.add(Config.packExtension + "plains");
		strongGiantBiomes.add(Config.packExtension + "prairie");
		strongGiantBiomes.add(Config.packExtension + "sakura_mountains");
		strongGiantBiomes.add(Config.packExtension + "shrublands");
		strongGiantBiomes.add(Config.packExtension + "sunflower_plains");
		strongGiantBiomes.add(Config.packExtension + "temperate_mountains");
		
		// legendary cod biomes
		legendaryCodBiomes.add(Config.packExtension + "cold_deep_ocean");
		legendaryCodBiomes.add(Config.packExtension + "cold_ocean");
		legendaryCodBiomes.add(Config.packExtension + "coral_ocean");
		legendaryCodBiomes.add(Config.packExtension + "deep_ocean");
		legendaryCodBiomes.add(Config.packExtension + "dry_temperate_white_mountains_riverfrozen_beach");
		legendaryCodBiomes.add(Config.packExtension + "frozen_deep_ocean");
		legendaryCodBiomes.add(Config.packExtension + "mountains_river");
		legendaryCodBiomes.add(Config.packExtension + "ocean");
		legendaryCodBiomes.add(Config.packExtension + "river");
		legendaryCodBiomes.add(Config.packExtension + "subtropical_ocean");
		legendaryCodBiomes.add(Config.packExtension + "tropical_deep_ocean");
		legendaryCodBiomes.add(Config.packExtension + "tropical_ocean");
		
	}
	
	public static String getBiomeName(Location location) {
		return RTUBiomeLib.getInterface().getBiomeName(location);
	}
	
	public static String getBiomeName(Player player) {
		return RTUBiomeLib.getInterface().getBiomeName(player.getLocation());
	}
	
	public static ArrayList<String> getBiomes() {
		return biomes;
	}
	
	public static ArrayList<String> getHammerheadSharkTemperateBiomes() {
		return hammerheadSharkTemperateBiomes;
	}
	
	public static ArrayList<String> getHammerheadSharkTropicalBiomes() {
		return hammerheadSharkTropicalBiomes;
	}
	
	public static ArrayList<String> getSharkTemperateBiomes() {
		return sharkTemperateBiomes;
	}
	
	public static ArrayList<String> getSharkTropicalBiomes() {
		return sharkTropicalBiomes;
	}
	
	public static ArrayList<String> getElephantJungleBiomes() {
		return elephantJungleBiomes;
	}
	
	public static ArrayList<String> getElephantSavannahBiomes() {
		return elephantSavannaBiomes;
	}
	
	public static ArrayList<String> getDeerBiomes() {
		return deerBiomes;
	}
	
	public static ArrayList<String> getFireGiantBiomes() {
		return fireGiantBiomes;
	}
	
	public static ArrayList<String> getIceGiantBiomes() {
		return iceGiantBiomes;
	}
	
	public static ArrayList<String> getStrongGiantBiomes() {
		return strongGiantBiomes;
	}
	
	public static ArrayList<String> getLegendaryCodBiomes() {
		return legendaryCodBiomes;
	}
}
