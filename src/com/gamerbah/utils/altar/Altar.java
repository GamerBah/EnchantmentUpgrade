package com.gamerbah.utils.altar;
/* Created by GamerBah on 1/19/2018 */

import com.gamerbah.EnchantmentUpgrade;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Altar implements ConfigurationSerializable {

	@Getter private final long     id;
	@Getter private final Location location;

	@Getter private final HashMap<Location, Material> blockLocations = new HashMap<>();
	@Getter private final HashSet<UUID>               trustedPlayers = new HashSet<>();
	@Getter private final HashSet<Upgrade>            upgrades       = new HashSet<>();

	@Getter
	@Setter
	private int  level;
	@Getter
	@Setter
	private UUID owner;


	public Altar(final Location location, UUID owner) {
		this.id = AltarManager.ALTAR_ID + 1;
		this.location = location;
		this.owner = owner;
		Location start = location.clone().add(-1, -1, -1);
		for (int y = 0; y < 3; y++) {
			for (int z = 0; z < 3; z++) {
				if (z % 2 == 0) {
					if (y == 0) {
						blockLocations.put(start.clone().add(0, y, z), Material.GOLD_BLOCK);
						blockLocations.put(start.clone().add(1, y, z), Material.IRON_BLOCK);
						blockLocations.put(start.clone().add(2, y, z), Material.GOLD_BLOCK);
					} else if (y == 1) {

					} else {

					}
				} else {
					if (y == 0) {
						blockLocations.put(start.clone().add(0, y, z), Material.IRON_BLOCK);
						blockLocations.put(start.clone().add(1, y, z), Material.DIAMOND_BLOCK);
						blockLocations.put(start.clone().add(2, y, z), Material.IRON_BLOCK);
					} else if (y == 1) {

					} else {

					}
				}
			}
		}
		/*
		Location edit = start.clone().add(x, y, z);
					if (edit.getBlock().getType() != Material.AIR) {
						blockLocations.add(edit);
					}
		 */
	}

	public static Altar deserialize(Map<String, Object> data) {
		// TODO
		return null;
	}

	public boolean addTrustedPlayer(UUID uuid) {
		if (!trustedPlayers.contains(uuid)) {
			trustedPlayers.add(uuid);
			return true;
		}
		return false;
	}

	public boolean addUpgrade(Upgrade upgrade) {
		if (!upgrades.contains(upgrade)) {
			upgrades.add(upgrade);
			return true;
		}
		return false;
	}

	public boolean decreaseUpgradeLevel(Upgrade upgrade) {
		return upgrades.contains(upgrade) && upgrade.decreaseLevel();
	}

	public boolean increaseUpgradeLevel(Upgrade upgrade) {
		return upgrades.contains(upgrade) && upgrade.increaseLevel();
	}

	public boolean removeTrustedPlayer(UUID uuid) {
		if (trustedPlayers.contains(uuid)) {
			trustedPlayers.remove(uuid);
			return true;
		}
		return false;
	}

	public boolean removeUpgrade(Upgrade upgrade) {
		if (upgrades.contains(upgrade)) {
			upgrades.remove(upgrade);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> serialize() {
		// TODO
		return null;
	}

	/*public void use(Player player) {
		if (EnchantmentUpgrade.getInstance().getConfig().getBoolean("breakBlocksOnUse")) {
			Collection<ItemStack> drops = new ArrayList<>();
			blockLocations.stream().filter(loc -> loc.getBlock() != null).forEach(loc -> {
				// TODO: Add chances based on Efficiency Upgrades
				drops.addAll(loc.getBlock().getDrops());
				blockLocations.remove(loc);
			});
			drops.forEach(drop -> location.getWorld().dropItemNaturally(location, drop));
		}
		player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		location.getWorld()
				.createExplosion(location.getX(), location.getBlockY() - 1, location.getZ(), 2F, false, false);
	}*/

}
