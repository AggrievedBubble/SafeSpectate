package com.jacobneave.safespectate.events;

import com.jacobneave.safespectate.SafeSpectate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class PlayerCommandPreprocess implements Listener {

	Plugin plugin = SafeSpectate.getPlugin(SafeSpectate.class);

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		//if command is not to to exit SafeSpectate then it is cancelled
		if (SafeSpectate.spectators_and_entry_state.containsKey(player)) {
			if (!Objects.requireNonNull(plugin.getServer().getPluginCommand("spectate")).getAliases().contains(e.getMessage().replace("/", ""))) {
				player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You cannot send commands while in SafeSpectate");
				e.setCancelled(true);
				plugin.getLogger().info(player.getDisplayName() + "'s command was not executed");
			}

		}

	}

}
