package com.github.civcraft.donum;

import com.github.civcraft.donum.commands.commands.Deliver;
import com.github.civcraft.donum.commands.commands.DeliverDeath;
import com.github.civcraft.donum.commands.commands.OpenDeliveries;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.civcraft.donum.listeners.AdminDeliveryListener;
import com.github.civcraft.donum.listeners.PlayerListener;
import com.github.civcraft.donum.listeners.storage.BukkitListener;

import vg.civcraft.mc.civmodcore.ACivMod;
import vg.civcraft.mc.civmodcore.commands.CommandManager;

public class Donum extends ACivMod {

	private static Donum instance;
	private DonumManager manager;
	private DonumConfiguration config;
	private CommandManager commandManager;

	public void onEnable() {
		super.onEnable();
		instance = this;
		config = new DonumConfiguration();
		config.parse();
		manager = new DonumManager();
		commandManager = new CommandManager(this);
		commandManager.init();
		registerCommands();
		registerListeners();
	}

	public void onDisable() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			manager.savePlayerData(p.getUniqueId(), p.getInventory(), false);
		}
	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new AdminDeliveryListener(), this);
		Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
	}

	private void registerCommands() {
		commandManager.registerCommand(new Deliver());
		commandManager.registerCommand(new DeliverDeath());
		commandManager.registerCommand(new OpenDeliveries());
	}

	public static Donum getInstance() {
		return instance;
	}

	public static DonumManager getManager() {
		return getInstance().manager;
	}
	
	public static DonumConfiguration getConfiguration() {
		return getInstance().config;
	}

}
