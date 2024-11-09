package GUI.Test;

import javax.swing.*;
import java.awt.*;

public class GridLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridLayout Example");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GridLayout với một cột và khoảng cách dọc là 10 pixels
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 0, 10));

        // Thêm nhiều nút vào panel
        for (int i = 1; i <= 5; i++) {
            JButton button = new JButton("Button " + i);
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}
