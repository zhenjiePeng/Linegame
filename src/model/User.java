package model;
import java.io.Serializable;//用来实现内存中java对象可以被序列化和反序列化
public class User implements Serializable{
    private String username;   // 用户名
    private String password;   // 密码
    private boolean guest;     // 是否为游客
    // 战绩信息
    private int highScore;     // 历史最高分
    private int totalGames;    // 总局数
    private int wins;          // 胜利局数
    /*普通用户构造方法*/
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.guest = false;
    }
    //完整
    public User(String username, String password, boolean guest) {
        this.username = username;
        this.password = password;
        this.guest = guest;
    }
    //Getter&Setter
    public static User createGuest() {
        return new User("Guest", "", true);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkPassword(String inputPassword) {
        return password != null && password.equals(inputPassword);
    }

    public boolean isGuest() {
        return guest;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getWins() {
        return wins;
    }
    //更新一场游戏后的统计数据
    public void recordGame(int score, boolean win) {
        totalGames++;
        if (win) {
            wins++;
        }
        if (score > highScore) {
            highScore = score;
        }
    }
    //胜率（0.0 ~ 1.0）
    public double getWinRate() {
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) wins / totalGames;
    }
    @Override
    public String toString() {
        return username;
    }
}
