package com.jacobneave.safespectate;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public final class SafeSpectate extends JavaPlugin implements Listener {

	public static Map<Player, EntryState> spectators_and_entry_state = new HashMap<>();

	@Override
	public void onEnable() {
		// Plugin startup logic
		System.out.println(this.getDescription().getName() + " " + this.getDescription().getVersion() + " Ready");
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equals("spectate")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.getGameMode().equals(GameMode.SPECTATOR)) {
					if (spectators_and_entry_state.containsKey(player)) {
						spectators_and_entry_state.get(player).restore();
						spectators_and_entry_state.remove(player);
					} else {
						return true;
					}
				} else {
					spectators_and_entry_state.put(player, new EntryState(player));
					player.setGameMode(GameMode.SPECTATOR);
				}
			} else {
				System.out.println("You must be a player to run this command");
			}
		}
		return super.onCommand(sender, command, label, args);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {

	}

}
