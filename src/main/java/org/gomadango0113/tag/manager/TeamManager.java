package org.gomadango0113.tag.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.gomadango0113.tag.util.ChatUtil;

import java.util.*;

public class TeamManager {

    private static final PluginManager plm = Bukkit.getPluginManager();
    private static final Scoreboard board = ScoreboardManager.getScoreboard();

    private static Map<GameTeam, Team> team_map = new HashMap<>();
    private static Team oni_team = board.getTeam("oni");
    private static Team run_team = board.getTeam("run");

    static {
        createTeam();

        team_map.put(GameTeam.ONI, oni_team);
        team_map.put(GameTeam.RUN, run_team);
    }

    public static void createTeam() {
        if (oni_team == null || run_team == null) {
            oni_team = board.registerNewTeam("oni");
            run_team = board.registerNewTeam("run");

            NameTagVisibility hide_nametag = NameTagVisibility.HIDE_FOR_OTHER_TEAMS;
            for (Team team : new Team[]{oni_team, run_team}) {
                team.setNameTagVisibility(hide_nametag);
            }

            oni_team.setPrefix(ChatColor.RED + "");
            run_team.setPrefix(ChatColor.AQUA + "");

            Bukkit.getLogger().info("[Tag-TeamManager] チームを作成しました。");
        }
    }

    public static Set<String> getTeamOfflinePlayer(GameTeam gameteam) {
        Team team = team_map.get(gameteam);
        return team.getEntries();
    }

    public static Set<Player> getTeamPlayer(GameTeam gameteam) {
        Set<Player> online = new HashSet<>();
        Set<String> team = getTeamOfflinePlayer(gameteam);
        for (String offline : team) {
            OfflinePlayer offline_player = Bukkit.getOfflinePlayer(offline);
            if (offline_player.isOnline()) {
                online.add(Bukkit.getPlayer(offline));
            }
        }

        return online;
    }

    public static GameTeam getPlayerTeam(OfflinePlayer player) {
        if (getTeamOfflinePlayer(GameTeam.RUN).contains(player.getName())) {
            return GameTeam.RUN;
        }
        else if (getTeamOfflinePlayer(GameTeam.ONI).contains(player.getName())) {
            return GameTeam.ONI;
        }
        else {
            return GameTeam.UNKNOWN;
        }
    }

    public static void randomTeam(boolean rejoin) {
        if (oni_team != null && run_team != null) {
            List<Player> online_players = new ArrayList<>(Bukkit.getOnlinePlayers());
            if (!rejoin) {
                online_players.removeAll(getTeamPlayer(GameTeam.ONI));
                online_players.removeAll(getTeamPlayer(GameTeam.RUN));
            }
            Collections.shuffle(online_players);

            double RANDOM_PERCENT = 0.5;
            int RANDOM_SIZE = (int) (online_players.size() * RANDOM_PERCENT);
            for (int n = 0; n < RANDOM_SIZE; n++) {
                Player select_player = online_players.remove(0);
                ChatUtil.sendMessage(select_player, "あなたは鬼になりました。");
                oni_team.addEntry(select_player.getName());
            }

            online_players.forEach(online -> run_team.addEntry(online.getName()));
        }
    }

    public enum GameTeam {
        ONI,
        RUN,
        UNKNOWN
    }

}
