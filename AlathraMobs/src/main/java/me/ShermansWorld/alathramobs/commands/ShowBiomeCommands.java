package me.ShermansWorld.alathramobs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.alathramobs.AlathraMobs;
import me.ShermansWorld.alathramobs.util.BiomeUtil;

public class ShowBiomeCommands implements CommandExecutor {

	public ShowBiomeCommands(AlathraMobs plugin) {
		plugin.getCommand("showbiome").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(BiomeUtil.getBiomeName(player.getLocation()));
			return true;
		}
		return false;
	}
}
