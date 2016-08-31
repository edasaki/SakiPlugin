package com.edasaki.commands.general;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.edasaki.commands.ACommand;

public class TestCommand extends ACommand {

    public TestCommand(String... commandNames) {
        super(commandNames);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Test command.");
    }

    @Override
    public void executePlayer(Player p, String[] args) {
    }

    @Override
    public void executeConsole(CommandSender sender, String[] args) {
    }

}
