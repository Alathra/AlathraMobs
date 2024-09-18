package me.ShermansWorld.alathramobs;

import net.playavalon.mythicdungeons.MythicDungeons;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.ShermansWorld.alathramobs.commands.AlathraMobsCommands;
import me.ShermansWorld.alathramobs.commands.ShowBiomeCommands;
import me.ShermansWorld.alathramobs.util.BiomeUtil;
import me.ShermansWorld.alathramobs.util.MobsUtil;

public class AlathraMobs extends JavaPlugin {

	public static AlathraMobs instance = null;
	
	public static MythicDungeons dungeons = null;

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
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new ItemsListener(), (Plugin) this);
		new AlathraMobsCommands(this);
		if (pluginManager.getPlugin("MythicDungeons") != null) {
			MythicDungeons dungeons = (MythicDungeons) pluginManager.getPlugin("MythicDungeons");
		}
		MobsUtil.init();
		BiomeUtil.init();
		timer.startFireGiantTimer();
		timer.startIceGiantTimer();
		timer.startStrongGiantTimer();
	}

}
