package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.wpPending;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpList implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equals("warplist")) {
                wpPending list = new wpPending();
                p.sendMessage(wpPending.pendingRequestsSender(p));
                p.sendMessage("To you " + wpPending.pendingRequestsRecivier(p));
            }
            return true;
        }
        return false;
    }
}
