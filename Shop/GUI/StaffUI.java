package GUI;

import HuySystem.HuyGuiUtil;
import Obj.Main.User.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffUI extends HuyGuiUtil
{
    private Staff staff;

    //========================================Constructor=========================================
    public StaffUI()
    {
        this.displayMain();
        staff = new Staff();
    }

    public StaffUI(Staff staff)
    {
        this.staff = staff;
    }

    //============================================Main============================================
    private void displayMain()
    {
        // frame
        JFrame frame = new JFrame("Staff Main Menu");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // informationButton
        JButton informationButton = createButton("Information", 200, 50);
        this.setAlignmentCenter(informationButton);
        informationButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayInformation();
            }
        });

        panel.add(informationButton);
        panel.add(Box.createVerticalStrut(20));

        // customerRequestButton
        JButton customerRequestButton = createButton("Customer Request", 200, 50);
        this.setAlignmentCenter(customerRequestButton);
        customerRequestButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayCustomerRequest();
            }
        });

        panel.add(customerRequestButton);
        panel.add(Box.createVerticalStrut(20));

        // checkButton
        JButton checkButton = createButton("Check In/Out", 200, 50);
        this.setAlignmentCenter(checkButton);
        checkButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayCheckInOut();
            }
        });

        panel.add(checkButton);
        panel.add(Box.createVerticalStrut(20));

        // quitButton
        JButton quitButton = createButton("Quit", 200, 50);
        this.setAlignmentCenter(quitButton);

        panel.add(quitButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(Box.createVerticalGlue());

        // mainFrame
        frame.add(panel);
        frame.setVisible(true);
    }

    //========================================Information=========================================
    private void displayInformation()
    {
        // frame
        JFrame frame = new JFrame("Staff Information Menu");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // searchButton
        JButton searchButton = createButton("Search By Date", 200, 50);
        this.setAlignmentCenter(searchButton);
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displaySearchInformation();
            }
        });

        panel.add(searchButton);
        panel.add(Box.createVerticalStrut(20));

        // privateInfoButton
        JButton privateInfoButton = createButton("Private Info", 200, 50);
        this.setAlignmentCenter(privateInfoButton);
        privateInfoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayPrivateInformation();
            }
        });

        panel.add(privateInfoButton);
        panel.add(Box.createVerticalStrut(20));

        // quitButton
        JButton quitButton = createComeBackMainMenuButton(frame, "Quit", 200, 50);
        this.setAlignmentCenter(quitButton);
        panel.add(quitButton);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void displaySearchInformation()
    {

    }

    private void displayPrivateInformation()
    {

    }

    //======================================Customer Request======================================
    private void displayCustomerRequest()
    {
        // frame
        JFrame frame = new JFrame("Customer Request Menu");
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // quitPanel
        JPanel quitPanel = new JPanel();
        quitPanel.setLayout(new BoxLayout(quitPanel, BoxLayout.Y_AXIS));

        // quitButton
        JButton quitButton = createComeBackMainMenuButton(frame, "Quit", 200, 50);
        quitPanel.add(quitButton);

        // customerReqPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // scrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        // customerRequests
//        int customerReqAmount = this.staff.getActiveShopSystem().getCustomerRequests().size();
        for (int i = 0; i < 10; i++)
        {
            // customerReqButton
            String customerReqId = "Button"; // this.staff.getActiveShopSystem().getCustomerRequests().get(i).getId();

            JButton customerReqButton = new JButton("Staff ID: " + customerReqId);
            this.setFixedSize(customerReqButton, 200, 50);
            this.setAlignmentCenter(customerReqButton);
            panel.add(customerReqButton);

            // totalPriceLabel
//            float customerReqTotalPrice = this.staff.getActiveShopSystem().getCustomerRequests().get(i).getTotalPrice();

            JLabel totalPriceLabel = new JLabel("Total Price: " + 1000f);
            this.setAlignmentCenter(totalPriceLabel);
            panel.add(totalPriceLabel);
            panel.add(Box.createVerticalStrut(20));
        }

        panel.add(Box.createVerticalGlue());

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(quitPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }

    //========================================Check In/Out========================================
    private void displayCheckInOut()
    {

    }

    //============================================Quit============================================
    private void displayQuit()
    {

    }

    //===========================================Other============================================
    private JButton createComeBackMainMenuButton(JFrame frame, String name, int width, int height)
    {
        JButton button = createButton(name, width, height);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayMain();
            }
        });

        return button;
    }

    //============================================Test============================================
    public static void main(String[] arg)
    {
        new StaffUI();
    }
}
