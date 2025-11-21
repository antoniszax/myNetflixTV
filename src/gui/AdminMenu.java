package gui;
import api.Admin;
import api.AdminSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Υλοποιείται για το menu επιλογών ενός Admin αφότου συνδεθεί στην εφαρμογή
 */
public class AdminMenu extends JFrame {

    AdminSystem adminSystem;
    Admin admin;
    private static JButton newM;
    private static JButton newS;
    private static JButton production;
    private static JButton search;
    private static JButton logout;
    public AdminMenu(Admin admin, AdminSystem adminSystem){
        this.admin = admin;
        this.adminSystem = adminSystem;
    }

    public void closeButton(){
        this.setVisible(false);
    }

    /**
     * Μέθοδος που φτιάχνει το gui των επιλογών για Admins
     */
    public void makeAdminMenu(){
        setTitle("Admin options");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300,300);

        JPanel panel = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Functions");
        panel.setBorder(border);
        GridLayout layout = new GridLayout(4,1);
        panel.setLayout(layout);

        newM = new JButton("Add a movie");
        panel.add(newM);
        newS = new JButton("Add a show");
        panel.add(newS);
        production = new JButton("Productions");
        panel.add(production);
        search = new JButton("Search");
        panel.add(search);
        logout = new JButton("Logout");
        panel.add(logout);

        newM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterMovies rm = new RegisterMovies(adminSystem);
                rm.NewMovie();
            }
        });

        newS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterShows rs = new RegisterShows(adminSystem);
                rs.NewShow();
            }
        });

        production.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Productions em = new Productions(adminSystem);
                em.makeProductions();
            }
        });

        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {closeButton();
            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search s = new Search(adminSystem);
                s.makeSearch();
            }
        };

        search.addActionListener(listener);

        add(panel,BorderLayout.CENTER);
        setVisible(true);
    }
}
