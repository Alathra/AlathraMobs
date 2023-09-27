package me.ShermansWorld.alathramobs;

import org.bukkit.plugin.java.JavaPlugin;

import me.ShermansWorld.alathramobs.commands.AlathraMobsCommands;
import me.ShermansWorld.alathramobs.commands.ShowBiomeCommands;
import me.ShermansWorld.alathramobs.util.BiomeUtil;
import me.ShermansWorld.alathramobs.util.MobsUtil;

public class AlathraMobs extends JavaPlugin {

	public static AlathraMobs instance = null;

	public static AlathraMobs getInstance() {
		return instance;
	}
	
	public static Timers timer = new Timers();
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Config.initConfigVals();
		new ShowBiomeCommands(this);
		new AlathraMobsCommands(this);
		MobsUtil.init();
		BiomeUtil.init();
		timer.startSharkTimer();
		timer.startElephantTimer();
		timer.startDeerTimer();
		timer.startFireGiantTimer();
		timer.startIceGiantTimer();
		timer.startStrongGiantTimer();
		timer.startLegedaryCodTimer();
		timer.startGiantSquidTimer();
	}

}
