package view;

import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {
    public ChartFrame(String username) {
        setTitle("Link Connect - Player Statistics");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        MenuController controller = new MenuController();
        // 假设可以从用户服务获取统计信息
        int totalGames = controller.getTotalGames(username);
        int highScore = controller.getHighScore(username);
        double avgScore = controller.getAverageScore(username);

        JLabel gamesLabel = new JLabel("Total Games Played: " + totalGames);
        JLabel highLabel = new JLabel("Highest Score: " + highScore);
        JLabel avgLabel = new JLabel("Average Score: " + String.format("%.1f", avgScore));
        JButton backBtn = new JButton("Back");

        gamesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        highLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        avgLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        panel.add(gamesLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(highLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(avgLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(backBtn);

        backBtn.addActionListener(e -> dispose());

        add(panel);
    }
}