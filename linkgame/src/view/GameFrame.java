package view;

import controller.GameController;
import model.Tile;
import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {
    private String username;
    private GameController gameController;
    private BoardPanel boardPanel;
    private JLabel scoreLabel;
    private JLabel comboLabel;
    private JLabel timerLabel;
    private JButton skill1Btn;
    private JButton skill2Btn;
    private Timer gameTimer;
    private int secondsLeft;

    public GameFrame(String username, GameController gameController) {
        this.username = username;
        this.gameController = gameController;
        setTitle("Link Connect - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 信息面板
        JPanel infoPanel = new JPanel(new GridLayout(1, 5, 10, 0));
        scoreLabel = new JLabel("Score: 0");
        comboLabel = new JLabel("Combo: 0");
        timerLabel = new JLabel("Time: 120");
        skill1Btn = new JButton("Hint");
        skill2Btn = new JButton("Shuffle");
        infoPanel.add(scoreLabel);
        infoPanel.add(comboLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(skill1Btn);
        infoPanel.add(skill2Btn);
        add(infoPanel, BorderLayout.NORTH);

        // 棋盘面板
        boardPanel = new BoardPanel(gameController);
        add(boardPanel, BorderLayout.CENTER);

        // 计时器
        secondsLeft = gameController.getLevelTime();
        gameTimer = new Timer(1000, e -> {
            secondsLeft--;
            timerLabel.setText("Time: " + secondsLeft);
            if (secondsLeft <= 0) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(this, "Time's up! Game Over.");
                gameController.endGame();
                backToMenu();
            }
        });
        gameTimer.start();

        // 技能按钮
        skill1Btn.addActionListener(e -> {
            gameController.useSkill("hint");
            boardPanel.repaint();
        });
        skill2Btn.addActionListener(e -> {
            gameController.useSkill("shuffle");
            boardPanel.repaint();
        });

        // 窗口关闭时结束游戏
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                gameTimer.stop();
                gameController.pauseGame();
                // 可选择是否自动保存
            }
        });
    }

    private void backToMenu() {
        this.dispose();
        new MenuFrame(username).setVisible(true);
    }

    // 内部棋盘绘制面板
    class BoardPanel extends JPanel {
        private GameController controller;
        private int rows, cols;
        private int cellSize = 50;
        private Point selected = null;

        public BoardPanel(GameController controller) {
            this.controller = controller;
            this.rows = controller.getBoardRows();
            this.cols = controller.getBoardCols();
            setPreferredSize(new Dimension(cols * cellSize + 20, rows * cellSize + 20));
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = (e.getX() - 10) / cellSize;
                    int row = (e.getY() - 10) / cellSize;
                    if (row < 0 || row >= rows || col < 0 || col >= cols) return;
                    Tile tile = controller.getTileAt(row, col);
                    if (tile == null || tile.isRemoved()) return;
                    if (selected == null) {
                        selected = new Point(row, col);
                    } else {
                        boolean matched = controller.tryMatch(selected.x, selected.y, row, col);
                        if (matched) {
                            scoreLabel.setText("Score: " + controller.getScore());
                            comboLabel.setText("Combo: " + controller.getCombo());
                        }
                        selected = null;
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Board board = controller.getBoard();  // 改为 Board 对象
            if (board == null) return;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    int x = 10 + c * cellSize;
                    int y = 10 + r * cellSize;
                    Tile tile = board.getTile(r, c);  // 用 getTile 获取方块
                    if (tile != null && !tile.isRemoved()) {
                        g.setColor(getColorForTile(tile.getType()));
                        g.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
                        g.setColor(Color.BLACK);
                        g.drawRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
                        g.drawString(tile.getType() + "", x + 20, y + 30);
                    }
                    if (selected != null && selected.x == r && selected.y == c) {
                        g.setColor(Color.RED);
                        g.drawRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
                        g.drawRect(x, y, cellSize, cellSize);
                    }
                }
            }
        }

        private Color getColorForTile(int type) {
            Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
                    Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK};
            return colors[type % colors.length];
        }
    }
}