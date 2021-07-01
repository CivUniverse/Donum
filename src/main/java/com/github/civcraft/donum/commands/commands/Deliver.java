package com.github.civcraft.donum.commands.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import com.github.civcraft.donum.gui.AdminDeliveryGUI;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import vg.civcraft.mc.namelayer.NameAPI;

public class Deliver extends BaseCommand {

	@CommandAlias("deliver")
	@CommandPermission("donum.op")
	@Syntax("<player>")
	@Description("Opens an inventory to which you can add items to forward them to the players delivery inventory")
	@CommandCompletion("@allplayers")
	public void execute(Player sender, String targetPlayer) {
		//TODO make namelayer soft dependency
		UUID delUUID = NameAPI.getUUID(targetPlayer);
		if (delUUID == null) {
			sender.sendMessage(ChatColor.RED + "This player has never logged into the server");
			return;
		}
		AdminDeliveryGUI.showInventory((Player) sender, delUUID);
	}
}
