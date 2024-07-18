package com.tinyshellzz.permissionManager.command;

import com.tinyshellzz.permissionManager.ObjectPool;
import com.tinyshellzz.permissionManager.core.PermissionService;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermCommand  implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "参数不足");
            return true;
        }

        String subcommand = args[0].toLowerCase();
        switch(subcommand) {
            case "give":
                if (args.length < 3) {
                    sender.sendMessage(ChatColor.YELLOW + "用法: perm give <player> <permission>");
                    return true;
                }
                return PermissionService.give(args[1], args[2]);
            case "remove":
                if (args.length < 3) {
                    sender.sendMessage(ChatColor.YELLOW + "用法: perm remove <remove> <permission>");
                    return true;
                }
                return PermissionService.remove(args[1], args[2]);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            // 如果只有一个参数，返回所有子命令的列表
            return Arrays.asList("give", "remove");
        }  else {
            String subcommand = args[0].toLowerCase();
            switch (subcommand) {
                case "give":
                    return giveTab(sender, args);
                case "remove":
                    return removeTab(sender, args);
            }

            return null;
        }
    }

    public List<String> giveTab(CommandSender sender, String[] args) {
        if (args.length == 2) {
            ArrayList<String> ret = new ArrayList<>();
            OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
            for (OfflinePlayer _p : offlinePlayers) {
                Player p = _p.getPlayer();
                ret.add(p.getDisplayName());
            }
            return ret;
        }

        return null;
    }

    public List<String> removeTab(CommandSender sender, String[] args) {
        if (args.length == 2) {
            ArrayList<String> ret = new ArrayList<>();
            OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
            for (OfflinePlayer _p : offlinePlayers) {
                Player p = _p.getPlayer();
                ret.add(p.getDisplayName());
            }
            return ret;
        }

        return null;
    }
}