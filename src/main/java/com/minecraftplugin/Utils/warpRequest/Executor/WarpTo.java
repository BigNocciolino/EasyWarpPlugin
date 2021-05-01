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

public class WarpTo implements CommandExecutor {

    /*
    * Class for the exeecutor for the warp requeto to another player
    *
    * If tou send a request you can warp yourself to him only if it accept
    */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //Player who send the request
            Player p = (Player) sender;
            if (args.length > 0) {
                //Player who recive the request
                Player target = Bukkit.getPlayer(args[0]);
                int lifetime = Integer.parseInt(Main.getInstance().getConfig().getString("warpRequest.requestLifetime"));
                if (target != null) {
                    if (target != p) {
                        //FIX
                        if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("warpRequest.sendRequest"))) {
                            //Create a new request
                            if (warpreq.getRequestBySender(p).size() >= 1) {
                                CustomMessagies.sendMessage(p, "warpreq.aredySent", "{player}", target.getName());
                            }else {
                                BukkitRunnable run = new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        CustomMessagies.sendMessage(p, "warpreq.expired", "{player}", target.getName());
                                        CustomMessagies.sendMessage(target, "warpreq.expiredRecivier", "{player}", p.getName());
                                        warpreq.removeRequest(warpreq.getRequestBySenderAndRecivier(target, p));
                                    }
                                };
                                //TODO add config expire time
                                run.runTaskLater(Main.getInstance(), lifetime * 20); // Transform in second
                                warpreq request = new warpreq(p, target, warpreq.warpType.WARPTO, run);
                                //Add it
                                warpreq.addRequest(request);
                                CustomMessagies.sendMessage(p, "warpreq.succesfullyWarpedsender", "{player}", target.getName());
                                CustomMessagies.sendMessage(target, "warpreq.succesfullyWarpedrecivier", "{player}", p.getName());
                            }
                        }else {
                            warpreq.warpTo(p, target);
                            //TODO messagies
                        }
                    }else {
                        CustomMessagies.sendMessage(p, "warpreq.warpToYou");
                    }
                }else {
                    CustomMessagies.sendMessage(p, "warpreq.offline");
                }
            }else {
                CustomMessagies.sendMessage(p, "warpreq.arg");
            }
            return true;
        }
        return false;
    }
}
