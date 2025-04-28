package com.ponkstars.announcementsplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AnnouncementsPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AnnouncementsPlugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("announce")) {
            // Ensure the sender is a player (or console can send an announcement)
            if (sender instanceof Player || sender.hasPermission("announce.use")) {
                if (args.length > 0) {
                    StringBuilder message = new StringBuilder();
                    for (String arg : args) {
                        message.append(arg).append(" ");
                    }

                    // Send the announcement to all players on the server
                    sendAnnouncement(message.toString().trim());
                } else {
                    sender.sendMessage("Usage: /announce <message>");
                }
                return true;
            } else {
                sender.sendMessage("You don't have permission to use this command.");
            }
        }
        return false;
    }

    // Method to send the announcement to all players
    private void sendAnnouncement(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendActionBar(message);
        }
    }
}
