package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSwitcher {
    public static void main(String[] args) {
        showFirstFrame();
    }

    // Hàm tạo và hiển thị JFrame đầu tiên
    public static void showFirstFrame() {
        JFrame firstFrame = new JFrame("First Frame");
        firstFrame.setSize(500, 200);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel firstPanel = new JPanel();
        JButton nextButton = new JButton("Next");

        // Thêm ActionListener cho nút "Next" trong JFrame đầu tiên
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFrame.dispose(); // Xoá JFrame đầu tiên
                showSecondFrame();    // Tạo và hiển thị JFrame thứ hai
            }
        });

        firstPanel.add(nextButton);
        firstFrame.add(firstPanel);
        firstFrame.setVisible(true);
    }

    // Hàm tạo và hiển thị JFrame thứ hai
    public static void showSecondFrame() {
        JFrame secondFrame = new JFrame("Second Frame");
        secondFrame.setSize(300, 200);
        secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel secondPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton aButton = new JButton("Back");
        JButton bButton = new JButton("Back");
        JButton cButton = new JButton("Back");

        // Thêm ActionListener cho nút "Back" trong JFrame thứ hai
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame.dispose(); // Xoá JFrame thứ hai
                showFirstFrame();      // Tạo và hiển thị lại JFrame đầu tiên
            }
        });

        secondPanel.add(backButton);
        secondPanel.add(aButton);
        secondPanel.add(bButton);
        secondPanel.add(cButton);
        secondFrame.add(secondPanel);
        secondFrame.setVisible(true);
    }
}


