package com.github.doka.fastfishing;

import net.minecraft.server.v1_12_R1.EntityFishingHook;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.entity.FishHook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class FastFishingPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event){
        setBiteTime(event.getHook(),5);
    }

    private void setBiteTime(FishHook hook, int time) {
        net.minecraft.server.v1_12_R1.EntityFishingHook hookCopy = (EntityFishingHook) ((CraftEntity) hook).getHandle();
        Field fishCatchTime = null;
        try {
            fishCatchTime = net.minecraft.server.v1_12_R1.EntityFishingHook.class.getDeclaredField("h");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        fishCatchTime.setAccessible(true);
        try {
            fishCatchTime.setInt(hookCopy, time);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        fishCatchTime.setAccessible(false);
    }
}
