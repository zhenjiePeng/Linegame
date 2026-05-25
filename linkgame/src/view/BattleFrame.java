package view;

import javax.swing.*;
import java.awt.*;

public class BattleFrame extends JFrame {
    private String username;

    public BattleFrame(String username) {
        this.username = username;
        setTitle("Link Connect - Network Battle");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel status = new JLabel("Waiting for opponent...", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 18));
        add(status, BorderLayout.CENTER);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> {
            dispose();
            new MenuFrame(username).setVisible(true);
        });
        add(cancelBtn, BorderLayout.SOUTH);

        // 实际网络对战逻辑由 BattleController 驱动
        // 此处仅为占位界面，连接建立后可通过回调更新
    }
}