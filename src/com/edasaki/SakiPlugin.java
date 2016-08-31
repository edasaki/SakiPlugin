package com.edasaki;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.edasaki.commands.CommandManager;

public final class SakiPlugin extends JavaPlugin {

    private static final String NAME;
    static {
        NAME = SakiPlugin.class.getName();
    }

    public static SakiPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        File f = getDataFolder();
        if (!f.exists())
            f.mkdirs();

        // Instantiate Managers here
        new CommandManager(this);

        System.out.println("Enabled " + SakiPlugin.NAME + ".");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled " + SakiPlugin.NAME + ".");
    }
}
