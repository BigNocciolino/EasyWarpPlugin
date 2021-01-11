package com.minecraftplugin.listener.commands;

import com.minecraftplugin.minecraftplugin.Main;
import com.minecraftplugin.utils.TPtimeout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

public class warpReq {

    public static HashMap<String, List<String>> requests = new HashMap<String, List<String>>();
    public int maxReq = Integer.parseInt(Main.getInstance().getConfig().getString("maxWarpRequest"));
    private int reqTimeOut = Integer.parseInt(Main.getInstance().getConfig().getString("warpRequestTimeout"));
    private final String pluginName = "§5[EasyWarp]§5 ";


    public void sendRequest(Player sender, Player recivier) {
        Timer timer = new Timer();

        if (requests.containsKey(recivier.getName())) {

            if (requests.get(recivier.getName()).size() >= maxReq) {
                recivier.sendMessage(pluginName + ChatColor.RED + recivier.getName() + Main.getmessagies().getString("wprRequests.TooManyWprRequest"));
                return;

            }
        } else {
            if (recivier != sender) {
                requests.put(recivier.getName(), new ArrayList<String>());
            }else {
                sender.sendMessage( pluginName+ ChatColor.GOLD + "WHY YOU WULD DO THIS?");
                return;
            }
        }

        for (String s : requests.get(recivier.getName())) {
            if (s.equals(sender.getName())) {
                sender.sendMessage(pluginName + ChatColor.RED + Main.getmessagies().getString("wprRequests.AlredySent") + recivier.getName());
                return;
            }
        }
        sender.sendMessage(pluginName + ChatColor.GOLD + "Tepeortation request has been sent to " + recivier.getName());
        recivier.sendMessage(pluginName + ChatColor.GOLD + sender.getName() + "has sent a warp request to you");
        requests.get(recivier.getName()).add(sender.getName());
        timer.schedule(new TPtimeout(this, recivier, sender), (reqTimeOut * 1000));
    }

    public void timeOutRequest(Player recivier, String name) {
        String recivierName = recivier.getName();
        ArrayList<String> remainingNames = new ArrayList<String>();

        if (requests.containsKey(recivierName)) {
            for (String s : requests.get(recivierName)) {
                if (s.equalsIgnoreCase(name) || name.equalsIgnoreCase("all")) {
                    Player requestor = Bukkit.getPlayerExact(s);
                    recivier.sendMessage( pluginName +ChatColor.GOLD + "Teleportation request from " + s + " has been timeouted");
                    if (requestor != null) {
                        requestor.sendMessage( pluginName + ChatColor.GOLD + "Teleporation request sent to " + recivierName + " has timed out");
                    }
                } else {
                    remainingNames.add(s);
                }
            }
            requests.remove(recivierName);
            requests.put(recivierName, remainingNames);
        }
    }


    public void acceptRequest(Player dstPlayer, String sName) {
        String dstName = dstPlayer.getName();
        ArrayList<String> remainingNames = new ArrayList<String>();

        if (requests.containsKey(dstName)) {
            for (String s : requests.get(dstName)) {
                if (s.equalsIgnoreCase(sName) || sName.equalsIgnoreCase("all") || sName.equals(s)) {
                    Player requestor = Bukkit.getServer().getPlayerExact(s);
                    if (requestor != null) {
                        requestor.sendMessage( pluginName + ChatColor.GOLD + "Teleporting " + ChatColor.AQUA
                                + dstName + ChatColor.GOLD + " to your location!");
                        dstPlayer.sendMessage(pluginName + ChatColor.GOLD + "Teleporting you to " + ChatColor.AQUA
                                + s + ChatColor.GOLD + "!");
                        dstPlayer.teleport((Entity) requestor);
                    } else {
                        dstPlayer.sendMessage(pluginName + ChatColor.GOLD + "Teleport requestor, " + ChatColor.AQUA
                                + s + ChatColor.GOLD + ", is not online!");
                    }
                } else {
                    remainingNames.add(s);
                }
            }
            // remove all requests
            requests.remove(dstName);
            // replace with new list, even if empty
            requests.put(dstName, remainingNames);
        }
    }

    public void denyRequest(Player dstPlayer, String sName) {
        String dstName = dstPlayer.getName();
        ArrayList<String> remainingNames = new ArrayList<String>();

        if (requests.containsKey(dstName)) {
            for (String s : requests.get(dstName)) {
                if (s.equalsIgnoreCase(sName) || sName.equalsIgnoreCase("all") || sName.equals(s)) {
                    Player requestor = Bukkit.getPlayerExact(s);
                    dstPlayer.sendMessage(pluginName + ChatColor.GOLD + "Denying teleportation request from " + ChatColor.AQUA
                            + s + ChatColor.GOLD + "!");
                    if (requestor != null) {
                        requestor.sendMessage(pluginName + ChatColor.GOLD + "Your teleportation request to " + ChatColor.AQUA
                                + dstName + ChatColor.GOLD + " was denied!");
                    }
                } else {
                    remainingNames.add(s);
                }
            }
            // remove all requests
            requests.remove(dstName);
            // replace with new list, even if empty
            requests.put(dstName, remainingNames);
        }
    }

    public List<String> pendingRequestst(Player sender) {
        List<String> pending = new ArrayList<>();

        if (requests.containsKey(sender.getName())) {
            for (String s : requests.get(sender.getName())) {
                pending.add(s);
            }
        }
        return pending;
    }


}