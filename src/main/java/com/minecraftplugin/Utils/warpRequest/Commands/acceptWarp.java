package com.minecraftplugin.Utils.warpRequest.Commands;

import org.bukkit.entity.Player;

public class acceptWarp {

    private warpreq req;

    public acceptWarp(warpreq request) {
        this.req = request;
    }

    //TODO impostare il controllo del warp in classe a parte
    public void acceptRequest() {

        Player sender = req.getRequester();
        Player recivier = req.getResponder();

        if (req == null) {
            sender.sendMessage("Sesso");
        }else {
            //Warp the player1
            if (req.getType() == warpreq.warpType.WARPTO) {
                sender.sendMessage("Ti stai warpando da " + recivier.getName());
                recivier.sendMessage(sender.getName() + " Si sta warpando da te");
                warpreq.warpTo(sender, recivier);
                req.destroy();
            }else {
                //Warp the recivier of the request to the sender
                //Request type = WPHERE
                recivier.sendMessage("Ti stai warpando da " + recivier.getName());
                sender.sendMessage(sender.getName() + " Si sta warpando da te");
                warpreq.warpTo(recivier, sender);
                req.destroy();
            }
        }
    }
}
