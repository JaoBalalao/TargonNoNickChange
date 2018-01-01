package net.targon.nickcase.commands;

import net.targon.nickcase.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NoNickChange implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("tnnc.use")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission to do that."));
            return true;
        }

        if(args.length == 0) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lTargonNoNickChange &e- version " + Main.getInstance().getDescription().getVersion()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eDeveloped by Atlvntis."));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eNeed help ? use &f/NoNickChange help&e."));
            return true;

        } else if(args.length > 0 && args[0].equalsIgnoreCase("help")) {

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lTargonNoNickChange &e- version " + Main.getInstance().getDescription().getVersion()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/NoNickChange delete <nick> &f- Deletes a player from the data."));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/NoNickChange reload &f- Reloads the plugin."));
            return true;

        } else if(args.length > 0 && args[0].equalsIgnoreCase("delete")) {

            if(args.length < 2 || args.length > 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUse: &f/NoNickChange delete <nick>&e."));
                return true;
            }

            if(!Main.getInstance().getPlayers().containsKey(args[1].toLowerCase())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlayer not found."));
                return true;
            }

            Main.getInstance().getPlayers().remove(args[1].toLowerCase());

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&ePlayer successfully deleted."));
            return true;

        } else if(args.length > 0 && args[0].equalsIgnoreCase("reload")) {

            if(args.length != 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eUse: &f/NoNickChange reload&e."));
                return true;
            }

            Main.getInstance().reloadConfig();

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eConfiguration successfully reloaded."));
            return true;

        }





        return false;
    }

}
