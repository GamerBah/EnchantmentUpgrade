package com.gamerbah.old;
/* Created by GamerBah on 2/18/2016 */

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Material.*;
import static org.bukkit.block.BlockFace.*;
import static org.bukkit.enchantments.Enchantment.*;

public class Methods {

	public static String packageLocation(Location l) {
		if (l == null) {
			return null;
		}
		return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	}

	public boolean canBeAltar(Block block) {
		Files        files  = new Files(plugin);
		Block        d      = block.getRelative(DOWN);
		Block        dn     = d.getRelative(NORTH);
		Block        ds     = d.getRelative(SOUTH);
		Block        de     = d.getRelative(EAST);
		Block        dw     = d.getRelative(WEST);
		Block        dnw    = dn.getRelative(WEST);
		Block        dne    = dn.getRelative(EAST);
		Block        dsw    = ds.getRelative(WEST);
		Block        dse    = ds.getRelative(EAST);
		Block        dd     = d.getRelative(DOWN);
		Block        ddn    = dn.getRelative(DOWN);
		Block        dds    = ds.getRelative(DOWN);
		Block        dde    = de.getRelative(DOWN);
		Block        ddw    = dw.getRelative(DOWN);
		Block        ddnw   = dnw.getRelative(DOWN);
		Block        ddne   = dne.getRelative(DOWN);
		Block        ddsw   = dsw.getRelative(DOWN);
		Block        ddse   = dse.getRelative(DOWN);
		List<String> altars = files.getAltars();

		if (altars == null) {
			return true;
		} else {
			return (block.getType().equals(ENCHANTMENT_TABLE) && d.getType().equals(BEACON) && dn.getType()
					.equals(REDSTONE_WIRE) && ds.getType().equals(REDSTONE_WIRE) && dw.getType().equals(REDSTONE_WIRE)
			        && de.getType().equals(REDSTONE_WIRE) && dnw.getType().equals(REDSTONE_TORCH_ON) && dne.getType()
					        .equals(REDSTONE_TORCH_ON) && dsw.getType().equals(REDSTONE_TORCH_ON) && dse.getType()
					        .equals(REDSTONE_TORCH_ON) && dd.getType().equals(DIAMOND_BLOCK) && ddn.getType()
					        .equals(GOLD_BLOCK) && dds.getType().equals(GOLD_BLOCK) && ddw.getType().equals(GOLD_BLOCK)
			        && dde.getType().equals(GOLD_BLOCK) && ddnw.getType().equals(IRON_BLOCK) && ddne.getType()
					        .equals(IRON_BLOCK) && ddsw.getType().equals(IRON_BLOCK) && ddse.getType()
					        .equals(IRON_BLOCK)) && !altars.contains(packageLocation(block.getLocation()));
		}
	}

	public Enchantment convertToEnchantment(String string) {
		Enchantment e = null;
		if (string.equals("Sharpness")) {
			e = DAMAGE_ALL;
		}
		if (string.equals("Bane of Arthropods")) {
			e = DAMAGE_ARTHROPODS;
		}
		if (string.equals("Smite")) {
			e = DAMAGE_UNDEAD;
		}
		if (string.equals("Knockback")) {
			e = KNOCKBACK;
		}
		if (string.equals("Protection")) {
			e = PROTECTION_ENVIRONMENTAL;
		}
		if (string.equals("Blast Protection")) {
			e = PROTECTION_EXPLOSIONS;
		}
		if (string.equals("Feather Falling")) {
			e = PROTECTION_FALL;
		}
		if (string.equals("Fire Protection")) {
			e = PROTECTION_FIRE;
		}
		if (string.equals("Luck of the Sea")) {
			e = LUCK;
		}
		if (string.equals("Lure")) {
			e = LURE;
		}
		if (string.equals("Fire Aspect")) {
			e = FIRE_ASPECT;
		}
		if (string.equals("Power")) {
			e = ARROW_DAMAGE;
		}
		if (string.equals("Flame")) {
			e = ARROW_FIRE;
		}
		if (string.equals("Punch")) {
			e = ARROW_KNOCKBACK;
		}
		if (string.equals("Fortune")) {
			e = LOOT_BONUS_BLOCKS;
		}
		if (string.equals("Looting")) {
			e = LOOT_BONUS_MOBS;
		}
		if (string.equals("Unbreaking")) {
			e = DURABILITY;
		}
		if (string.equals("Efficiency")) {
			e = DIG_SPEED;
		}
		if (string.equals("Depth Strider")) {
			e = DEPTH_STRIDER;
		}
		if (string.equals("Thorns")) {
			e = THORNS;
		}
		if (string.equals("Respiration")) {
			e = OXYGEN;
		}
		if (string.equals("Frost Walker")) {
			e = FROST_WALKER;
		}
		if (string.equals("Sweeping Edge")) {
			e = SWEEPING_EDGE;
		}

		return e;
	}

