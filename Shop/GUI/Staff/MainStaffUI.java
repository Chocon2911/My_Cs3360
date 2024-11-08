package GUI.Staff;

import Obj.Main.Staff;

import javax.swing.*;

public class MainStaffUI
{
    private Staff staff;

    //========================================Constructor=========================================
    public MainStaffUI()
    {
        staff = new Staff();
    }

    public MainStaffUI(Staff staff)
    {
        this.staff = staff;
    }

    //=============================================UI=============================================
    private void displayUI()
    {
        JFrame mainFrame = new JFrame("Staff Main Menu");
        mainFrame.setSize(500, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

    }
}
