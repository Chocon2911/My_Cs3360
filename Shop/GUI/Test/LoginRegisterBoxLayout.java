package GUI.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterBoxLayout extends JFrame {
    public LoginRegisterBoxLayout() {
        // Thiết lập JFrame
        setTitle("Login Form with BoxLayout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Tạo JPanel chính với BoxLayout (theo chiều dọc)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Tạo các thành phần
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        // JPanel cho các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Khoảng cách giữa các nút
        buttonPanel.add(cancelButton);

        // Thêm các thành phần vào mainPanel
        mainPanel.add(createRow(usernameLabel, usernameField));
        mainPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các hàng
        mainPanel.add(createRow(passwordLabel, passwordField));
        mainPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa hàng và nút
        mainPanel.add(buttonPanel);

        // Thêm mainPanel vào JFrame
        add(mainPanel);

        // Action cho nút Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        // Action cho nút Cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText(""); // Clear username field
                passwordField.setText(""); // Clear password field
            }
        });

        // Hiển thị JFrame
        setVisible(true);
    }

    // Tạo một dòng với Label và TextField
    private JPanel createRow(JLabel label, JTextField field) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.add(label);
        row.add(Box.createHorizontalStrut(10)); // Khoảng cách giữa Label và TextField
        row.add(field);
        return row;
    }

    public static void main(String[] args) {
        // Tạo JFrame
        new LoginRegisterBoxLayout();
    }
}

