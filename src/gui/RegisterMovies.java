package gui;

import api.AdminSystem;
import api.Movie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterMovies extends JFrame {

    AdminSystem adminSystem;
    private static JLabel success;
    public RegisterMovies(AdminSystem adminSystem){
        this.adminSystem = adminSystem;
    }

    public void fillin(){success.setText("Fill in all required fields");}

    public void approve(){success.setText("Register approved");}
    public void closeButton(){setVisible(false);}
    public void NewMovie(){

        JLabel titleLabel, descriptionLabel, suitabilityLabel, yearLabel, durationLabel, categoryLabel, actorsLabel;
        JTextField titleField, yearField, durationField, actorsField;
        JTextArea descriptionArea;
        JRadioButton isAppropriate, isNotAppropriate;
        ButtonGroup suitabilityGroup;
        JComboBox<String> categoryDropdown;
        JButton submitButton;


        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 50, 100, 30);
        titleField = new JTextField();
        titleField.setBounds(160, 50, 200, 30);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 100, 100, 30);descriptionArea = new JTextArea();
        descriptionArea.setBounds(160, 100, 200, 100);
        descriptionArea.setLineWrap(true);

        suitabilityLabel = new JLabel("Appropriate:");
        suitabilityLabel.setBounds(50, 220, 100, 30);
        isAppropriate = new JRadioButton("Yes");
        isAppropriate.setBounds(160, 220, 80, 30);
        isNotAppropriate = new JRadioButton("No");
        isNotAppropriate.setBounds(250, 220, 80, 30);
        suitabilityGroup = new ButtonGroup();
        suitabilityGroup.add(isAppropriate);
        suitabilityGroup.add(isNotAppropriate);
        isAppropriate.setSelected(true);

        yearLabel = new JLabel("First screening:");
        yearLabel.setBounds(50, 270, 150, 30);
        yearField = new JTextField();
        yearField.setBounds(210, 270, 150, 30);

        durationLabel = new JLabel("Duration (min):");
        durationLabel.setBounds(50, 320, 100, 30);
        durationField = new JTextField();
        durationField.setBounds(160, 320, 200, 30);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 370, 150, 30);
        String[] categories = {"Horror", "Drama", "Sci-Fi", "Comedy", "Action"};
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.setBounds(200, 370, 160, 30);

        actorsLabel = new JLabel("Protagonists:");
        actorsLabel.setBounds(50, 420, 100, 30);
        actorsField = new JTextField();
        actorsField.setBounds(160, 420, 200, 30);


        submitButton = new JButton("Submit");
        submitButton.setBounds(160, 470, 100, 30);
        //submitButton.addActionListener(this);
        success=new JLabel("");
        success.setBounds(10,110,300,25);

        add(titleLabel);
        add(titleField);
        add(descriptionLabel);
        add(descriptionArea);
        add(suitabilityLabel);
        add(isAppropriate);
        add(isNotAppropriate);
        add(yearLabel);
        add(yearField);
        add(durationLabel);
        add(durationField);
        add(categoryLabel);
        add(success);
        add(categoryDropdown);
        add(actorsLabel);
        add(actorsField);
        add(submitButton);

        setTitle("Movie Form");
        setSize(400, 550);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String descr = descriptionArea.getText();
                Boolean suitable = true;
                if(isAppropriate.isSelected())
                    suitable = true;
                else
                    suitable = false;
                String date = yearField.getText();
                int year = Integer.parseInt(date);
                String dur = durationField.getText();
                int duration = Integer.parseInt(dur);
                String kind = (String) categoryDropdown.getSelectedItem();
                String actors = actorsField.getText();
                if (title.equals("") || descr.equals("") || date.equals("") || dur.equals("") || actors.equals("")) fillin();
                else{
                    Movie m = new Movie(title, descr, suitable, year, duration, kind, actors);
                    adminSystem.getDB().addMovie(m);
                    closeButton();
                }
            }
        });



    }
}