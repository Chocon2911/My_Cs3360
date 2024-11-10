package GUI.Test;

import javax.swing.*;
import java.awt.*;

public class ScrollableButtonList {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Adjustable Scroll Speed Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        // Tạo JPanel chứa các nút và sắp xếp theo chiều dọc
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Thêm nhiều nút vào buttonPanel
        for (int i = 1; i <= 50; i++) {
            JButton button = new JButton("Button " + i);
            buttonPanel.add(button);
            buttonPanel.add(Box.createVerticalStrut(5)); // Thêm khoảng cách giữa các nút
        }

        // Đặt buttonPanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Điều chỉnh tốc độ lăn chuột
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Tăng giá trị để cuộn nhanh hơn

        // Thêm JScrollPane vào frame
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
