package com.gamerbah;

import com.gamerbah.commands.AltarCommand;
import com.gamerbah.utils.Updater;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bstats.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EnchantmentUpgrade extends JavaPlugin {

	public static final String chatStarter = ChatColor.GRAY + "[" + ChatColor.AQUA + "" + ChatColor.BOLD + "EU"
	                                         + ChatColor.GRAY + "] ";

	@Getter private static EnchantmentUpgrade instance = null;

	public void onEnable() {
		instance = this;

		getLogger().info("Enabled successfully!");

		getCommand("altar").setExecutor(new AltarCommand(this));

		// Config Version Check
		getConfig().addDefault("usePermissions", false);
		getConfig().addDefault("breakBlocksOnUse", true);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		if (getResource("altars.txt") == null) {
			saveResource("altars.txt", true);
		}

		if (getResource("metrics.txt") == null) {
			saveResource("metrics.txt", true);
		}

		// Metrics
		Metrics metrics = new Metrics(this);
		metrics.addCustomChart(new Metrics.AdvancedPie("altar_stats") {
			@Override
			public HashMap<String, Integer> getValues(HashMap<String, Integer> valueMap) {
				valueMap.put("Created", getMetricsStat("created"));
				valueMap.put("Broken", getMetricsStat("broken"));
				valueMap.put("Used", getMetricsStat("used"));
				return valueMap;
			}
		});
		metrics.addCustomChart(new Metrics.MultiLineChart("players_servers") {
			@Override
			public HashMap<String, Integer> getValues(HashMap<String, Integer> valueMap) {
				valueMap.put("Servers", 1);
				valueMap.put("Players", getServer().getOnlinePlayers().size());
				return valueMap;
			}
		});

		// Update Checker
		if (getConfig().getBoolean("checkForUpdates")) {
			Updater updateCheck;
			if (!getConfig().getBoolean("autoDownloadUpdates")) {
				updateCheck = new Updater(this, 82186, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			} else {
				updateCheck = new Updater(this, 82186, this.getFile(), Updater.UpdateType.DEFAULT, false);
			}
			Updater.UpdateResult updateCheckResult = updateCheck.getResult();
			switch (updateCheckResult) {
				case DISABLED:
					break;
				case SUCCESS:
				case UPDATE_AVAILABLE:
					for (Player player : getServer().getOnlinePlayers()) {
						if (player.isOp()) {
							player.sendMessage(chatStarter + ChatColor.GOLD + " An update is available (v" + updateCheck
									.getLatestName().substring(updateCheck.getLatestName().length() - 5) + ")");

						}
					}
			}
		}
	}

	public void onDisable() {

	}

	public void addMetricsStat(String stat) {
		FileConfiguration config;
		File              file = new File(getDataFolder(), "metrics.txt");
		if (file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
			int i = config.getInt("Metrics." + stat);
			config.set("Metrics." + stat, i + 1);
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			saveResource("metrics.txt", true);
			config = YamlConfiguration.loadConfiguration(file);
			if (stat.equals("created")) {
				int i = config.getInt("Metrics.Created");
				config.set("Metrics.Created", i + 1);
			}
		}
	}

	public int getMetricsStat(String stat) {
		FileConfiguration config;
		File              file = new File(getDataFolder(), "metrics.txt");
		if (file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
			int i = config.getInt("Metrics." + stat);
			return i;
		} else {
			saveResource("metrics.txt", false);
			return 0;
		}
	}

}
