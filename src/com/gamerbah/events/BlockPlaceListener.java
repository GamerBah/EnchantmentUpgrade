package com.gamerbah.events;
/* Created by GamerBah on 1/19/2018 */

import com.gamerbah.EnchantmentUpgrade;
import com.gamerbah.utils.altar.Altar;
import com.gamerbah.utils.altar.AltarManager;
import javafx.scene.paint.ColorBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.bukkit.Material.ENCHANTING_TABLE;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			Block  block  = event.getBlockPlaced();

			if (block.getType().equals(ENCHANTING_TABLE)) {
				if (AltarManager.isGlitchedAltar(block.getLocation())) {
					player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
					if (player.isOp()) {
						player.sendMessage(EnchantmentUpgrade.chatStarter + ChatColor.RED
						                   + "Somehow there's already an altar here! You might wanna send in a bug report!");
						player.sendMessage(EnchantmentUpgrade.chatStarter + ChatColor.YELLOW
						                   + "http://dev.bukkit.org/bukkit-plugins/enchantment-upgrade/create-ticket/");
					} else {
						player.sendMessage(EnchantmentUpgrade.chatStarter + ChatColor.RED
						                   + "Somehow there's already an altar here! A server admin has been notified"
						                   + " of the issue.");
						// TODO: Notify Admins
					}
					event.setCancelled(true);
					return;
				}

				if (AltarManager.isAvailableLocation(block.getLocation())) {
					// TODO: Permissions
					Altar altar = new Altar(block.getLocation().add(0, 2, 0), player.getUniqueId());
					altar.getLocation().getWorld().strikeLightningEffect(altar.getLocation());
					altar.getBlockLocations().forEach(location -> {
						for (int y = 0; y < 3; y++) {

						}
					}); AltarManager.getAltars().add(altar);
					Bukkit.getServer().getScheduler().runTaskLater(EnchantmentUpgrade.getInstance(), () -> {
						player.sendMessage(
								new ColorBuilder(ChatColor.GRAY).italic() + "A strange voice whispers to you: ");
						player.sendMessage(new ColorBuilder(ChatColor.GRAY).italic()
						                   + "\"The altar has been completed. Use it wisely.\"");
						player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, 1);
					}, 10L);
				} else {
					event.setCancelled(true);
				}
			}
		}
	}

}
