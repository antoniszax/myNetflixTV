package gui;

import api.AdminSystem;
import api.Episode;
import api.Season;
import api.Show;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditShows extends JFrame {

    private JLabel success;
    JLabel titleLabel, descriptionLabel, suitabilityLabel, categoryLabel, actorsLabel, seasonsLabel, messageLabel;
    JTextField titleField, seasonNumberField, seasonYearField, episodeNumberField, episodeDurationField;
    JTextArea descriptionArea, actorsField;
    JRadioButton suitabilityYes, suitabilityNo;
    ButtonGroup suitabilityGroup;
    JComboBox<String> categoryDropdown;
    JButton addSeasonButton, deleteSeasonButton, addEpisodeButton, deleteEpisodeButton, submitButton, closeButton;
    AdminSystem adminSystem;
    Show show;
    Season season;
    Episode episode;

    public EditShows(Show show, AdminSystem adminSystem){
        this.show=show;
        this.adminSystem=adminSystem;
    }
    public void fillin(){
        success.setText("Fill in all required fields");
    }
    public void closeButton(){setVisible(false);}
    public void makeS(){
        setTitle("Edit Shows");
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

        addSeasonButton = new JButton("Add Season");
        addSeasonButton.setBounds(300, 500, 150, 30);
        add(addSeasonButton);
        deleteSeasonButton = new JButton("Delete Season");
        deleteSeasonButton.setBounds(300, 530,150,30);
        add(deleteSeasonButton);

        addEpisodeButton = new JButton("Add Episode");
        addEpisodeButton.setBounds(300, 580, 150, 30);
        add(addEpisodeButton);
        deleteEpisodeButton = new JButton("Delete Episode");
        deleteEpisodeButton.setBounds(300,610,150,30);
        add(deleteEpisodeButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(160, 660, 100, 30);
        add(submitButton);
        closeButton = new JButton("Close");
        closeButton.setBounds(50,660,100,30);
        add(closeButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 690, 300, 30);
        add(messageLabel);

        titleField.setText(show.getTitle());
        descriptionArea.setText(show.getDescription());
        actorsField.setText(show.getCast());
        if(show.getAppropriation())
            suitabilityYes.setSelected(true);
        else
            suitabilityNo.setSelected(true);
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

        deleteSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show.deleteLastSeason();
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

        deleteEpisodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                season.deleteLastEpisode();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButton();
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
