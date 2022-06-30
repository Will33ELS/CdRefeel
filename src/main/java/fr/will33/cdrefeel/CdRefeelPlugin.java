package fr.will33.cdrefeel;

import fr.will33.cdrefeel.command.RefeelCommand;
import fr.will33.cdrefeel.listener.PlayerListener;
import fr.will33.cdrefeel.task.RefeelTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CdRefeelPlugin extends JavaPlugin {

    private final List<Player> restrictedPlayers = new ArrayList<>();
    private Location refeelLocation, endDelayLocation;
    private int delayInSeconds;
    private List<String> blacklistCommands;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.refeelLocation = new Location(Bukkit.getWorld(this.getConfig().getString("refeelLocation.world")),
                this.getConfig().getDouble("refeelLocation.x"),
                this.getConfig().getDouble("refeelLocation.y"),
                this.getConfig().getDouble("refeelLocation.z"),
                this.getConfig().getLong("refeelLocation.yaw"),
                this.getConfig().getLong("refeelLocation.pitch"));

        this.endDelayLocation = new Location(Bukkit.getWorld(this.getConfig().getString("endDelayLocation.world")),
                this.getConfig().getDouble("endDelayLocation.x"),
                this.getConfig().getDouble("endDelayLocation.y"),
                this.getConfig().getDouble("endDelayLocation.z"),
                this.getConfig().getLong("endDelayLocation.yaw"),
                this.getConfig().getLong("endDelayLocation.pitch"));

        this.delayInSeconds = this.getConfig().getInt("delayInSeconds");
        this.blacklistCommands = this.getConfig().getStringList("commandsBlacklist");

        this.getCommand("refeel").setExecutor(new RefeelCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    /**
     * Launch refeel
     * @param player Instance of the player
     */
    public void launchRefeel(Player player){
        CdRefeelPlugin.getInstance().getRestrictedPlayers().add(player);
        player.teleport(CdRefeelPlugin.getInstance().getRefeelLocation());
        new RefeelTask(player).runTaskLater(CdRefeelPlugin.getInstance(), CdRefeelPlugin.getInstance().getDelayInSeconds() * 20L);
    }

    /**
     * List of currently restricted players
     * @return
     */
    public List<Player> getRestrictedPlayers() {
        return restrictedPlayers;
    }

    /**
     * Get black list command
     * @return
     */
    public List<String> getBlacklistCommands() {
        return blacklistCommands;
    }

    /**
     * Get refeel location
     * @return
     */
    public Location getRefeelLocation() {
        return refeelLocation;
    }

    /**
     * Get end delay location
     * @return
     */
    public Location getEndDelayLocation() {
        return endDelayLocation;
    }

    /**
     * Get delay in seconds
     * @return
     */
    public int getDelayInSeconds() {
        return delayInSeconds;
    }

    /**
     * Get instance of the plugin
     * @return
     */
    public static CdRefeelPlugin getInstance() {
        return CdRefeelPlugin.getPlugin(CdRefeelPlugin.class);
    }
}
