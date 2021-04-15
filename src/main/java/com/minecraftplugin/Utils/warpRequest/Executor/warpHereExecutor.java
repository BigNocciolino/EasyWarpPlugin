package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warpHereExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (command.getName().equals("warphere")) {
                if (args.length > 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target != p) {
                            warpreq req = new warpreq(p, target, warpreq.warpType.WARPHERE);
                            warpreq.addRequest(req);
                            p.sendMessage("Sent warp request to you at " + target.getName());
                            target.sendMessage("Recivied warp request to him from " + p.getName());
                        }else {
                            p.sendMessage("WHYYYYYYYYYYYYYYYY");
                        }
                    }else {
                        p.sendMessage("Questo player non esiste");
                    }
                }else {
                    p.sendMessage("Inserisci il nome del player");
                }
            }
        }
        return false;
    }
}
