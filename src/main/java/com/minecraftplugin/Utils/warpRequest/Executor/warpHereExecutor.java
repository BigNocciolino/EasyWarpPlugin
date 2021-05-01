package com.minecraftplugin.Utils.warpRequest.Executor;

import com.minecraftplugin.Utils.CustomMessagies;
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
            Player p = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                int expireTime = Integer.parseInt(Main.getInstance().getConfig().getString("warpRequest.requestLifetime"));
                if (target != null) {
                    if (target != p) {
                        if (warpreq.getRequestBySender(p).size() >= 1) {
                            CustomMessagies.sendMessage(p, "warphere.alredySent", "{player}", target.getName());
                        } else {
                            BukkitRunnable run = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    CustomMessagies.sendMessage(p, "warphere.expired", "{player}", target.getName());
                                    CustomMessagies.sendMessage(target, "warphere.expiredRecivier", "{player}", p.getName());
                                    warpreq.removeRequest(warpreq.getRequestBySenderAndRecivier(target, p));
                                }
                            };
                            //TODO config
                            run.runTaskLater(Main.getInstance(), expireTime * 20);
                            warpreq req = new warpreq(p, target, warpreq.warpType.WARPHERE, run);
                            warpreq.addRequest(req);
                            CustomMessagies.sendMessage(p, "warphere.succesfullyWarpedsender", "{player}", target.getName());
                            CustomMessagies.sendMessage(target, "warphere.succesfullyWarpedrecivier", "{player}", p.getName());
                        }
                    } else {
                        CustomMessagies.sendMessage(p, "warphere.warpToYou");
                    }
                } else {
                    CustomMessagies.sendMessage(p, "warphere.offline");
                }
            } else {
                CustomMessagies.sendMessage(p, "warphere.arg");
            }
            return true;
        }
        return false;
    }
}
