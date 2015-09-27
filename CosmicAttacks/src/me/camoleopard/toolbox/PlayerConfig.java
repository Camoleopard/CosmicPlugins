package me.camoleopard.toolbox;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.camoleopard.betterfactions.BetterFactions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerConfig extends YamlConfiguration {

	public static JavaPlugin owningPlugin = null;
	
	public PlayerConfig(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}
	
    private static Map<UUID, PlayerConfig> configs = new HashMap<UUID, PlayerConfig>();

    public static PlayerConfig getConfig(Player player) {
        return getConfig(player.getUniqueId());
    }

    public static PlayerConfig getConfig(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        if (!new File(owningPlugin.getPlugin(BetterFactions.class).getDataFolder(), "userdata" + File.separator + uuid.toString() + ".yml").exists()) {
            return null;
        }
        return getConfig(uuid);
    }

    public static PlayerConfig getConfig(UUID uuid) {
        synchronized (configs) {
            if (configs.containsKey(uuid)) {
                return configs.get(uuid);
            }
            PlayerConfig config = new PlayerConfig(uuid);
            configs.put(uuid, config);
            return config;
        }
    }

    public static void removeConfigs() {
        Collection<PlayerConfig> oldConfs = new ArrayList<PlayerConfig>(configs.values());
        synchronized (configs) {
            for (PlayerConfig config : oldConfs) {
                config.discard();
            }
        }
    }

    private File file = null;
    private Object saveLock = new Object();
    private UUID uuid;

    public PlayerConfig(UUID uuid) {
        super();
        file = new File(owningPlugin.getPlugin(BetterFactions.class).getDataFolder(), "userdata" + File.separator + uuid.toString() + ".yml");
        this.uuid = uuid;
        reload();
    }

    @SuppressWarnings("unused")
    private PlayerConfig() {
        uuid = null;
    }

    private void reload() {
        synchronized (saveLock) {
            try {
                load(file);
            } catch (Exception ignore) {
            }
        }
    }

    public void save() {
        synchronized (saveLock) {
            try {
                save(file);
            } catch (Exception ignore) {
            }
        }
    }

    public void discard() {
        discard(false);
    }

    public void discard(boolean save) {
        if (save) {
            save();
        }
        synchronized (configs) {
            configs.remove(uuid);
        }
    }

    public Location getLocation(String path) {
        if (get(path) == null) {
            return null;
        }
        World world = Bukkit.getWorld(getString(path + ".world", ""));
        if (world == null) {
            return null;
        }
        double x = getDouble(path + ".x");
        double y = getDouble(path + ".y");
        double z = getDouble(path + ".z");
        float pitch = (float) getDouble(path + ".pitch");
        float yaw = (float) getDouble(path + ".yaw");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public void setLocation(String path, Location value) {
        set(path + ".world", value.getWorld().getName());
        set(path + ".x", value.getX());
        set(path + ".y", value.getY());
        set(path + ".z", value.getZ());
        set(path + ".pitch", value.getPitch());
        set(path + ".yaw", value.getYaw());
    }
   
}
 