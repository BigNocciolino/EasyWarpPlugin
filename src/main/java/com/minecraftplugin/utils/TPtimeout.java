package com.minecraftplugin.utils;

import com.minecraftplugin.Executors.Warprequest;
import com.minecraftplugin.listener.commands.warpReq;
import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.entity.Player;

import java.util.TimerTask;

public class TPtimeout extends TimerTask {
    private Player recivier;
    private Player sender;
    warpReq wpRequest;

    public TPtimeout (warpReq wpreq, Player recivier, Player sender) {
        this.recivier = recivier;
        this.sender = sender;
        this.wpRequest = wpreq;
    }

    @Override
    public void run() {
        wpRequest.timeOutRequest(recivier, sender.getName());
    }

}
