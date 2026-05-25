package view;

import controller.MenuController;
import model.RankingEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RankingFrame extends JFrame {
    private String username;
    private MenuController menuController;

    public RankingFrame(String username) {
        this.username = username;
        menuController = new MenuController();
        setTitle("Link Connect - Rankings");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Rank", "Player", "Score", "Level"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        List<RankingEntry> rankings = menuController.getRankings();
        int rank = 1;
        for (RankingEntry entry : rankings) {
            model.addRow(new Object[]{rank++, entry.getPlayerName(), entry.getScore(), entry.getLevel()});
        }

        add(scrollPane, BorderLayout.CENTER);
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> dispose());
        add(backBtn, BorderLayout.SOUTH);
    }
}
