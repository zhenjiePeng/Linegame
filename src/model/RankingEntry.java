package model;
import java.io.Serializable;
import java.time.LocalDate;
public class RankingEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private int score;
    private int level;
    private int timeUsed;
    private String date;
    public RankingEntry(String username, int score, int level, int timeUsed) {
        this.username = username;
        this.score = score;
        this.level = level;
        this.timeUsed = timeUsed;
        // 自动记录当前日期
        this.date = LocalDate.now().toString();
    }
    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

    public String getDate() {
        return date;
    }
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            return;
        }
        this.username = username;
    }
    public void setScore(int score) {
        if (score < 0) {
            return;
        }
        this.score = score;
    }
    public void setLevel(int level) {
        if (level < 1) {
            return;
        }
        this.level = level;
    }
    public void setTimeUsed(int timeUsed) {
        if (timeUsed < 0) {
            return;
        }
        this.timeUsed = timeUsed;
    }
    public void setDate(String date) {
        if (date == null || date.isEmpty()) {
            return;
        }
        this.date = date;
    }
    public boolean betterThan(RankingEntry other) {
        if (other == null) {
            return true;
        }
        // 分数优先
        if (this.score > other.score) {
            return true;
        }
        // 同分比较时间
        if (this.score == other.score) {
            return this.timeUsed < other.timeUsed;
        }
        return false;
    }
    @Override
    public String toString() {
        return username +
                " | Score: " + score +
                " | Level: " + level +
                " | Time: " + timeUsed + "s" +
                " | Date: " + date;
    }
}
