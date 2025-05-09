package org.gomadango0113.tag.manager;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.gomadango0113.tag.TagPlugin;
import org.gomadango0113.tag.util.ChatUtil;

public class GameManager {

    private static GameStatus status;
    private static int GAME_TIME;
    private static BukkitTask task;

    static {
        status = GameStatus.WAITING;
        GAME_TIME = 60*15;
    }

    public static int startGame() {
        if (status == GameStatus.WAITING && task == null) {
            final int[] count_time = {10};

            task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (status == GameStatus.WAITING || status == GameStatus.COUNTING) {
                        if (count_time[0] == 0) {
                            ChatUtil.sendGlobalMessage("ゲーム開始!!");
                            ScoreboardManager.setScoreboard(1);
                            TeamManager.randomTeam(false);
                            status = GameStatus.RUNNING;
                        }
                        else {
                            status = GameStatus.COUNTING;
                            String count_format = String.format("ゲーム開始まであと%d秒", count_time[0]);
                            ChatUtil.sendGlobalMessage(count_format);
                            count_time[0]--;
                        }
                    }
                    else if (status == GameStatus.RUNNING) {

                        if (GAME_TIME == 0) {
                            ChatUtil.sendGlobalMessage("ゲーム終了!!");
                            this.cancel();
                        }

                        GAME_TIME--;
                    }
                }
            }.runTaskTimer(TagPlugin.getInstance(), 0L, 20L);

            return 0;
        }

        return 1;
    }

    public static GameStatus getStatus() {
        return status;
    }

    public static int getTime() {
        return GAME_TIME;
    }

    public static void setTime(int time) {
        GAME_TIME = time;
    }

    public enum GameStatus {
        WAITING,
        RUNNING,
        COUNTING,
        ENDING,
    }
}
