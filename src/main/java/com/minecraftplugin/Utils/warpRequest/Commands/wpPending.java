package com.minecraftplugin.Utils.warpRequest.Commands;

import org.bukkit.entity.Player;

public class wpPending {

    public static String pendingRequestsRecivier(Player recv) {
        String requests;
        requests = String.valueOf(warpreq.getRequestsByRevier(recv).size());
        return  requests;
    }

    public static String pendingRequestsSender(Player send) {
        String requests;
        requests = String.valueOf(warpreq.getRequestBySender(send).size());
        return  requests;
    }
}
