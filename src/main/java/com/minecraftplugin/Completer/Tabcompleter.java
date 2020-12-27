package com.minecraftplugin.Completer;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tabcompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        //convert the set in a ArrayList
        int setSize = Main.getData().retunrallSet().size();
        List<String> possibileWarps = new ArrayList<>(setSize);
        for (String x : Main.getData().retunrallSet()) {
            possibileWarps.add(x);
        }

        //To get the lost
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : possibileWarps) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }

        return null;
    }
}
