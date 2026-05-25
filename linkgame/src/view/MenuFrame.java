package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private String username;
    private MenuController controller;

    public MenuFrame(String username) {
        this.username = username;
        controller = new MenuController();
        setTitle("Link Connect - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel welcome = new JLabel("Welcome, " + username + "!");
        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(welcome);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JButton newGameBtn = createButton("New Game");
        JButton loadGameBtn = createButton("Load Game");
        JButton battleBtn = createButton("Network Battle");
        JButton rankingBtn = createButton("Rankings");
        JButton chartBtn = createButton("Statistics");
        JButton logoutBtn = createButton("Logout");

        panel.add(newGameBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(loadGameBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(battleBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(rankingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(chartBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(logoutBtn);

        add(panel);

        // 事件处理
        newGameBtn.addActionListener(e -> {
            controller.startNewGame(username);
            this.dispose();
            // GameFrame 由核心逻辑部分的 GameController 配合创建
            // 此处通过接口调用，假设 GameFrame 构造需要 GameController
            // 我们会让 MenuController 返回一个 GameController 实例
            GameFrame gameFrame = new GameFrame(username, controller.getGameController());
            gameFrame.setVisible(true);
        });

        loadGameBtn.addActionListener(e -> {
            String saveId = JOptionPane.showInputDialog(this, "Enter save ID:");
            if (saveId != null && !saveId.trim().isEmpty()) {
                controller.loadGame(saveId.trim());
                // 加载成功则打开 GameFrame
                GameFrame gameFrame = new GameFrame(username, controller.getGameController());
                gameFrame.setVisible(true);
                this.dispose();
            }
        });

        battleBtn.addActionListener(e -> {
            // 打开对战界面
            BattleFrame battleFrame = new BattleFrame(username);
            battleFrame.setVisible(true);
            this.dispose();
        });

        rankingBtn.addActionListener(e -> {
            new RankingFrame(username).setVisible(true);
        });

        chartBtn.addActionListener(e -> {
            new ChartFrame(username).setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            controller.logout();
            this.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setMaximumSize(new Dimension(250, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}