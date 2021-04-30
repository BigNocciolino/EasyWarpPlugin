package com.minecraftplugin.Utils;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomMessagies {

    public static String translateString(String str, String... placeholders){
        if (str == null ) return "";
        str = ChatColor.translateAlternateColorCodes('&', str);

        for (int i = 0; i < placeholders.length; i+=2){
            str = str.replace(placeholders[i], placeholders[i+1]);
        }
        return str;
    }

    public static void sendMessage(CommandSender sender, String path, String... placeholders) {
        Player player = (Player) sender;
        String message = Main.getInstance().getConfig().getString("messagies." + path);
        if (message != null) {
            message = translateString(message, placeholders);
            player.sendMessage(message);
        }else {
            player.sendMessage("");
        }
    }
}
