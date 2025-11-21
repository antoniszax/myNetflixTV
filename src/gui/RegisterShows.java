package gui;

import api.AdminSystem;
import api.Show;
import api.Season;
import api.Episode;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterShows extends JFrame {
    AdminSystem adminSystem;
    Show show;
    Season season;
    Episode episode;
    private static JLabel success;
    JLabel titleLabel, descriptionLabel, suitabilityLabel, categoryLabel, actorsLabel, seasonsLabel, messageLabel;
    JTextField titleField, seasonNumberField, seasonYearField, episodeNumberField, episodeDurationField;
    JTextArea descriptionArea, actorsField;
    JRadioButton suitabilityYes, suitabilityNo;
    ButtonGroup suitabilityGroup;
    JComboBox<String> categoryDropdown;
    JButton addShowButton, addSeasonButton, addEpisodeButton, submitButton;

    public RegisterShows(AdminSystem adminSystem) {
        this.adminSystem = adminSystem;
    }

    public void fillin(){
        success.setText("Fill all required fields");
    }

    public void approve(){
        success.setText("Register approved");
    }
    public void closeButton(){setVisible(false);}

    public void NewShow() {
        setTitle("Series form");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 50, 100, 30);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(160, 50, 200, 30);
        add(titleField);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 100, 100, 30);
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(160, 100, 200, 100);
        descriptionArea.setLineWrap(true);
        add(descriptionArea);

        suitabilityLabel = new JLabel("Appropriate:");
        suitabilityLabel.setBounds(50, 220, 100, 30);
        add(suitabilityLabel);

        suitabilityYes = new JRadioButton("Yes");
        suitabilityYes.setBounds(160, 220, 200, 30);
        suitabilityNo = new JRadioButton("No");
        suitabilityNo.setBounds(160, 250, 200, 30);
        suitabilityGroup = new ButtonGroup();
        suitabilityGroup.add(suitabilityYes);
        suitabilityGroup.add(suitabilityNo);
        add(suitabilityYes);
        add(suitabilityNo);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 300, 150, 30);
        add(categoryLabel);

        String[] categories = {"Horror", "Drama", "Sci-Fi", "Comedy", "Action"};
        categoryDropdown = new JComboBox<>(categories);
        categoryDropdown.setBounds(200, 300, 160, 30);
        add(categoryDropdown);

        actorsLabel = new JLabel("Protagonists:");
        actorsLabel.setBounds(50, 350, 100, 30);
        add(actorsLabel);

        actorsField = new JTextArea();
        actorsField.setBounds(160, 350, 200, 100);
        actorsField.setLineWrap(true);
        add(actorsField);

        seasonsLabel = new JLabel("Season:");
        seasonsLabel.setBounds(50, 470, 150, 30);
        add(seasonsLabel);

        JLabel seasonNumberLabel = new JLabel("Season's number:");
        seasonNumberLabel.setBounds(50, 500, 100, 30);
        add(seasonNumberLabel);

        seasonNumberField = new JTextField();
        seasonNumberField.setBounds(160, 500, 100, 30);
        add(seasonNumberField);

        JLabel seasonYearLabel = new JLabel("First screening:");
        seasonYearLabel.setBounds(50, 540, 100, 30);
        add(seasonYearLabel);

        seasonYearField = new JTextField();
        seasonYearField.setBounds(160, 540, 100, 30);
        add(seasonYearField);

        JLabel episodeNumberLabel = new JLabel("Episode's number:");
        episodeNumberLabel.setBounds(50, 580, 150, 30);
        add(episodeNumberLabel);

        episodeNumberField = new JTextField();
        episodeNumberField.setBounds(180, 580, 100, 30);
        add(episodeNumberField);

        JLabel episodeDurationLabel = new JLabel("Episode's duration:");
        episodeDurationLabel.setBounds(50, 620, 150, 30);
        add(episodeDurationLabel);

        episodeDurationField = new JTextField();
        episodeDurationField.setBounds(180, 620, 100, 30);
        add(episodeDurationField);

        addShowButton = new JButton("Add Show");
        addShowButton.setBounds(360, 420, 140, 30);
        add(addShowButton);

        addSeasonButton = new JButton("Add Season");
        addSeasonButton.setBounds(300, 500, 150, 30);
        add(addSeasonButton);

        addEpisodeButton = new JButton("Add Episode");
        addEpisodeButton.setBounds(300, 580, 180, 30);
        add(addEpisodeButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(160, 660, 100, 30);
        add(submitButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 690, 300, 30);
        add(messageLabel);

        addShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String descr = descriptionArea.getText();
                boolean isAppropriate = true;
                if (suitabilityYes.isSelected())
                    isAppropriate=true;
                else
                    isAppropriate=false;
                String kind = (String) categoryDropdown.getSelectedItem();
                String cast = actorsField.getText();
                if (title.equals("") || descr.equals("") || kind.equals("") || cast.equals("")) fillin();
                else{
                    show = new Show(title, descr, isAppropriate, kind, cast);
                    adminSystem.getDB().addShow(show);
                }
            }
        });
        addSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sNumber = seasonNumberField.getText();
                String sYear = seasonYearField.getText();
                int number = Integer.parseInt(sNumber);
                int year = Integer.parseInt(sYear);
                if(sNumber.equals("") || sYear.equals("")) fillin();
                else{
                    season = new Season(number, year);
                    show.addSeason(season);
                }
            }
        });
        addEpisodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String epNumber = episodeNumberField.getText();
                String epDur = episodeDurationField.getText();
                int epN = Integer.parseInt(epNumber);
                int epD = Integer.parseInt(epDur);
                if (epNumber.equals("") || epDur.equals("")) fillin();
                else{
                    episode = new Episode(epN, epD);
                    season.addEpisode(episode);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButton();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);



    }
}
