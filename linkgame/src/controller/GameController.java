package controller;

import model.Board;
import model.Tile;
import service.*;

public class GameController {
    private Board board;
    private MatchService matchService;
    private ScoreService scoreService;
    private ComboService comboService;
    private SkillService skillService;
    private String playerName;
    private int levelTime = 120; // 默认时间

    public GameController(int rows, int cols, int typeCount) {
        BoardGenerator generator = new BoardGenerator();
        this.board = generator.generateBoard(rows, cols, typeCount);
        this.matchService = new MatchService(board);
        this.scoreService = new ScoreService();
        this.comboService = new ComboService();
        this.skillService = new SkillService(board, matchService, scoreService, comboService);
    }

    public void setPlayerName(String name) { this.playerName = name; }
    public String getPlayerName() { return playerName; }

    // ----- GameFrame 需要的方法 -----
    public int getLevelTime() { return levelTime; }
    public void endGame() { /* 可添加结束逻辑 */ }
    public void pauseGame() { /* 可添加暂停逻辑 */ }

    public void useSkill(String skillName) {
        switch (skillName) {
            case "hint":
                skillService.hint();
                break;
            case "shuffle":
                skillService.shuffle();
                break;
            default:
                break;
        }
    }

    public int getBoardRows() { return board.getRows(); }
    public int getBoardCols() { return board.getCols(); }

    public Tile getTileAt(int row, int col) {
        return board.getTile(row, col);
    }

    public boolean tryMatch(int row1, int col1, int row2, int col2) {
        Tile a = board.getTile(row1, col1);
        Tile b = board.getTile(row2, col2);
        if (a == null || b == null) return false;

        boolean success = matchService.match(a, b);
        if (success) {
            scoreService.addMatchScore();
            int combo = comboService.onMatch();
            if (combo > 1) {
                // 连击额外加分
                scoreService.addScore(combo * 5);
            }
            // 检查竞速模式
            skillService.onRaceMatch();
        }
        return success;
    }

    public int getScore() { return scoreService.getScore(); }
    public int getCombo() { return comboService.getCombo(); }

    // ----- 原有方法保留（可继续使用） -----
    public boolean selectTile(int row, int col) {
        // 原有实现可保留，但 GameFrame 未直接调用，可忽略
        return false;
    }

    public boolean isGameOver() {
        return matchService.isGameFinished();
    }

    public Board getBoard() { return board; }

    public void shuffleBoard() {
        BoardGenerator generator = new BoardGenerator();
        board = generator.generateBoard(board.getRows(), board.getCols(), 4);
        matchService = new MatchService(board);
    }
}