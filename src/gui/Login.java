package gui;
import api.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import api.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame {

    private Admin admin;
    private User user;

    private static JLabel usernameLabel;
    private static JTextField usernameText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton registerButton;
    private static JButton close;
    private static JLabel correct;

    public void approve(){
        correct.setText("Login approved!");
    }

    public void disapprove() {
        correct.setText("Login failed, try again!");
    }

    public void makeLogin(Database database){
        //create Frame
        setTitle("Login page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300,200);

        //make Panel
        JPanel panel = new JPanel();
        TitledBorder border = new TitledBorder("Login");
        panel.setBorder(border);
        GridLayout layout = new GridLayout(4, 2);
        panel.setLayout(layout);

        //username Label
        usernameLabel = new JLabel("Username");
        panel.add(usernameLabel);
        //username TextField
        usernameText = new JTextField(10);
        panel.add(usernameText);

        //password Label
        passwordLabel = new JLabel("Passsword");
        panel.add(passwordLabel);
        //password TextField;
        passwordText = new JPasswordField(10);
        panel.add(passwordText);

        //success Label
        correct = new JLabel("");
        correct.setBounds(10,110,300,25);
        panel.add(correct);

        //add panel to frame
        add(panel, BorderLayout.CENTER);

        //make panel for buttons
        JPanel panelButtons = new JPanel();

        //login Button
        loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        panelButtons.add(loginButton);

        //register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(100,210,200,20);
        registerButton.setForeground(Color.BLACK);
        registerButton.setBackground(Color.WHITE);
        panelButtons.add(registerButton);

        //add panel for buttons on frame
        add(panelButtons,BorderLayout.PAGE_END);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Login")){
                    String username = usernameText.getText();
                    String password = passwordText.getText();
                    Person person = database.login(username, password);
                    if (person==null) disapprove();
                    else {
                        approve();
                        if(person instanceof Admin){
                            admin = (Admin)person;
                            AdminSystem adminSystem = new AdminSystem(admin, database);
                            AdminMenu adminMenu = new AdminMenu(admin, adminSystem);
                            adminMenu.makeAdminMenu();
                        }
                        else{
                            user = (User)person;
                            UserSystem userSystem = new UserSystem(user, database);
                            UserMenu userMenu = new UserMenu(database, userSystem);
                            userMenu.makeUserMenu();
                        }
                    }
                }
                if (e.getActionCommand().equals("Register")){
                    Register register = new Register();
                    register.makeRegister(database);
                }
            }

        };

        loginButton.addActionListener(listener);
        registerButton.addActionListener(listener);

        //close Button
        close = new JButton("Terminate");
        panelButtons.add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.saveInFiles();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        Database database = new Database();
        Login L = new Login();
        L.makeLogin(database);
    }
}
