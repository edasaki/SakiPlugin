package com.edasaki.commands;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.edasaki.Manager;
import com.edasaki.SakiPlugin;
import com.edasaki.commands.general.TestCommand;

public class CommandManager extends Manager {

    public static CommandMap cmap = null;

    public CommandManager(SakiPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().split(" ")[0].contains(":")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Hidden syntax is disabled.");
        }
    }

    @Override
    public void initialize() {
        try {
            Field f = CraftServer.class.getDeclaredField("commandMap");
            f.setAccessible(true);
            cmap = (CommandMap) f.get(plugin.getServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cmap == null) {
            Log.error("FATAL ERROR: COULD NOT RETRIEVE COMMAND MAP.");
            plugin.getServer().shutdown();
            return;
        }
        ACommand.plugin = plugin;

        // Member
        register(new TestCommand("testcommand"));
    }

    private void register(ACommand command) {
        cmap.register("", command);
        plugin.getServer().getPluginManager().registerEvents(command, plugin);
    }
}
