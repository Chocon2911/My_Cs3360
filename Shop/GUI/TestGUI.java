package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI implements ActionListener
{
    JFrame frame;
    JButton button;
    JLabel clickNumbLabel;
    JPanel panel;
    int count = 0;

    public TestGUI()
    {
        this.frame = new JFrame();

        this.button = new JButton("Click");
        this.button.setPreferredSize(new Dimension(100, 50));
        this.button.addActionListener(this);

        this.clickNumbLabel = new JLabel("Hello");
        this.clickNumbLabel.setPreferredSize(new Dimension(200, 20));

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(200, 500, 200, 500));
        this.panel.setLayout(new GridLayout(0, 2));
        this.panel.add(this.button);
        this.panel.add(this.clickNumbLabel);

        this.frame.add(panel, BorderLayout.CENTER);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Our GUI");
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new TestGUI();

        JFrame frame = new JFrame("Text Input Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter text:");
        JTextField textField = new JTextField(15);
        JButton button = new JButton("Submit");

        // Thêm ActionListener cho nút
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText(); // Lấy văn bản từ JTextField
                System.out.println("User entered: " + inputText);
            }
        });

        // Thêm các thành phần vào panel
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        // Thêm panel vào frame
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.count++;
        this.clickNumbLabel.setText("Number of Clicks: " + count);
    }
}
