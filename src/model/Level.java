package model;
import java.io.Serializable;
public class Level implements Serializable {
    private static final long serialVersionUID = 1L;
    private int levelNumber;
    private int rows;
    private int cols;
    private int typeCount;
    private int timeLimit;
    private String ComputerDifficulty;
    private int hintCount;
    private int shuffleCount;
    private int raceCount;
    private int swapCount;
    public Level(int levelNumber, int rows, int cols, int typeCount, int timeLimit, String ComputerDifficulty, int hintCount, int shuffleCount, int raceCount, int swapCount) {
        this.levelNumber = levelNumber;
        this.rows = rows;
        this.cols = cols;
        this.typeCount = typeCount;
        this.timeLimit = timeLimit;
        this.ComputerDifficulty = ComputerDifficulty;
        this.hintCount = hintCount;
        this.shuffleCount = shuffleCount;
        this.raceCount = raceCount;
        this.swapCount = swapCount;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getComputerDifficulty() {
        return ComputerDifficulty;
    }

    public int getHintCount() {
        return hintCount;
    }

    public int getShuffleCount() {
        return shuffleCount;
    }

    public int getRaceCount() {
        return raceCount;
    }

    public int getSwapCount() {
        return swapCount;
    }
    public void setLevelNumber(int levelNumber) {
        if (levelNumber < 1) {
            return;
        }
        this.levelNumber = levelNumber;
    }
    public void setRows(int rows) {
        if (rows <= 0) {
            return;
        }
        this.rows = rows;
    }
    public void setCols(int cols) {
        if (cols <= 0) {
            return;
        }
        this.cols = cols;
    }
    public void setTypeCount(int typeCount) {
        if (typeCount <= 0) {
            return;
        }
        this.typeCount = typeCount;
    }
    public void setTimeLimit(int timeLimit) {
        if (timeLimit < 0) {
            return;
        }
        this.timeLimit = timeLimit;
    }
    public void setComputerDifficulty(String ComputerDifficulty) {
        if (ComputerDifficulty == null) {
            return;
        }
        this.ComputerDifficulty = ComputerDifficulty;
    }
    public void setHintCount(int hintCount) {
        if (hintCount < 0) {
            return;
        }
        this.hintCount = hintCount;
    }
    public void setShuffleCount(int shuffleCount) {
        if (shuffleCount < 0) {
            return;
        }
        this.shuffleCount = shuffleCount;
    }
    public void setRaceCount(int raceCount) {
        if (raceCount < 0) {
            return;
        }
        this.raceCount = raceCount;
    }
    public void setSwapCount(int swapCount) {
        if (swapCount < 0) {
            return;
        }
        this.swapCount = swapCount;
    }
    public static Level createLevel(int level) {

        switch (level) {

            case 1:
                return new Level(
                        1,
                        6,
                        6,
                        4,
                        300,
                        "easy",
                        5,
                        3,
                        1,
                        2
                );

            case 2:
                return new Level(
                        2,
                        8,
                        8,
                        6,
                        240,
                        "normal",
                        4,
                        2,
                        1,
                        2
                );

            case 3:
                return new Level(
                        3,
                        10,
                        10,
                        8,
                        180,
                        "hard",
                        3,
                        2,
                        1,
                        1
                );

            default:
                return new Level(
                        level,
                        10,
                        10,
                        8,
                        180,
                        "hard",
                        2,
                        1,
                        1,
                        1
                );
        }
    }
}
