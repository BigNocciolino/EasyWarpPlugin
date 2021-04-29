package com.minecraftplugin.Utils.warpRequest.Executor;

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
                if (target != null) {
                    if (target != p) {
                        //FIX
                        if (Boolean.parseBoolean(Main.getInstance().getConfig().getString("sendRequest"))) {
                            //Create a new request
                            if (warpreq.getRequestBySender(p).size() >= 1) {
                                p.sendMessage("You have already sent a request to " + target.getName());
                            }else {
                                BukkitRunnable run = new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        p.sendMessage("Your request expired");
                                        warpreq.removeRequest(warpreq.getRequestBySenderAndRecivier(target, p));
                                    }
                                };
                                //TODO add config expire time
                                run.runTaskLater(Main.getInstance(), 60 * 20); // Transform in second
                                warpreq request = new warpreq(p, target, warpreq.warpType.WARPTO, run);
                                //Add it
                                warpreq.addRequest(request);
                                p.sendMessage("Sent request to " + target.getName());
                                target.sendMessage("Recivied a requesto from " + p.getName());
                            }
                        }else {
                            warpreq.warpTo(p, target);
                            //TODO messagies
                        }
                    }else {
                        p.sendMessage("WTF are you doing WHY");
                    }
                }else {
                    p.sendMessage("Questo player non esiste");
                }
            }else {
                p.sendMessage("inserisci il nome del player a cui vuoi fare la richiesta");
            }
        }
        return false;
    }
}
