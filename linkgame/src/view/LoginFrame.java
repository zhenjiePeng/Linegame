package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private LoginController controller;

    public LoginFrame() {
        controller = new LoginController();
        setTitle("Link Connect - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Link Connect", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        JButton guestButton = new JButton("Play as Guest");
        gbc.gridy = 4;
        panel.add(guestButton, gbc);

        add(panel);

        // 事件绑定
        loginButton.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
                return;
            }
            boolean success = controller.login(user, pass);
            if (success) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                openMenu(user);
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Check credentials.");
            }
        });

        registerButton.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
                return;
            }
            boolean success = controller.register(user, pass);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful! Please login.");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Username may already exist.");
            }
        });

        guestButton.addActionListener(e -> {
            openMenu("Guest");
        });
    }

    private void openMenu(String username) {
        this.dispose();
        new MenuFrame(username).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
