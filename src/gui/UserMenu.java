package gui;
import api.Database;
import api.User;
import api.UserSystem;
import api.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Υλοποιείται για το menu επιλογών ενός User αφότου συνδεθεί στην εφαρμογή
 */
public class UserMenu extends JFrame {

    Database database;
    UserSystem userSystem;
    private JButton production, search, favorites,logout;

    public void closeButton(){
        setVisible(false);
    }
    public UserMenu(Database database, UserSystem userSystem){
        this.database = database;
        this.userSystem = userSystem;
    }

    /**
     * Μέθοδος που φτιάχνει το gui για της επιλογές των Users
     */

    public void makeUserMenu(){
        setTitle("User options");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300,300);

        JPanel panel = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Functions");
        panel.setBorder(border);
        GridLayout layout = new GridLayout(4,1);
        panel.setLayout(layout);

        production = new JButton("Productions");
        panel.add(production);
        search = new JButton("Search");
        panel.add(search);
        favorites = new JButton("Your favorites!");
        panel.add(favorites);
        logout = new JButton("Logout");
        panel.add(logout);



        production.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productions em = new Productions(userSystem);
                em.makeProductions();
            }
        });

        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {closeButton();}
        });

        favorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Favorites f = new Favorites(userSystem);
                f.makeF();
            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search s = new Search(userSystem);
                s.makeSearch();
            }
        };

        search.addActionListener(listener);

        add(panel,BorderLayout.CENTER);
        setVisible(true);
    }
}