	public ItemStack createBook(String name, List<String> lore) {
		ItemStack book = new ItemStack(ENCHANTED_BOOK);
		ItemMeta  im   = book.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		book.setItemMeta(im);
		return book;
	}

	public ArrayList<String> formatEnchantments(ItemStack item) {
		ArrayList<String>         formattedenchantments = new ArrayList<>();
		Map<Enchantment, Integer> enchantments          = item.getEnchantments();
		String                    understandablename    = "";
		for (Enchantment e : enchantments.keySet()) {
			if (e.equals(DAMAGE_ALL)) {
				understandablename = "Sharpness";
			}
			if (e.equals(DAMAGE_ARTHROPODS)) {
				understandablename = "Bane of Arthropods";
			}
			if (e.equals(DAMAGE_UNDEAD)) {
				understandablename = "Smite";
			}
			if (e.equals(KNOCKBACK)) {
				understandablename = "Knockback";
			}
			if (e.equals(PROTECTION_ENVIRONMENTAL)) {
				understandablename = "Protection";
			}
			if (e.equals(PROTECTION_EXPLOSIONS)) {
				understandablename = "Blast Protection";
			}
			if (e.equals(PROTECTION_FALL)) {
				understandablename = "Feather Falling";
			}
			if (e.equals(PROTECTION_FIRE)) {
				understandablename = "Fire Protection";
			}
			if (e.equals(LUCK)) {
				understandablename = "Luck of the Sea";
			}
			if (e.equals(LURE)) {
				understandablename = "Lure";
			}
			if (e.equals(FIRE_ASPECT)) {
				understandablename = "Fire Aspect";
			}
			if (e.equals(ARROW_DAMAGE)) {
				understandablename = "Power";
			}
			if (e.equals(ARROW_FIRE)) {
				understandablename = "Flame";
			}
			if (e.equals(ARROW_KNOCKBACK)) {
				understandablename = "Punch";
			}
			if (e.equals(LOOT_BONUS_BLOCKS)) {
				understandablename = "Fortune";
			}
			if (e.equals(LOOT_BONUS_MOBS)) {
				understandablename = "Looting";
			}
			if (e.equals(DURABILITY)) {
				understandablename = "Unbreaking";
			}
			if (e.equals(DIG_SPEED)) {
				understandablename = "Efficiency";
			}
			if (e.equals(DEPTH_STRIDER)) {
				understandablename = "Depth Strider";
			}
			if (e.equals(THORNS)) {
				understandablename = "Thorns";
			}
			if (e.equals(FROST_WALKER)) {
				understandablename = "Frost Walker";
			}
			if (e.equals(OXYGEN)) {
				understandablename = "Respiration";
			}
			if (e.equals(SWEEPING_EDGE)) {
				understandablename = "Sweeping Edge";
			}
			// No Upgrades
			if (e.equals(WATER_WORKER)) {
				understandablename = "Aqua Affinity";
			}
			if (e.equals(SILK_TOUCH)) {
				understandablename = "Silk Touch";
			}
			if (e.equals(ARROW_INFINITE)) {
				understandablename = "Infinity";
			}
			if (e.equals(MENDING)) {
				understandablename = "Mending";
			}
			if (e.equals(BINDING_CURSE)) {
				understandablename = "Curse of Binding";
			}
			if (e.equals(VANISHING_CURSE)) {
				understandablename = "Curse of Vanishing";
			}

			formattedenchantments.add(understandablename + " " + enchantments.get(e));
		}
		return formattedenchantments;
	}

