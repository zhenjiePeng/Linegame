package model;
import java.io.Serializable;
public class MatchRecord implements Serializable{
    private static final long serialVersionUID = 1L;
    private int row1;
    private int col1;
    private int row2;
    private int col2;
    private long timestamp;
    private int scoreAfter;
    private boolean skillUsed;
    private String skillName;
    private int comboAfter;
    //普通消除
    public MatchRecord(int row1, int col1, int row2, int col2, long timestamp, int scoreAfter, int comboAfter) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
        this.timestamp = timestamp;//操作时间戳（毫秒）
        this.scoreAfter = scoreAfter;
        this.comboAfter = comboAfter;
        this.skillUsed = false;
        this.skillName = "";
    }
    //技能记录
    public MatchRecord(String skillName, long timestamp, int scoreAfter, int comboAfter) {
        this.skillUsed = true;
        this.skillName = skillName;
        this.timestamp = timestamp;
        this.scoreAfter = scoreAfter;
        this.comboAfter = comboAfter;
    }
    public int getRow1() {
        return row1;
    }

    public int getCol1() {
        return col1;
    }

    public int getRow2() {
        return row2;
    }

    public int getCol2() {
        return col2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getScoreAfter() {
        return scoreAfter;
    }

    public int getComboAfter() {
        return comboAfter;
    }

    public boolean isSkillUsed() {
        return skillUsed;
    }

    public String getSkillName() {
        return skillName;
    }
    public void setTimestamp(long timestamp) {

        if (timestamp < 0) {
            return;
        }

        this.timestamp = timestamp;
    }

    public void setScoreAfter(int scoreAfter) {

        if (scoreAfter < 0) {
            return;
        }

        this.scoreAfter = scoreAfter;
    }

    public void setComboAfter(int comboAfter) {

        if (comboAfter < 0) {
            return;
        }

        this.comboAfter = comboAfter;
    }
    public boolean isMatchRecord() {

        return !skillUsed;
    }

    @Override
    public String toString() {

        if (skillUsed) {
            return "[Skill] "
                    + skillName
                    + " | Score: "
                    + scoreAfter
                    + " | Combo: "
                    + comboAfter;
        }
        return "(" + row1 + "," + col1 + ")"
                + " <-> "
                + "(" + row2 + "," + col2 + ")"
                + " | Score: "
                + scoreAfter
                + " | Combo: "
                + comboAfter;
    }
}
