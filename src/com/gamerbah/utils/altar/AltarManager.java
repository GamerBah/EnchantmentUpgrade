package com.gamerbah.utils.altar;
/* Created by GamerBah on 1/19/2018 */

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AltarManager {

	static final long ALTAR_ID = 0;

	@Getter private static HashSet<Altar> altars = new HashSet<>();

	public static boolean isAltarBase(Location location) {
		return altars.stream().anyMatch(altar -> altar.getBlockLocations().contains(location) && location.getBlock().getType() != Material.ENCHANTING_TABLE);
	}

	public static boolean isAltarCore(Location location) {
		return altars.stream().anyMatch(altar -> altar.getBlockLocations().contains(location) && location.getBlock().getType() == Material.ENCHANTING_TABLE);
	}

	public static boolean isAvailableLocation(Location location) {
		Location       start     = location.clone().add(-1, -2, -1);
		List<Location> locations = new ArrayList<>();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					Location edit = start.clone().add(x, y, z);
					locations.add(edit);
				}
			}
		}
		return locations.stream().anyMatch(loc -> loc.getBlock().getType() != Material.AIR);
	}

	public static boolean isGlitchedAltar(Location location) {
		return altars.stream().anyMatch(altar -> altar.getBlockLocations().contains(location) && location.getBlock() == null);
	}

}
