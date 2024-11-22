package GUI;

import DataBase.DataBaseCtrl;
import HuySystem.HuyGuiUtil;
import Obj.Main.Shop;
import Obj.Main.User;
import Obj.Side.ActiveShop;
import Obj.Side.CustomerRequest;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App2UI extends HuyGuiUtil
{
    private DataBaseCtrl dataBase;

    public App2UI()
    {
        dataBase = new DataBaseCtrl();
        this.displayMain();
    }



    //============================================================================================
    //==========================================Main UI===========================================
    //============================================================================================
    private void displayMain()
    {
        // Frame
        JFrame frame = new JFrame("App2 Main");
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        // Title Label
        JLabel titleLabel = new JLabel("App2");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(50));

        // Login Button
        JButton loginButton = createButton("Login", 200, 50);
        this.setAlignmentCenter(loginButton);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayLogin();
            }
        });
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(20));

        // Sign Up Button
        JButton registerButton = createButton("Register", 200, 50);
        this.setAlignmentCenter(registerButton);
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayRegister();
            }
        });
        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(20));

        // Quit Button
        JButton quitButton = createButton("Quit", 200, 50);
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
        panel.add(Box.createVerticalGlue());

        // Frame
        frame.add(panel);
        frame.setVisible(true);
    }



    //============================================================================================
    //==========================================Login UI==========================================
    //============================================================================================
    // Display
    private void displayLogin()
    {
        // ===Frame===
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // ===Panel===
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        // ===Title Label===
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        this.setAlignmentCenter(titleLabel);



        // ===ID Panel===
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        this.setFixedSize(idPanel, 300, 25);
        idPanel.add(Box.createHorizontalGlue());

        // ID Label
        JLabel idLabel = new JLabel("ID:");
        this.setFixedSize(idLabel, 100, 20);
        idPanel.add(idLabel);
        idPanel.add(Box.createVerticalStrut(20));

        // ID Text Field
        JTextField idField = new JTextField(20);
        this.setFixedSize(idField, 200, 25);
        idPanel.add(idField);
        idPanel.add(Box.createHorizontalGlue());



        // ===Password Panel===
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        this.setFixedSize(passwordPanel, 300, 25);
        passwordPanel.add(Box.createHorizontalGlue());

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        this.setFixedSize(passwordLabel, 100, 20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createVerticalStrut(20));

        // Password Text Field
        JPasswordField passwordField = new JPasswordField(20);
        this.setFixedSize(passwordField, 200, 25);
        passwordPanel.add(passwordField);
        passwordPanel.add(Box.createHorizontalGlue());



        // ===Button Panel===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        this.setFixedSize(buttonPanel, 300, 25);
        buttonPanel.add(Box.createHorizontalGlue());

        // Cancel Button
        JButton cancelButton = createButton("Cancel", 100, 25);
        this.setAlignmentCenter(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayMain();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(20));

        // Login Button
        JButton loginButton = createButton("Login", 100, 25);
        this.setAlignmentCenter(loginButton);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String id = idField.getText();
                String password = String.valueOf(passwordField.getPassword());

                System.out.println(id + " - " + password);
                ActiveShop activeShop = createActiveShop(id, password);
                if (activeShop == null)
                {
                    JOptionPane.showMessageDialog(null, "Invalid Id or Password!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    // TODO: Get into ActiveShop Menu
                }
            }
        });
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createHorizontalGlue());


        // ===Display===
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(idPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(passwordPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }

    // Other
    private ActiveShop createActiveShop(String shopId, String shopPassword)
    {
        List<Shop> shops = dataBase.queryAllShopData();
        for (Shop shop : shops)
        {
            if (!shopId.equals(shop.getId()) || !shopPassword.equals(shop.getPassword())) continue;

            String randId = getRandomStr(8);
            List<CustomerRequest> customerRequests = new ArrayList<CustomerRequest>();
            List<User> users = new ArrayList<User>();

            return new ActiveShop(randId, shop, customerRequests, users);
        }

        return null;
    }



    //============================================================================================
    //========================================Register UI=========================================
    //============================================================================================

    //==========================================Display===========================================
    private void displayRegister()
    {
        // ===Frame===
        JFrame frame = new JFrame("Register");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // ===Panel===
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));



        // ===Title Label===
        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        this.setAlignmentCenter(titleLabel);



        // ===SystemCode Panel===
        JPanel systemCodePanel = new JPanel();
        systemCodePanel.setLayout(new BoxLayout(systemCodePanel, BoxLayout.X_AXIS));
        this.setFixedSize(systemCodePanel, 300, 25);
        systemCodePanel.add(Box.createHorizontalGlue());

        // SystemCode Label
        JLabel systemCodeLabel = new JLabel("System Code:");
        this.setFixedSize(systemCodeLabel, 100, 20);
        systemCodePanel.add(systemCodeLabel);
        systemCodePanel.add(Box.createVerticalStrut(20));

        // SystemCode Text Field
        JTextField systemCodeField = new JTextField(20);
        this.setFixedSize(systemCodeField, 200, 25);
        systemCodePanel.add(systemCodeField);
        systemCodePanel.add(Box.createHorizontalGlue());



        // ===Name Panel===
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        this.setFixedSize(namePanel, 300, 25);
        namePanel.add(Box.createHorizontalGlue());

        // Name Label
        JLabel nameLabel = new JLabel("Name");
        this.setFixedSize(nameLabel, 100, 20);
        namePanel.add(nameLabel);
        namePanel.add(Box.createVerticalStrut(20));

        // Name Text Field
        JTextField nameField = new JTextField(20);
        this.setFixedSize(nameField, 200, 25);
        namePanel.add(nameField);
        namePanel.add(Box.createHorizontalGlue());



        // ===Password Panel===
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        this.setFixedSize(passwordPanel, 300, 25);
        passwordPanel.add(Box.createHorizontalGlue());

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        this.setFixedSize(passwordLabel, 100, 20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createVerticalStrut(20));

        // Password Text Field
        JPasswordField passwordField = new JPasswordField(20);
        this.setFixedSize(passwordField, 200, 25);
        passwordPanel.add(passwordField);
        passwordPanel.add(Box.createHorizontalGlue());



        // ===Button Panel===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        this.setFixedSize(buttonPanel, 300, 25);
        buttonPanel.add(Box.createHorizontalGlue());

        // Cancel Button
        JButton cancelButton = createButton("Cancel", 100, 25);
        this.setAlignmentCenter(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                displayMain();
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalStrut(20));

        // Register Button
        JButton registerButton = createButton("Register", 100, 25);
        this.setAlignmentCenter(registerButton);
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String systemCode = systemCodeField.getText();
                String name = nameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                System.out.println("System Code: " + systemCode);
                System.out.println("Name: " + name);
                System.out.println("Password: " + password);

                if (systemCode.isEmpty() || name.isEmpty() || password.isEmpty())
                {
                    System.out.println("Text Field is Empty");
                    JOptionPane.showMessageDialog(null, "Text Field is Empty");
                }

                else
                {
                    Shop newShop = createShop(name, password, systemCode);
                }
            }
        });
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalGlue());



        // ===Display===
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(systemCodePanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(namePanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(passwordPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }

    //===========================================Other============================================
    private Shop createShop(String name, String password, String SystemCode)
    {
        String id = this.getRandomStr(8);
        Shop newShop = new Shop(id, name, password, SystemCode, this.dataBase);

        this.dataBase.insertShopData(newShop);
        return newShop;
    }



    //============================================================================================
    //==========================================Quit UI===========================================
    //============================================================================================
    private void displayQuit()
    {

    }



    //============================================================================================
    //============================================Test============================================
    //============================================================================================
    public static void main(String[] args)
    {
        new App2UI();
    }
}
