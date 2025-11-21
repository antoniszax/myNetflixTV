package gui;
import api.AdminSystem;
import api.Production;
import api.UserSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Η κλάση που πραγματοποιεί το gui της αναζήτησης για χρήστη και πάροχο
 */
public class Search extends JFrame {
    private UserSystem userSystem;
    private AdminSystem adminSystem;
    private static JTextField titleField, actorField, ratingField;
    private static JLabel correct;
    private static JRadioButton appropriate, notAppropriate;
    private static JButton searchButton, closeButton;
    private static JComboBox genreComboBox;

    public Search(AdminSystem adminSystem){
        this.adminSystem = adminSystem;
    }
    public Search(UserSystem userSystem){
      this.userSystem = userSystem;
    }
    public void closeButton(){
        setVisible(false);
    }
    public void makeSearch(){
        setTitle("Movie/TV Series Search");
        setSize(400, 200);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        add(titleLabel);
        add(titleField);

        JLabel personLabel = new JLabel("Actor:");
        actorField = new JTextField();
        add(personLabel);
        add(actorField);

        JLabel leastRating = new JLabel("Least rating");
        ratingField = new JTextField();
        add(leastRating);
        add(ratingField);

        JLabel suitableLabel = new JLabel("Appropriate:");
        appropriate = new JRadioButton("Yes");
        notAppropriate = new JRadioButton("No");
        ButtonGroup suitableGroup = new ButtonGroup();
        suitableGroup.add(appropriate);
        suitableGroup.add(notAppropriate);
        JPanel suitablePanel = new JPanel(new GridLayout(1, 2));
        suitablePanel.add(appropriate);
        suitablePanel.add(notAppropriate);
        add(suitableLabel);
        add(suitablePanel);

        JLabel genreLabel = new JLabel("Genre:");
        String[] genres = {"Horror", "Drama", "Sci-Fi", "Action", "Comedy"};
        genreComboBox = new JComboBox<>(genres);
        add(genreLabel);
        add(genreComboBox);

        closeButton = new JButton("Close");
        JButton searchButton = new JButton("Search");

        add(searchButton);
        add(closeButton);

        correct = new JLabel("");
        correct.setBounds(10,110,300,25);
        add(correct);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButton();
            }
        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String title = titleField.getText();
              String actor = actorField.getText();
              boolean appr = appropriate.isSelected();
              String genre = (String)genreComboBox.getSelectedItem();
              String ra = ratingField.getText();
              Double r;
              if(ra.equals("")) r=0.0;
              else r = Double.parseDouble(ra);
              ArrayList<Production> p = new ArrayList<>();
              if(userSystem == null) {
                  p = adminSystem.getDB().search(title, actor, appr, genre, r, adminSystem.getDB());
                  if(p!=null) {
                      SearchResults sr = new SearchResults(adminSystem);
                      sr.makeResults(p);
                  }
                  else correct.setText("No results found");
              }
              else {
                  p=userSystem.getDB().search(title,actor, appr, genre, r, userSystem.getDB());
                  if(p!=null) {
                      SearchResults sr = new SearchResults(userSystem);
                      sr.makeResults(p);
                  }
                  else correct.setText("No results found");
              }
            }
        });


        setVisible(true);
    }
}
