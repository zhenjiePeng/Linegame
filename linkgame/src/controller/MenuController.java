package controller;

import model.RankingEntry;
import service.RankingService;

import java.util.List;

public class MenuController {
    private GameController gameController;
    private RankingService rankingService;

    public MenuController() {
        rankingService = new RankingService();
    }

    public void startNewGame(String username) {
        // 以默认参数创建新游戏
        gameController = new GameController(8, 8, 4);
        gameController.setPlayerName(username);
    }

    public GameController getGameController() {
        return gameController;
    }

    public void loadGame(String saveId) {
        // 简化实现：假设从 SaveLoadService 恢复 GameController
        // 由于 SaveLoadService 返回 GameData，这里可后续扩展
        // 此处先创建一个新游戏占位，防止空指针
        gameController = new GameController(8, 8, 4);
        gameController.setPlayerName("LoadedGame");
    }

    public List<RankingEntry> getRankings() {
        return rankingService.getTopRankings(10);
    }

    public void logout() {
        gameController = null;
    }

    // 为 ChartFrame 提供的方法（简单示例）
    public int getTotalGames(String username) { return 10; }
    public int getHighScore(String username) { return 1000; }
    public double getAverageScore(String username) { return 500.0; }
}