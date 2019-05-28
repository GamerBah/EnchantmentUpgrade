package com.gamerbah.utils;
/* Created by GamerBah on 5/20/19 */

import org.bukkit.entity.Player;

import java.util.Collection;

import static com.gamerbah.EnchantmentUpgrade.chatStarter;

public class M {

	public void sendMessage(final Player player, final String message) {
		player.sendMessage(chatStarter + message);
	}

	public void sendMessage(final Collection<? extends Player> players, final String message) {
		players.forEach(player -> sendMessage(player, message));
	}

}
