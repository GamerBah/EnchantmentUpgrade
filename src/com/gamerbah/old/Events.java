package com.gamerbah.old;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.*;
import static org.bukkit.enchantments.Enchantment.*;

public class Events implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block  block  = event.getBlock();
		if (methods.isAltar(block) || methods.isAltarBaseBlock(block)) {
			if (!player.hasPermission("enchupgrade.break")) {
				player.sendMessage(RED + "You do not have permission to break Enchantment Upgrade altars!");
				event.setCancelled(true);
				return;
			}
			if (player.hasPermission("enchupgrade.break." + player.getWorld().getName())) {
				if (!player.isOp()) {
					player.sendMessage(
							RED + "You do not have permission to break Enchantment Upgrade altars in this world!");
					event.setCancelled(true);
					return;
				}
			}
			if (player.getGameMode() == GameMode.CREATIVE) {
				if (player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("sword")) {
					event.setCancelled(true);
					return;
				} else if (player.getInventory().getItemInMainHand().getType().equals(AIR)) {
					event.setCancelled(false);
				} else {
					event.setCancelled(true);
					return;
				}
			}
			methods.removeAltarNaturally(block);
			player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
		} else {
			return;
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_BLOCK) {
			return;
		}
		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().getType().equals(AIR)) {
			return;
		}

		Player player = event.getPlayer();

		if (player.getGameMode() == GameMode.CREATIVE) {
			event.setCancelled(false);
		}

		ItemStack inHand = player.getInventory().getItemInMainHand();
		if (methods.isAltar(event.getClickedBlock())) {
			if (plugin.getConfig().getBoolean("usePermissions")) {
				if (!player.hasPermission("enchupgrade.use")) {
					player.sendMessage(RED + "You do not have permission to use Enchantment Upgrade altars!");
					event.setCancelled(true);
					return;
				}
				if (player.hasPermission("enchupgrade.use." + player.getWorld().getName())) {
					if (!player.isOp()) {
						player.sendMessage(
								RED + "You do not have permission to use Enchantment Upgrade altars in this world!");
						event.setCancelled(true);
						return;
					}
				}
			}
			Inventory inv = Bukkit.getServer().createInventory(player, 27, "Item Upgrade Altar");
			if (inHand.getEnchantments().size() == 0) {
				player.sendMessage(GRAY + "" + ITALIC + "A strange voice whispers to you: ");
				player.sendMessage(
						GRAY + "" + ITALIC + "\"You must empower your weapon before placing it on this altar.\"");
				player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1, 1);
				event.setCancelled(true);
				return;
			} else {
				for (int i = 0; i <= methods.formatEnchantments(inHand).size() - 1; i++) {
					String       enchantment = methods.formatEnchantments(inHand).get(i);
					List<String> lore        = new ArrayList<>();
					int          level       = Integer
							.parseInt(enchantment.substring(enchantment.length() - 1));

					while (enchantment.contains("Mending") || enchantment.contains("Silk") || enchantment
							.contains("Infinity") || enchantment.contains("Flame") || enchantment
							       .contains("Aqua Affinity")) {
						lore.add(DARK_RED + "Unavailable Upgrade!");
						lore.add("");
						lore.add(YELLOW + "This enchantment can't");
						lore.add(YELLOW + "be upgraded!");
						inv.setItem(
								10 + i,
								methods.createBook(
										RED + enchantment.substring(0, enchantment.length() - 2) + " Upgrade",
										lore
								                  )
						           );
						if (i != methods.formatEnchantments(inHand).size() - 1 && !enchantment.contains("Curse")) {
							i++;
							enchantment = methods.formatEnchantments(inHand).get(i);
							lore.clear();
							level = Integer
									.parseInt(enchantment.substring(enchantment.length() - 1));
						} else {
							break;
						}
					}

					if (enchantment.contains("Curse")) {
						break;
					}

					if (enchantment.contains("Depth") || enchantment.contains("")) {
						if (enchantment.contains("10")) {
							lore.add(RED + "Upgrade Unavailable");
							lore.add("");
							lore.add(AQUA + "You've upgraded this");
							lore.add(AQUA + "enchantment to the max!");
							inv.setItem(
									10 + i,
									methods.createBook(
											GOLD + enchantment.substring(0, enchantment.length() - 3) + " Upgrade",
											lore
									                  )
							           );
						} else {
							if (plugin.getConfig().getBoolean("consumeXP")) {
								if (enchantment.contains("10")) {
									return;
								} else {
									int ench         = Integer.parseInt(enchantment
											.substring(enchantment.length() - 1));
									int base         = plugin.getConfig().getInt("xpStart");
									int rate         = plugin.getConfig().getInt("xpRate");
									int enchMaxLevel = methods
											.convertToEnchantment(enchantment.substring(0, enchantment.length() - 2))
											.getMaxLevel();
									int req          = 0;
									if (ench <= enchMaxLevel) {
										req = base;
									}
									if (ench > enchMaxLevel) {
										req = base + ((ench - enchMaxLevel) * rate);
									}
									if (!(player.getLevel() >= (req))) {
										lore.add(0, GOLD + "Required XP: " + RED + req + " Levels");
									} else {
										lore.add(0, GOLD + "Required XP: " + GREEN + req + " Levels");
									}
									lore.add(1, "");
									lore.add(2, WHITE + "Increases your " + AQUA + enchantment);
									lore.add(
											3,
											WHITE + "to " + AQUA + enchantment.substring(0, enchantment.length() - 2)
											+ " " + (level + 1)
									        );
									inv.setItem(
											10 + i,
											methods.createBook(
													GREEN + enchantment.substring(0, enchantment.length() - 2)
													+ " Upgrade",
													lore
											                  )
									           );
								}
							} else {
								lore.add(0, WHITE + "Increases your " + AQUA + enchantment);
								lore.add(
										1,
										WHITE + "to " + AQUA + enchantment.substring(0, enchantment.length() - 2) + " "
										+ (level + 1)
								        );
								if (plugin.getConfig().getBoolean("useVault")) {
									lore.add(2, " ");
									lore.add(
											3,
											GREEN + "" + BOLD + "Cost: " + plugin.getConfig().getDouble("upgradeCost")
									        );
								}
								inv.setItem(
										10 + i,
										methods.createBook(
												GREEN + enchantment.substring(0, enchantment.length() - 2) + " Upgrade",
												lore
										                  )
								           );
							}
						}
					}
				}
			}

			ItemStack cancel = new ItemStack(BARRIER);
			ItemMeta  im     = cancel.getItemMeta();
			im.setDisplayName(RED + "Cancel Upgrade");
			cancel.setItemMeta(im);

			inv.setItem(22, cancel);

			player.openInventory(inv);
			loc = event.getClickedBlock().getLocation();
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("Item Upgrade Altar")) {
			Player player = (Player) e.getWhoClicked();

			if (e.getCurrentItem() == null) {
				return;
			}

			if (e.getCurrentItem().getType().equals(ENCHANTED_BOOK)) {
				ItemStack   inHand      = player.getInventory().getItemInMainHand();
				ItemStack   clickedBook = e.getCurrentItem();
				String      enchantment = clickedBook.getItemMeta().getDisplayName()
						.substring(0, clickedBook.getItemMeta().getDisplayName().length() - 8);
				Enchantment ench        = null;
				if (enchantment.equals(GREEN + "Sharpness")) {
					ench = DAMAGE_ALL;
					enchantment = "Sharpness";
				}
				if (enchantment.equals(GREEN + "Smite")) {
					ench = DAMAGE_UNDEAD;
					enchantment = "Smite";
				}
				if (enchantment.equals(GREEN + "Bane of Arthropods")) {
					ench = DAMAGE_ARTHROPODS;
					enchantment = "Bane of Arthropods";
				}
				if (enchantment.equals(GREEN + "Unbreaking")) {
					ench = DURABILITY;
					enchantment = "Unbreaking";
				}
				if (enchantment.equals(GREEN + "Fire Aspect")) {
					ench = FIRE_ASPECT;
					enchantment = "Fire Aspect";
				}
				if (enchantment.equals(GREEN + "Knockback")) {
					ench = KNOCKBACK;
					enchantment = "Knockback";
				}
				if (enchantment.equals(GREEN + "Looting")) {
					ench = LOOT_BONUS_MOBS;
					enchantment = "Looting";
				}
				if (enchantment.equals(GREEN + "Power")) {
					ench = ARROW_DAMAGE;
					enchantment = "Power";
				}
				if (enchantment.equals(GREEN + "Punch")) {
					ench = ARROW_KNOCKBACK;
					enchantment = "Punch";
				}
				if (enchantment.equals(GREEN + "Flame")) {
					ench = ARROW_FIRE;
					enchantment = "Flame";
				}
				if (enchantment.equals(GREEN + "Protection")) {
					ench = PROTECTION_ENVIRONMENTAL;
					enchantment = "Protection";
				}
				if (enchantment.equals(GREEN + "Blast Protection")) {
					ench = PROTECTION_EXPLOSIONS;
					enchantment = "Blast Protection";
				}
				if (enchantment.equals(GREEN + "Feather Falling")) {
					ench = PROTECTION_FALL;
					enchantment = "Feather Falling";
				}
				if (enchantment.equals(GREEN + "Fire Protection")) {
					ench = PROTECTION_FIRE;
					enchantment = "Fire Protection";
				}
				if (enchantment.equals(GREEN + "Projectile Protection")) {
					ench = PROTECTION_PROJECTILE;
					enchantment = "Projectile Protection";
				}
				if (enchantment.equals(GREEN + "Luck of the Sea")) {
					ench = LUCK;
					enchantment = "Luck";
				}
				if (enchantment.equals(GREEN + "Lure")) {
					ench = LURE;
					enchantment = "Lure";
				}
				if (enchantment.equals(GREEN + "Thorns")) {
					ench = THORNS;
					enchantment = "Thorns";
				}
				if (enchantment.equals(GREEN + "Fortune")) {
					ench = LOOT_BONUS_BLOCKS;
					enchantment = "Fortune";
				}
				if (enchantment.equals(GREEN + "Depth Strider")) {
					ench = DEPTH_STRIDER;
					enchantment = "Depth Strider";
				}
				if (enchantment.equals(GREEN + "Respiration")) {
					ench = OXYGEN;
					enchantment = "Respiration";
				}
				if (enchantment.equals(GREEN + "Efficiency")) {
					ench = DIG_SPEED;
					enchantment = "Efficiency";
				}
				if (enchantment.equals(GREEN + "Frost Walker")) {
					ench = FROST_WALKER;
					enchantment = "Frost Walker";
				}
				if (enchantment.equals(GREEN + "Sweeping Edge")) {
					ench = SWEEPING_EDGE;
					enchantment = "Sweeping Edge";
				}

				if (clickedBook.getItemMeta().getDisplayName().contains(GOLD + "Upgrade") || ench == null) {
					e.setCancelled(true);
					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
					return;
				}
				int level = inHand.getEnchantmentLevel(ench) + 1;
				inHand.removeEnchantment(ench);
				inHand.addUnsafeEnchantment(ench, level);
				player.closeInventory();
				methods.removeAltar(player.getWorld().getBlockAt(loc));
				ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 1, loc, 50);
				player.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 3, 1);
				player.getWorld().playSound(loc, Sound.ENTITY_BLAZE_DEATH, 2, 1);
				player.sendMessage(
						GRAY + "Upgraded " + GOLD + "" + BOLD + enchantment + WHITE + " \u00BB " + AQUA + "Level "
						+ level);
			}

			if (e.getCurrentItem().getType().equals(BARRIER)) {
				player.closeInventory();
			}
			e.setCancelled(true);
		}
	}
}
