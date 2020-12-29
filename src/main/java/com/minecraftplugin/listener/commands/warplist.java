package com.minecraftplugin.listener.commands;


import com.minecraftplugin.minecraftplugin.Main;

import java.util.ArrayList;
import java.util.List;

public class warplist {


    public List<String> returnWarpList() {
        int size = Main.getData().retunrallSet().size();
        List<String> warps = new ArrayList<>(size);
        for (String x : Main.getData().retunrallSet()) {
            warps.add(x);
        }
        return warps;
    }



}
