package GUI.Test;

import javax.swing.*;
import java.awt.*;

public class ScrollCursorExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scroll Cursor Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo JPanel chứa nhiều JButton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 5, 5)); // 1 cột, các nút cách nhau 5 pixel

        // Thêm nhiều JButton vào buttonPanel
        for (int i = 1; i <= 50; i++) { // Thay đổi số lượng nút theo nhu cầu
            JButton button = new JButton("Button " + i);
            buttonPanel.add(button);
        }

        // Đặt buttonPanel vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Luôn hiển thị thanh cuộn dọc
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        // Tùy chỉnh kích thước JScrollPane
        scrollPane.setPreferredSize(new Dimension(200, 400)); // Kích thước tùy chỉnh của JScrollPane

        // Thêm JScrollPane vào JFrame
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null); // Đặt JFrame vào giữa màn hình
        frame.setVisible(true);
    }
}
