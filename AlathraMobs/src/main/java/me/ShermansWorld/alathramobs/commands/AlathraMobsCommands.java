package me.ShermansWorld.alathramobs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.alathramobs.AlathraMobs;
import me.ShermansWorld.alathramobs.Config;
import me.ShermansWorld.alathramobs.util.BiomeUtil;
import me.ShermansWorld.alathramobs.util.Util;


public class AlathraMobsCommands implements CommandExecutor {

	private boolean isConsole;

	public AlathraMobsCommands(AlathraMobs plugin) {
		plugin.getCommand("alathramobs").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		if (sender instanceof Player) {
			isConsole = false;
			player = (Player) sender;
			if (!player.hasPermission("AlathraMobs.admin")) {
				player.sendMessage(Util.chatLabel() + Util.color("&cYou do not have permission to use this command"));
				return false; 
			}
		} else {
			isConsole = true;
		}
		
		if (args.length == 0) {
			return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				AlathraMobs.getInstance().reloadConfig();
				AlathraMobs.getInstance().saveDefaultConfig();
				AlathraMobs.timer.stopSharkTimer();
				AlathraMobs.timer.stopElephantTimer();
				AlathraMobs.timer.stopDeerTimer();
				Config.initConfigVals();
				BiomeUtil.init();
				AlathraMobs.timer.startSharkTimer();
				AlathraMobs.timer.startElephantTimer();
				AlathraMobs.timer.startDeerTimer();
				if (isConsole) {
					sender.sendMessage("config reloaded");
				} else {
					sender.sendMessage("config reloaded");
				}
				return true;
			}
		}
		return false;
	}

}
