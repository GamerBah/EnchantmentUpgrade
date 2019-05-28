package com.gamerbah.utils.altar;
/* Created by GamerBah on 1/19/2018 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

public class Upgrade implements ConfigurationSerializable {

	@Getter private final Type type;
	@Getter private       int  level;


	public Upgrade(final Type type, final int level) {
		this.type = type;
		this.level = level;
	}

	public Upgrade(final Type type) {
		this(type, 1);
	}

	public static Altar deserialize(Map<String, Object> data) {
		// TODO
		return null;
	}

	public boolean decreaseLevel() {
		if (this.level != this.type.getMaxLevel() && this.level != 1) {
			this.level--;
			return true;
		}
		return false;
	}

	public boolean increaseLevel() {
		if (this.level != this.type.getMaxLevel()) {
			this.level++;
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> serialize() {
		// TODO
		return null;
	}

	@AllArgsConstructor
	@Getter
	public enum Type {

		PROTECTION(1, ChatColor.GOLD + "Protection", new String[]{ChatColor.GRAY + "Gives an altar protection", ChatColor.GRAY + "from all outsiders!"}),
		EFFICIENCY(3, ChatColor.AQUA + "Efficiency", new String[]{ChatColor.GRAY + "Lose less materials when", ChatColor.GRAY + "an altar is used!"}),
		TRUST(3, ChatColor.LIGHT_PURPLE + "Trust",
		      new String[]{ChatColor.GRAY + "Allows an altar to have a", ChatColor.GRAY + "list of trusted players", ChatColor.GRAY + "that can use it!", " ",
		                   ChatColor.GOLD + "Requires the Protection Upgrade"});

		private int      maxLevel;
		private String   name;
		private String[] lore;
	}
}
