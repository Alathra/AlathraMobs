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
		
		packExtension = config.getString("PackExtension");
		
		
	}
}