	public Block getAltarFromBase(Block block) {
		Files files = new Files(plugin);
		if (isAltarBaseBlock(block)) {
			Block altar = null;
			for (String s : files.getAltars()) {
				if (isAltar(block.getRelative(UP))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(NORTH))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(SOUTH))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(NORTH_WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(NORTH_EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(SOUTH_WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(SOUTH_EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(NORTH))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(SOUTH))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(NORTH_WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(NORTH_EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(SOUTH_WEST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
				if (isAltar(block.getRelative(UP).getRelative(UP).getRelative(SOUTH_EAST))) {
					altar = block.getWorld().getBlockAt(unpackageLocation(s));
				}
			}
			return altar;
		}
		return null;
	}

	public boolean isAltar(Block block) {
		Block d    = block.getRelative(DOWN);
		Block dn   = d.getRelative(NORTH);
		Block ds   = d.getRelative(SOUTH);
		Block de   = d.getRelative(EAST);
		Block dw   = d.getRelative(WEST);
		Block dnw  = dn.getRelative(WEST);
		Block dne  = dn.getRelative(EAST);
		Block dsw  = ds.getRelative(WEST);
		Block dse  = ds.getRelative(EAST);
		Block dd   = d.getRelative(DOWN);
		Block ddn  = dn.getRelative(DOWN);
		Block dds  = ds.getRelative(DOWN);
		Block dde  = de.getRelative(DOWN);
		Block ddw  = dw.getRelative(DOWN);
		Block ddnw = dnw.getRelative(DOWN);
		Block ddne = dne.getRelative(DOWN);
		Block ddsw = dsw.getRelative(DOWN);
		Block ddse = dse.getRelative(DOWN);

		if (files.getAltars() == null) {
			return false;
		} else {
			return (block.getType().equals(ENCHANTMENT_TABLE) && d.getType().equals(BEACON) && dn.getType()
					.equals(REDSTONE_WIRE) && ds.getType().equals(REDSTONE_WIRE) && dw.getType().equals(REDSTONE_WIRE)
			        && de.getType().equals(REDSTONE_WIRE) && dnw.getType().equals(REDSTONE_TORCH_ON) && dne.getType()
					        .equals(REDSTONE_TORCH_ON) && dsw.getType().equals(REDSTONE_TORCH_ON) && dse.getType()
					        .equals(REDSTONE_TORCH_ON) && dd.getType().equals(DIAMOND_BLOCK) && ddn.getType()
					        .equals(GOLD_BLOCK) && dds.getType().equals(GOLD_BLOCK) && ddw.getType().equals(GOLD_BLOCK)
			        && dde.getType().equals(GOLD_BLOCK) && ddnw.getType().equals(IRON_BLOCK) && ddne.getType()
					        .equals(IRON_BLOCK) && ddsw.getType().equals(IRON_BLOCK) && ddse.getType()
					        .equals(IRON_BLOCK)) && files.getAltars().contains(packageLocation(block.getLocation()));
		}
	}

	public boolean isAltarBaseBlock(Block block) {
		if (block.getType().equals(IRON_BLOCK)) {
			return isAltar(block.getRelative(UP).getRelative(UP).getRelative(NORTH_EAST)) || isAltar(block
					.getRelative(UP).getRelative(UP).getRelative(NORTH_WEST)) || isAltar(block.getRelative(UP)
					.getRelative(UP).getRelative(SOUTH_EAST)) || isAltar(block.getRelative(UP).getRelative(UP)
					.getRelative(SOUTH_WEST));
		} else if (block.getType().equals(GOLD_BLOCK)) {
			return isAltar(block.getRelative(UP).getRelative(UP).getRelative(NORTH).getRelative(NORTH)) || isAltar(block
					.getRelative(UP).getRelative(UP).getRelative(SOUTH).getRelative(SOUTH)) || isAltar(block
					.getRelative(UP).getRelative(UP).getRelative(EAST).getRelative(EAST)) || isAltar(block
					.getRelative(UP).getRelative(UP).getRelative(WEST).getRelative(WEST));
		} else if (block.getType().equals(DIAMOND_BLOCK)) {
			return isAltar(block.getRelative(UP).getRelative(UP));
		} else if (block.getType().equals(REDSTONE_WIRE)) {
			return isAltar(block.getRelative(UP).getRelative(NORTH)) || isAltar(block.getRelative(UP)
					.getRelative(SOUTH)) || isAltar(block.getRelative(UP).getRelative(EAST)) || isAltar(block
					.getRelative(UP).getRelative(WEST));
		} else if (block.getType().equals(REDSTONE_TORCH_ON)) {
			return isAltar(block.getRelative(UP).getRelative(NORTH_WEST)) || isAltar(block.getRelative(UP)
					.getRelative(SOUTH_WEST)) || isAltar(block.getRelative(UP).getRelative(NORTH_EAST)) || isAltar(block
					.getRelative(UP).getRelative(SOUTH_EAST));
		} else if (block.getType().equals(BEACON)) {
			return isAltar(block.getRelative(UP));
		} else {
			return false;
		}
	}

	public boolean isGlitchedAltar(Block block) {
		Files        files  = new Files(plugin);
		Block        d      = block.getRelative(DOWN);
		Block        dn     = d.getRelative(NORTH);
		Block        ds     = d.getRelative(SOUTH);
		Block        de     = d.getRelative(EAST);
		Block        dw     = d.getRelative(WEST);
		Block        dnw    = dn.getRelative(WEST);
		Block        dne    = dn.getRelative(EAST);
		Block        dsw    = ds.getRelative(WEST);
		Block        dse    = ds.getRelative(EAST);
		Block        dd     = d.getRelative(DOWN);
		Block        ddn    = dn.getRelative(DOWN);
		Block        dds    = ds.getRelative(DOWN);
		Block        dde    = de.getRelative(DOWN);
		Block        ddw    = dw.getRelative(DOWN);
		Block        ddnw   = dnw.getRelative(DOWN);
		Block        ddne   = dne.getRelative(DOWN);
		Block        ddsw   = dsw.getRelative(DOWN);
		Block        ddse   = dse.getRelative(DOWN);
		List<String> altars = files.getAltars();

		if (altars == null) {
			return false;
		} else {
			return (block.getType().equals(ENCHANTMENT_TABLE) && d.getType().equals(BEACON) && dn.getType()
					.equals(REDSTONE_WIRE) && ds.getType().equals(REDSTONE_WIRE) && dw.getType().equals(REDSTONE_WIRE)
			        && de.getType().equals(REDSTONE_WIRE) && dnw.getType().equals(REDSTONE_TORCH_ON) && dne.getType()
					        .equals(REDSTONE_TORCH_ON) && dsw.getType().equals(REDSTONE_TORCH_ON) && dse.getType()
					        .equals(REDSTONE_TORCH_ON) && dd.getType().equals(DIAMOND_BLOCK) && ddn.getType()
					        .equals(GOLD_BLOCK) && dds.getType().equals(GOLD_BLOCK) && ddw.getType().equals(GOLD_BLOCK)
			        && dde.getType().equals(GOLD_BLOCK) && ddnw.getType().equals(IRON_BLOCK) && ddne.getType()
					        .equals(IRON_BLOCK) && ddsw.getType().equals(IRON_BLOCK) && ddse.getType()
					        .equals(IRON_BLOCK)) && altars.contains(packageLocation(block.getLocation()));
		}
	}

	public void removeAltar(Block block) {
		Files files = new Files(plugin);
		Block altar = null;
		Block d;
		Block dn;
		Block ds;
		Block de;
		Block dw;
		Block dnw;
		Block dne;
		Block dsw;
		Block dse;
		Block dd;
		Block ddn;
		Block dds;
		Block dde;
		Block ddw;
		Block ddnw;
		Block ddne;
		Block ddsw;
		Block ddse;
		if (isAltarBaseBlock(block)) {
			altar = getAltarFromBase(block);
			plugin.getServer().broadcastMessage(altar.toString());
		}
		if (isAltar(block)) {
			altar = block;
		}
		d = altar.getRelative(DOWN);
		dn = d.getRelative(NORTH);
		ds = d.getRelative(SOUTH);
		de = d.getRelative(EAST);
		dw = d.getRelative(WEST);
		dnw = dn.getRelative(WEST);
		dne = dn.getRelative(EAST);
		dsw = ds.getRelative(WEST);
		dse = ds.getRelative(EAST);
		dd = d.getRelative(DOWN);
		ddn = dn.getRelative(DOWN);
		dds = ds.getRelative(DOWN);
		dde = de.getRelative(DOWN);
		ddw = dw.getRelative(DOWN);
		ddnw = dnw.getRelative(DOWN);
		ddne = dne.getRelative(DOWN);
		ddsw = dsw.getRelative(DOWN);
		ddse = dse.getRelative(DOWN);
		altar.setType(AIR);
		block.getWorld().getBlockAt(d.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dn.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ds.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(de.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dnw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dne.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dsw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dse.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dd.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddn.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dds.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dde.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddnw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddne.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddsw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddse.getLocation()).setType(AIR);

		List<String> altars = files.getAltars();
		if (altars == null) {
			return;
		} else if (altars.contains(packageLocation(altar.getLocation()))) {
			files.removeAltar(altar);
			ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 1, altar.getLocation(), 50);
			block.getWorld().playSound(altar.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 3, 1);
			block.getWorld().playSound(altar.getLocation(), Sound.ENTITY_BLAZE_DEATH, 2, 1);
			plugin.addMetricsStat("used");
		}
	}

	public void removeAltarNaturally(Block block) {
		Files files = new Files(plugin);
		Block altar = null;
		Block d;
		Block dn;
		Block ds;
		Block de;
		Block dw;
		Block dnw;
		Block dne;
		Block dsw;
		Block dse;
		Block dd;
		Block ddn;
		Block dds;
		Block dde;
		Block ddw;
		Block ddnw;
		Block ddne;
		Block ddsw;
		Block ddse;
		if (isAltarBaseBlock(block)) {
			altar = getAltarFromBase(block);
		}
		if (isAltar(block)) {
			altar = block;
		}
		d = altar.getRelative(DOWN);
		dn = d.getRelative(NORTH);
		ds = d.getRelative(SOUTH);
		de = d.getRelative(EAST);
		dw = d.getRelative(WEST);
		dnw = dn.getRelative(WEST);
		dne = dn.getRelative(EAST);
		dsw = ds.getRelative(WEST);
		dse = ds.getRelative(EAST);
		dd = d.getRelative(DOWN);
		ddn = dn.getRelative(DOWN);
		dds = ds.getRelative(DOWN);
		dde = de.getRelative(DOWN);
		ddw = dw.getRelative(DOWN);
		ddnw = dnw.getRelative(DOWN);
		ddne = dne.getRelative(DOWN);
		ddsw = dsw.getRelative(DOWN);
		ddse = dse.getRelative(DOWN);
		altar.setType(AIR);
		block.getWorld().getBlockAt(d.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dn.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ds.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(de.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dnw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dne.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dsw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dse.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dd.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddn.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dds.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(dde.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddnw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddne.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddsw.getLocation()).setType(AIR);
		block.getWorld().getBlockAt(ddse.getLocation()).setType(AIR);
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(IRON_BLOCK, 4));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(GOLD_BLOCK, 4));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(DIAMOND_BLOCK, 1));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(BEACON, 1));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(REDSTONE, 4));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(REDSTONE_TORCH_ON, 4));
		block.getWorld().dropItemNaturally(altar.getLocation(), new ItemStack(ENCHANTMENT_TABLE, 1));

		List<String> altars = files.getAltars();
		if (altars == null) {
			return;
		} else if (altars.contains(packageLocation(altar.getLocation()))) {
			files.removeAltar(altar);
			ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 1, altar.getLocation(), 50);
			block.getWorld().playSound(altar.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 3, 1);
			block.getWorld().playSound(altar.getLocation(), Sound.ENTITY_BLAZE_DEATH, 2, 1);
			plugin.addMetricsStat("broken");
		}
	}

}
