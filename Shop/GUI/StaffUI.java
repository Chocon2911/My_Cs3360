package GUI;

import HuySystem.HuyGuiUtil;
import Obj.Main.User.Staff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffUI extends HuyGuiUtil
{
    private Staff staff;

    //============================================Test============================================
    public static void main(String[] arg)
    {
        new StaffUI();
    }

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
        JButton informationButton = new JButton("Information");
        this.setFixedSize(informationButton, 200, 50);
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
        JButton customerRequestButton = new JButton("Customer Request");
        this.setFixedSize(customerRequestButton, 200, 50);
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
        JButton checkButton = new JButton("Check In");
        this.setFixedSize(checkButton, 200, 50);
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
        JButton quitButton = new JButton("Quit");
        this.setFixedSize(quitButton, 200, 50);
        this.setAlignmentCenter(quitButton);

        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayQuit();
            }
        });

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
        JButton searchButton = new JButton("Search By Date");
        this.setFixedSize(searchButton, 200, 50);
        this.setAlignmentCenter(searchButton);
        panel.add(searchButton);
        panel.add(Box.createVerticalStrut(20));

        // privateInfoButton
        JButton privateInfoButton = new JButton("Private Info");
        this.setFixedSize(privateInfoButton, 200, 50);
        this.setAlignmentCenter(privateInfoButton);
        panel.add(privateInfoButton);
        panel.add(Box.createVerticalStrut(20));

        // quitButton
        JButton quitButton = new JButton("Quit");
        this.setFixedSize(quitButton, 200, 50);
        this.setAlignmentCenter(quitButton);

        quitButton.addActionListener(new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 frame.dispose();
                 displayMain();
             }
         });

        panel.add(quitButton);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }

    //======================================Customer Request======================================
    private void displayCustomerRequest()
    {
        // frame
        JFrame frame = new JFrame("Customer Request Menu");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // customerRequests
//        for (int customerReqIndex = 0; customerReqIndex < this.staff.getActiveShopSystem())
    }

    //========================================Check In/Out========================================
    private void displayCheckInOut()
    {

    }

    //============================================Quit============================================
    private void displayQuit()
    {

    }
}
