package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.warpRequest.Commands.warpreq;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
                            if (warpreq.getRequestBySender(p).size() >= 1) {
                                p.sendMessage("You have alredy sent a request");
                            }else {
                                //TODO add one request only
                                BukkitRunnable run = new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.sendMessage("Your request expired");
                                        warpreq.removeRequest(warpreq.getRequestBySenderAndRecivier(target, p));
                                    }
                                };
                                //TODO config
                                run.runTaskLater(Main.getInstance(), 60 * 20);
                                warpreq req = new warpreq(p, target, warpreq.warpType.WARPHERE, run);
                                warpreq.addRequest(req);
                                p.sendMessage("Sent warp request to you at " + target.getName());
                                target.sendMessage("Recivied warp request to him from " + p.getName());
                            }
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
