package com.minecraftplugin.data;

import com.minecraftplugin.minecraftplugin.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class data {

    public static final File dir = new File(Main.getInstance().getDataFolder() + "/EasyWarp");//Cartella del file
    public static final File file = new File(dir + "data.yml"); //Folder del file dove sono instanziati tutti i plugin
    private FileConfiguration data;

    public data() {
        if (!file.exists()) {
            try {
                dir.mkdirs();
                file.createNewFile();
                this.data = YamlConfiguration.loadConfiguration(file);
                this.data.createSection("Warps");
                this.data.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        this.data = YamlConfiguration.loadConfiguration(file);
    }



    public void saveData(Player p, Location lo, String wpName) {
        this.data.createSection("Warps." + wpName);
        this.data.set("Warps."+wpName+ "." +"Owner", p.getName());
        this.data.set("Warps."+wpName+ "." +"World", lo.getWorld().getName());
        this.data.set("Warps."+wpName+ "." +"X", lo.getX());
        this.data.set("Warps."+wpName+ "." +"Y", lo.getY());
        this.data.set("Warps."+wpName+ "." +"Z", lo.getZ());
        this.data.set("Warps."+wpName+ "." +"Pitch", lo.getPitch());

        try {
            this.data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delData(String wpName) {
        this.data.set("Warps."+wpName, null);

        try {
            this.data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method retunr true if there is no warp whith the wpName
    public boolean warpExist(String wpName) {
        return this.data.getString("Warps."+wpName)==null;
    }

    //Retunrn the warp name
    public String returnWarpName(String wpName) {
        return (String) this.data.getString("Warps."+wpName);
    }

    //Return the x location
    public double returnWarpX(String wpName) {
        return (double) this.data.get("Warps."+wpName+".X");
    }

    //Return the Y value
    public double returnwarpY(String warpName){
        return (double) this.data.get("Warps."+warpName+".Y");
    }

    //retunr the owner of this warp, only the owner can change the warpData
    public String returnOwner(String warpName) {
        return (String) this.data.get("Warps."+warpName+".Owner").toString();
    }

    //return the pitch
    public double returnPitch(String warpName) {
        return (double) this.data.get("Warps."+warpName+".Pitch");
    }

    //Retunr the world
    public String returnWorld(String warpName) {
        return (String) this.data.get("Warps."+warpName+".World");
    }

    //return the z
    public double returnWarpZ(String warpname) {
        return (double) this.data.get("Warps."+warpname+".Z");
    }

    //Method for return all warep in the yaml file for the tabCompleter
    public Set<String> retunrallSet() {
        Set<String> set;
        set = this.data.getConfigurationSection("Warps").getKeys(false);
        return set;
    }

}
