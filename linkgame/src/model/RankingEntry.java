package model;

import java.io.Serializable;

public class RankingEntry implements Serializable {
    private String playerName;
    private int score;
    private int level;

    public RankingEntry(String playerName, int score, int level) {
        this.playerName = playerName;
        this.score = score;
        this.level = level;
    }

    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
}