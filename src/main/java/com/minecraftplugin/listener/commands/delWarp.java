package com.minecraftplugin.listener.commands;

import com.minecraftplugin.minecraftplugin.Main;

public class delWarp {

    private String wpName;

    public delWarp(String wpName) {
        this.wpName = wpName;
    }

    public void delwarp() {
        Main.getData().delData(this.wpName);
    }
}
