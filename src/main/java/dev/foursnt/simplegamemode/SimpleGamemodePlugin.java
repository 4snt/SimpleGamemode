package dev.foursnt.simplegamemode;

import java.io.File;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleGamemodePlugin extends JavaPlugin {

    private List<String> blockedGamemodes;
    private ConfigurationSection messages;

    @Override
    public void onEnable() {
        // Garante que a pasta do plugin existe
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Salva o config.yml se não existir
        saveDefaultConfig();

        // Salva o messages.yml se não existir
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }

        // Carrega configurações
        blockedGamemodes = getConfig().getStringList("blocked-gamemodes");
        loadMessages();

        getLogger().info("SimpleGamemode enabled.");
    }

    private void loadMessages() {
        File file = new File(getDataFolder(), "messages.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        String lang = yaml.getString("language", "en");
        messages = yaml.getConfigurationSection("messages." + lang);

        if (messages == null) {
            getLogger().warning("Missing messages section for language: " + lang);
            messages = yaml.getConfigurationSection("messages.en"); // fallback
        }
    }

    private String msg(String key) {
        return messages.getString(key, "§cMessage missing: " + key);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(msg("only-players"));
            return true;
        }

        if (!player.hasPermission("simplegamemode.use")) {
            player.sendMessage(msg("no-permission"));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(msg("usage"));
            return true;
        }

        GameMode mode;
        switch (args[0]) {
            case "0" -> mode = GameMode.SURVIVAL;
            case "1" -> mode = GameMode.CREATIVE;
            case "2" -> mode = GameMode.ADVENTURE;
            case "3" -> mode = GameMode.SPECTATOR;
            default -> {
                player.sendMessage(msg("invalid-mode"));
                return true;
            }
        }

        if (blockedGamemodes.contains(mode.name().toLowerCase())) {
            player.sendMessage(msg("blocked-mode"));
            return true;
        }

        player.setGameMode(mode);
        player.sendMessage(msg("changed-mode").replace("%mode%", mode.name().toLowerCase()));
        return true;
    }
}
