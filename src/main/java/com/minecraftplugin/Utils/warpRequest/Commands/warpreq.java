package com.minecraftplugin.Utils.warpRequest.Commands;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class warpreq {

    public static List<warpreq> requestlist = new ArrayList<>();

    static Player recivier; //Player that receive the request
    static Player sender; //Player that send request
    private warpType type;

    public warpreq(Player sender, Player recivier, warpType tpType) {
        this.recivier = recivier;
        this.sender = sender;
        this.type = tpType;
    }

    public Player getRequester() {
        return sender;
    }

    public Player getResponder() {
        return recivier;
    }

    public warpType getType() {
        return type;
    }

    public enum warpType {
        WARPHERE,
        WARPTO
    }

    public static List<warpreq> getRequestsByRevier(Player recivier) {
        List<warpreq> requests = new ArrayList<>();
        for (warpreq request : requestlist) {
            if (request.recivier == recivier) {
                requests.add(request);
            }
        }
        return requests;
    }

    public static warpreq getRequestBySenderAndRecivier(Player sender, Player recivier) {
        for (warpreq req : requestlist) {
            if (req.getRequester() == sender && req.getResponder() == recivier) {
                return req;
            }
        }
        return null;
    }

    public static List<warpreq> getRequestBySender(Player sender) {
        List<warpreq> requests = new ArrayList<>();
        for (warpreq req : requestlist) {
            if (req.getRequester() == sender) {
                requests.add(req);
            }
        }
        return requests;
    }


    public static void addRequest(warpreq request) {
        requestlist.add(request);
    }

    public static void removeRequest(warpreq request) {
        requestlist.remove(request);
    }

    public static void warpTo(Player send, Player recv) {
        send.teleport(recv.getLocation());
    }

}
