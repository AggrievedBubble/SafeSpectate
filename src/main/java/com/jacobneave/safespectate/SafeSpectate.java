package com.jacobneave.safespectate;

import com.jacobneave.safespectate.commands.Spectate;
import com.jacobneave.safespectate.events.PlayerQuit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SafeSpectate extends JavaPlugin implements Listener {

	public static Map<Player, EntryState> spectators_and_entry_state = new HashMap<>();

	@Override
	public void onLoad() {

		//set command executors
		try {
			Objects.requireNonNull(getCommand("spectate")).setExecutor(new Spectate());
		} catch (NullPointerException ex) {
			getLogger().severe("Failed To Load Commands, " + getDescription().getName() + " " + getDescription().getVersion() + " not Loaded");
			getPluginLoader().disablePlugin(this);
		}


		//register event listeners
		getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

	}

	@Override
	public void onEnable() {
		// Plugin startup logic

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

}
