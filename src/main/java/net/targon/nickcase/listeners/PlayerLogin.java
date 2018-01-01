package net.targon.nickcase.listeners;

import net.targon.nickcase.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {

    @EventHandler
    public void playerLogin(PlayerLoginEvent e) {

        if(Main.getInstance().getPlayers().containsKey(e.getPlayer().getName().toLowerCase())) {

            if(!Main.getInstance().getPlayers().get(e.getPlayer().getName().toLowerCase()).equals(e.getPlayer().getName())) {

                StringBuilder sb = new StringBuilder();

                for(String string : Main.getInstance().getConfig().getStringList("Messages.kickWrongNick")) {

                    sb.append(ChatColor.translateAlternateColorCodes('&', string));
                    sb.append("\n");

                }

                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, sb.toString());

            } else {

                Main.getInstance().getPlayers().put(e.getPlayer().getName().toLowerCase(), e.getPlayer().getName());

            }

        }

    }


}
