package model;
import java.io.Serializable;
public class GameState implements Serializable {
    private static final long serialVersionUID=1L;//版本号，装饰用
    private boolean gameOver;
    private boolean paused;
    private boolean win;
    private boolean surrendered;
    private int level;
    private int timeLeft;
    private String mode;
    public GameState() {
        gameOver = false;
        paused = false;
        win = false;
        surrendered = false;
        level = 1;
        timeLeft = 300;
        mode = "single";
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isSurrendered() {
        return surrendered;
    }

    public int getLevel() {
        return level;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getMode() {
        return mode;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setSurrendered(boolean surrendered) {
        this.surrendered = surrendered;
    }

    public void setLevel(int level) {

        if (level < 1) {
            return;
        }

        this.level = level;
    }

    public void setTimeLeft(int timeLeft) {

        if (timeLeft < 0) {
            this.timeLeft = 0;
            return;
        }

        this.timeLeft = timeLeft;
    }

    public void setMode(String mode) {

        if (mode == null) {
            return;
        }

        this.mode = mode;
    }
    public void decreaseTime(int seconds){
        if(seconds<0){
            return;
        }
        if(timeLeft<=0){
            timeLeft=0;
            gameOver=true;
        }
    }
    public void winGame() {
        win = true;
        gameOver = true;
    }
    public void surrender() {
        surrendered = true;
        gameOver = true;
    }
    public void pause() {
        paused = true;
    }
    public void resume() {
        paused = false;
    }
    public void reset() {
        gameOver = false;
        paused = false;
        win = false;
        surrendered = false;
        level = 1;
        timeLeft = 300;
        mode = "single";
    }
}
