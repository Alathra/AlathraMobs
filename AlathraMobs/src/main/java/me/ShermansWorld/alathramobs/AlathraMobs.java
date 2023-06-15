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
	
	public static Timer timer = new Timer();
	
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
		this.getServer().getPluginManager().registerEvents(new SpawnListener(), this);
	}

}
