package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
    public static void main(String[] args)
    {
        // Tạo frame
        JFrame frame = new JFrame("Comprehensive GUI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Panel trên cùng để nhập văn bản và chọn
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        // TextField để nhập tên
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        topPanel.add(nameLabel);
        topPanel.add(nameField);

        // ComboBox để chọn giới tính
        JLabel genderLabel = new JLabel("Gender:");
        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        topPanel.add(genderLabel);
        topPanel.add(genderComboBox);

        // CheckBox để chọn sở thích
        JCheckBox agreeCheckBox = new JCheckBox("I agree to the terms");
        topPanel.add(agreeCheckBox);

        frame.add(topPanel, BorderLayout.NORTH);

        // TextArea để hiển thị thông tin đầu ra
        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel dưới cùng chứa button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        // Button để thực hiện hành động
        JButton submitButton = new JButton("Submit");
        bottomPanel.add(submitButton);

        // Đăng ký sự kiện cho button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String gender = (String) genderComboBox.getSelectedItem();
                boolean agreed = agreeCheckBox.isSelected();

                // Kiểm tra đầu vào và hiển thị kết quả
                if (name.isEmpty()) {
                    outputArea.setText("Please enter your name.");
                } else if (!agreed) {
                    outputArea.setText("You must agree to the terms.");
                } else {
                    outputArea.setText("Name: " + name + "\nGender: " + gender + "\nAgreed to terms: " + agreed);
                }
            }
        });

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Hiển thị frame
        frame.setVisible(true);
    }
}
