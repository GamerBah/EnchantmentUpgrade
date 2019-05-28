package com.gamerbah.utils;
/* Created by GamerBah on 12/30/18 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum S {

	SUCCESS(Sound.BLOCK_NOTE_BLOCK_HARP, 1, 2), FAIL(Sound.ITEM_FLINTANDSTEEL_USE, 1, 1);

	private Sound sound;
	private float volume;
	private float pitch;

	public void play(Collection<Player> players) {
		players.forEach(player -> player.playSound(player.getLocation(), sound, volume, pitch));
	}

	public void play(Player... players) {
		this.play(Arrays.asList(players));
	}

	public static void play(WrappedSound sound, Collection<Player> players) {
		players.forEach(player -> player.playSound(player.getLocation(), sound.sound, sound.volume, sound.pitch));
	}

	public static void play(WrappedSound sound, Player... players) {
		play(sound, Arrays.asList(players));
	}

	public static Builder build(WrappedSound... wrappedSounds) {
		return new Builder(wrappedSounds);
	}

	/**
	 * Creates a Wrapped sound for use in the Builder.
	 */
	public class WrappedSound {

		@Getter private final Sound sound;
		@Getter private final float volume;
		@Getter private final float pitch;
		@Getter private       int   delay = 0;

		public WrappedSound(Sound sound, float volume, float pitch) {
			this.sound = sound;
			this.volume = volume;
			this.pitch = pitch;
		}

		public WrappedSound(Sound sound, float pitch) {
			this(sound, 1, pitch);
		}

		public WrappedSound(Sound sound) {
			this(sound, 1, 1);
		}

		/**
		 * Adds a delay to this sound when used in the Builder
		 *
		 * @param ticks the amount of ticks to wait before playing this sound
		 * @return this instance
		 */
		public WrappedSound withDelay(int ticks) {
			this.delay = ticks;
			return this;
		}

	}

	private static class Builder {

		@Getter private ArrayList<WrappedSound> wrappedSounds;
		@Getter private Location                location;

		Builder(WrappedSound... wrappedSounds) {
			this.wrappedSounds = (ArrayList<WrappedSound>) Arrays.asList(wrappedSounds);
		}

		private Builder atLocation(Location location) {
			this.location = Objects.requireNonNull(location);
			return this;
		}

		private void play(Collection<Player> players) {
			players.forEach(player -> {
				// TODO: Logic for playing sounds with delay
			});
		}

		private void play(Player... players) {
			this.play(Arrays.asList(players));
		}

	}

}
