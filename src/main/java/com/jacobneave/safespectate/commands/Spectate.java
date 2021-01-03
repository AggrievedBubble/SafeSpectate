package com.jacobneave.safespectate.commands;

import com.jacobneave.safespectate.EntryState;
import com.jacobneave.safespectate.SafeSpectate;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.getGameMode().equals(GameMode.SPECTATOR)) {
				if (SafeSpectate.spectators_and_entry_state.containsKey(player)) {
					SafeSpectate.spectators_and_entry_state.get(player).restore();
					SafeSpectate.spectators_and_entry_state.remove(player);
				} else {
					return true;
				}
			} else {
				SafeSpectate.spectators_and_entry_state.put(player, new EntryState(player));
				player.setGameMode(GameMode.SPECTATOR);
			}
		} else {
			System.out.println("You must be a player to run this command");
		}
		return true;
	}

}




