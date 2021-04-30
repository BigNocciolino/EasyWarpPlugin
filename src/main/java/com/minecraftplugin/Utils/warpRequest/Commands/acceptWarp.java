package com.minecraftplugin.Utils.warpRequest.Commands;

import com.minecraftplugin.Utils.CustomMessagies;
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
                CustomMessagies.sendMessage(sender, "acceptWarp.acceptedSender", "{player}", recivier.getName());
                CustomMessagies.sendMessage(recivier, "acceptWarp.acceptedRecivier", "{player}", sender.getName());
                warpreq.warpTo(sender, recivier);
                req.destroy();
            }else {
                //Warp the recivier of the request to the sender
                //Request type = WPHERE
                CustomMessagies.sendMessage(recivier, "acceptWarp.acceptedSender", "{player}", sender.getName());
                CustomMessagies.sendMessage(sender, "acceptWarp.acceptedRecivier", "{player}", recivier.getName());
                warpreq.warpTo(recivier, sender);
                req.destroy();
            }
        }
    }
}
