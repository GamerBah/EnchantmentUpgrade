package com.gamerbah.commands;
/* Created by GamerBah on 2/24/2016 */

import com.gamerbah.EnchantmentUpgrade;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AltarCommand implements CommandExecutor {

	private EnchantmentUpgrade plugin;

	public AltarCommand(EnchantmentUpgrade plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;

		if (args.length > 0) {
			player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);
			player.sendMessage(EnchantmentUpgrade.chatStarter + ChatColor.RED
			                   + "For future reference, you don't need any arguments in this command! Just use /altar");
		}
		player.sendMessage(
				EnchantmentUpgrade.chatStarter + ChatColor.GREEN + "Click the link below to see how to make an altar!");
		player.sendMessage(
				EnchantmentUpgrade.chatStarter + ChatColor.YELLOW + "" + ChatColor.BOLD + "LINK: " + ChatColor.GOLD
				+ "http://goo.gl/FbQB3S");

		return false;
	}


}
