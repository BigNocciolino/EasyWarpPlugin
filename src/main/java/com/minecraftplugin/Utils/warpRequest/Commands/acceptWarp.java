package com.minecraftplugin.Utils.warpRequest.Commands;

import org.bukkit.entity.Player;

public class acceptWarp {

    static Player recivier;
    static Player sender;

    public acceptWarp(Player send, Player recv) {
        recivier = recv;
        sender = send;
    }

    //TODO impostare il controllo del warp in classe a parte
    public static boolean acceptRequest() {
        warpreq req = warpreq.getRequestBySenderAndRecivier(sender, recivier);
        if (req == null) {
            return false;
        }else {
            //Warp the player
            if (req.getType() == warpreq.warpType.WARPTO) {
                req.warpTo(sender, recivier);
                warpreq.removeRequest(req);
                sender.sendMessage("Ti stai warpando da " + recivier.getName());
                recivier.sendMessage(sender.getName() + " Si sta warpando da te");
                return true;
            }else {
                //Warp the recivier of the request to the sender
                req.warpTo(recivier, sender);
                warpreq.removeRequest(req);
                recivier.sendMessage("Ti stai warpando da " + recivier.getName());
                sender.sendMessage(sender.getName() + " Si sta warpando da te");
                return true;
            }
        }
    }
}
