package com.edasaki;

import org.bukkit.event.Listener;

public abstract class AbstractManager implements Listener {
    public static SakiPlugin plugin;

    public AbstractManager(SakiPlugin plugin) {
        try {
            load(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + this.getClass().getSimpleName() + ".");
    }

    public void load(SakiPlugin plugin) {
        AbstractManager.plugin = plugin;
        try {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void initialize();

}
