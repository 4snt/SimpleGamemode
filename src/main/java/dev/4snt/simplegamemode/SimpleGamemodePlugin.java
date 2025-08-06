package dev.fndit.simplegamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SimpleGamemodePlugin extends JavaPlugin {

    private List<String> blockedGamemodes;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        blockedGamemodes = getConfig().getStringList("blocked-gamemodes");
        getLogger().info("SimpleGamemode ativo.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Somente jogadores podem usar esse comando.");
            return true;
        }

        if (!player.hasPermission("simplegamemode.use")) {
            player.sendMessage("§cVocê não tem permissão para usar esse comando.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso correto: /gm <0|1|2|3>");
            return true;
        }

        GameMode mode;
        switch (args[0]) {
            case "0" -> mode = GameMode.SURVIVAL;
            case "1" -> mode = GameMode.CREATIVE;
            case "2" -> mode = GameMode.ADVENTURE;
            case "3" -> mode = GameMode.SPECTATOR;
            default -> {
                player.sendMessage("§cModo inválido.");
                return true;
            }
        }

        if (blockedGamemodes.contains(mode.name().toLowerCase())) {
            player.sendMessage("§cEsse modo de jogo está bloqueado.");
            return true;
        }

        player.setGameMode(mode);
        player.sendMessage("§aModo de jogo alterado para §e" + mode.name().toLowerCase() + "§a.");
        return true;
    }
}
