package me.ShermansWorld.alathramobs;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	
	private static FileConfiguration config;
	
	// config version
	public static int configVersion = 1;
	
	// timer settings
	public static int sharkTimerInterval = 60;
	
	public static int elephantTimerInterval = 480;
	
	public static int deerTimerInterval = 480;
	
	public static int fireGiantTimerInterval = 7200;
	
	public static int iceGiantTimerInterval = 10800;
	
	public static int strongGiantTimerInterval = 7200;
	
	public static int legendaryCodTimerInterval = 300;
	
	public static int giantSquidTimerInterval = 1800;
	
	// other settings
	public static String packExtension = "terra:overworld/overworld/";
	
	
	public static void initConfigVals() {
		//init config
		config = AlathraMobs.getInstance().getConfig();
		
		//init variables
		configVersion = config.getInt("config-version");
		
		sharkTimerInterval = config.getInt("SharkTimerInterval");
		elephantTimerInterval = config.getInt("ElephantTimerInterval");
		deerTimerInterval = config.getInt("DeerTimerInterval");
		
		fireGiantTimerInterval = config.getInt("FireGiantTimerInterval");
		iceGiantTimerInterval = config.getInt("IceGiantTimerInterval");
		strongGiantTimerInterval = config.getInt("StrongGiantTimerInterval");
		
		legendaryCodTimerInterval = config.getInt("LegendaryCodTimerInterval");
		
		giantSquidTimerInterval = config.getInt("GiantSquidTimerInterval");
		
		packExtension = config.getString("PackExtension");
		
		
	}
}
