package fr.will33.cdrefeel.listener;

import fr.will33.cdrefeel.CdRefeelPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onExecuteCommand(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        CdRefeelPlugin instance = CdRefeelPlugin.getInstance();
        if(instance.getRestrictedPlayers().contains(player) && instance.getBlacklistCommands().contains(event.getMessage().toLowerCase())){
            event.setCancelled(true);
        }
    }

}