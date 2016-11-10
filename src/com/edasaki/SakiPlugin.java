package com.edasaki;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.edasaki.commands.CommandManager;

public final class SakiPlugin extends JavaPlugin {

    private static final String NAME;
    static {
        NAME = SakiPlugin.class.getName();
    }

    public static SakiPlugin plugin;

    private HashMap<Class<? extends AbstractManager>, AbstractManager> instances;

    protected void registerManager(Class<? extends AbstractManager> clazz, AbstractManager instance) throws Exception {
        if (instances.containsKey(clazz))
            throw new Exception("Duplicate manager " + clazz + " " + instance);
        instances.put(clazz, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        AbstractManager instance = instances.get(clazz);
        if (instance == null)
            return null;
        if (clazz.isInstance(instance))
            return (T) instance;
        return null;
    }

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
