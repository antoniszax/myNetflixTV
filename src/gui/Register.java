package gui;
import api.Person;
import api.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Register extends JFrame {
    private static int radio;
    private static JLabel nameLabel;
    private static JLabel surnameLabel;
    private static JLabel usernameLabel;
    private static JLabel passwordLabel;
    private static JTextField nameText;
    private static JTextField surnameText;
    private static JTextField usernameText;
    private static JTextField passwordText;
    private static JButton registerButton;
    private static JLabel success;


    //close page
    public void closeButton(){
        this.setVisible(false);
    }
    //label when there are gaps
    public void fillin(){
        success.setText("Fill in the required fields!");
    }
    //register approved label
    public void approve(){
        success.setText("Register approved!");
    }
    //register failed label
    public void disapprove(){
        success.setText("Username already exists!");
    }
    //make register GUI
    public void makeRegister(Database database) {
        //create JFrame
        setTitle("Register Page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(350,250);

        //make panel
        JPanel panel =new JPanel();
        TitledBorder border=BorderFactory.createTitledBorder("Registration form");
        panel.setBorder(border);
        GridLayout layout=new GridLayout(6,2);
        panel.setLayout(layout);

        //name label
        nameLabel=new JLabel("Name");
        panel.add(nameLabel);
        //name textfield
        nameText=new JTextField(10);
        panel.add(nameText);

        //surname label
        surnameLabel=new JLabel("Surname");
        panel.add(surnameLabel);
        //surname textfield
        surnameText=new JTextField(10);
        panel.add(surnameText);

        //username label
        usernameLabel=new JLabel("Username");
        panel.add(usernameLabel);
        //username textfield
        usernameText=new JTextField(10);
        panel.add(usernameText);

        //password label
        passwordLabel=new JLabel("Password");
        panel.add(passwordLabel);
        //password textfield
        passwordText=new JTextField(10);
        panel.add(passwordText);


        //success label
        success=new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(success);

        //add panel to frame
        add(panel,BorderLayout.CENTER);

        //make panel for button
        JPanel panelButtons=new JPanel();

        //register button
        registerButton=new JButton("Register");
        registerButton.setBounds(100, 210, 200, 20);
        registerButton.setForeground(Color.BLACK);
        registerButton.setBackground(Color.WHITE);
        panelButtons.add(registerButton);

        //add panel for buttons on frame
        add(panelButtons,BorderLayout.PAGE_END);


        //action listener for the button
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Register")) {
                    String name = nameText.getText();
                    String surname = surnameText.getText();
                    String username = usernameText.getText();
                    String password = passwordText.getText();
                    if(nameText.getText().isEmpty()||surname.equals("")||username.equals("")||password.equals("")) fillin();
                    else {
                        Person p=database.register(name,surname,username,password);
                        if(p==null) disapprove();
                        else{
                            approve();
                            //close page and go back to login page
                            closeButton();
                        }
                    }
                }
            }
        };
        registerButton.addActionListener(listener);

        setVisible(true);
    }
}