package com.minecraftplugin.data;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class manageMessagesYaml {

    private File file = new File(Main.getInstance().getDataFolder()+"/messages.yml");
    private FileConfiguration conf;

    public manageMessagesYaml() {
        if (!file.exists()) {
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                createMessagsFile();
                this.conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getFile() {
        return conf;
    }


    public void createMessagsFile() throws IOException {
        this.conf.createSection("SetWarpMessagies");
        this.conf.set("SetWarpMessagies." + "SuccesfullySet", "Succesfully set the warp");
        this.conf.set("SetWarpMessagies." + "AlredyExist", "The warp already exists");
        this.conf.set("SetWarpMessagies." + "Arg", "The argument must be present");

        this.conf.createSection("WarpMessagies");
        this.conf.set("WarpMessagies." + "SyccesfullyWarped", "You warped yourself");
        this.conf.set("WarpMessagies." + "NoWarp", "There is no warp with this name");
        this.conf.set("WarpMessagies." + "Arg", "The argument must be present");

        this.conf.createSection("DeleteWarp");
        this.conf.set("DeleteWarp." + "SuccesfulluDeleted", "Succesfully deleted the warp");
        this.conf.set("DeleteWarp." + "NoWarp", "You aren''t the owner of this plugin");
        this.conf.set("DeleteWarp." + "NotOwner", "There is no warp with this name");
        this.conf.set("DeleteWarp." + "Arg", "The argument must be present");

        this.conf.createSection("warps");
        this.conf.set("warps." + "NoWarpInList", "There is no warp in the list");
    }
}
