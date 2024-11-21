package GUI.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterForm extends JFrame {
    public LoginRegisterForm() {
        // Thiết lập tiêu đề cho JFrame
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); // Sử dụng GridLayout

        // Các nhãn và text fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Các nút
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        // Thêm các thành phần vào JFrame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty cell for spacing
        add(new JLabel()); // Empty cell for spacing
        add(loginButton);
        add(cancelButton);

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

    public static void main(String[] args) {
        // Tạo JFrame
        new LoginRegisterForm();
    }
}
