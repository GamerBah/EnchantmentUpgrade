package com.gamerbah.old;
/* Created by GamerBah on 2/23/2016 */

import com.gamerbah.EnchantmentUpgrade;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Files {

	private EnchantmentUpgrade plugin;

	public Files(EnchantmentUpgrade plugin) {
		this.plugin = plugin;
	}

	public void addAltar(Block block) {
		FileConfiguration config;
		File              file = new File(plugin.getDataFolder(), "altars.txt");
		if (file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
			Object object = getAltars();
			if (object == null) {
				config.set("Altars", Methods.packageLocation(block.getLocation()));
			} else {
				List<String> altars = getAltars();
				altars.add(Methods.packageLocation(block.getLocation()));
				config.set("Altars", altars);
			}
			plugin.addMetricsStat("created");
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			plugin.saveResource("altars.txt", true);
		}
	}

	public List<String> getAltars() {
		FileConfiguration config;
		File              file = new File(plugin.getDataFolder(), "altars.txt");
		if (file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
			List<String> altars = config.getStringList("Altars");
			return altars;
		} else {
			plugin.saveResource("altars.txt", false);
			return null;
		}
	}

	public void removeAltar(Block block) {
		FileConfiguration config;
		File              file = new File(plugin.getDataFolder(), "altars.txt");
		if (file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
			Object object = config.getStringList("Altars");
			if (object == null) {
				return;
			} else {
				List<String> altars = getAltars();
				altars.remove(Methods.packageLocation(block.getLocation()));
				config.set("Altars", altars);
			}
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			plugin.saveResource("altars.txt", true);
		}
	}
}
