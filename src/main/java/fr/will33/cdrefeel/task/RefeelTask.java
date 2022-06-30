package fr.will33.cdrefeel.task;

import fr.will33.cdrefeel.CdRefeelPlugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RefeelTask extends BukkitRunnable {

    private final Player player;
    public RefeelTask(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        if(this.player == null) return;
        CdRefeelPlugin.getInstance().getRestrictedPlayers().remove(this.player);
        this.player.teleport(CdRefeelPlugin.getInstance().getEndDelayLocation());
    }
}
