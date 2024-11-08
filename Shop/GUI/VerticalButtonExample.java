package GUI;

import javax.swing.*;

public class VerticalButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vertical Buttons Example");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout theo chiều dọc

        // Thêm nhiều nút vào panel
        for (int i = 1; i <= 5; i++) {
            JButton button = new JButton("Button " + i);
            panel.add(button);
            panel.add(Box.createVerticalStrut(10)); // Thêm khoảng cách giữa các nút
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}

