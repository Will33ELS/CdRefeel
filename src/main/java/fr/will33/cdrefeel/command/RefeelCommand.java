package fr.will33.cdrefeel.command;

import fr.will33.cdrefeel.CdRefeelPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefeelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            CdRefeelPlugin.getInstance().launchRefeel(player);
        }
        return false;
    }
}
