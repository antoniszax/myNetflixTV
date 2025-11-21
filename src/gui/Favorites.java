package gui;

import api.Movie;
import api.Show;
import api.Production;
import api.User;
import api.UserSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Favorites extends JFrame {
    UserSystem userSystem;
    Production production;
    private static JLabel title, descr, kind, cast, suitable, empty;
    private static JButton view,remove, closeButton;
    private static JPanel panel, panel2;
    public  Favorites(UserSystem userSystem){
        this.userSystem=userSystem;
    }

    public void closeButton(){
        setVisible(false);
    }
    public void emptyLabel(){
        empty=new JLabel("You have no registrations");
        panel.add(empty);
    }

    public void makeF(){
        setTitle("Favorites");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(new GridLayout(2,1));

        //make panel1
        panel = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Favorite Productions");
        panel.setBorder(border);

        panel2 = new JPanel();
        TitledBorder border2 = BorderFactory.createTitledBorder("Details");
        panel2.setBorder(border2);

        int k=0;

        if(userSystem.getUser().getFavorites().size()!=0) {
            for (Production a : userSystem.getUser().getFavorites()) {
                k++;
                title = new JLabel("    " + a.getTitle());
                descr = new JLabel("     " + a.getDescription());
                if (a.getAppropriation()) suitable = new JLabel("Appropriate");
                else suitable = new JLabel("Not Appropriate");
                kind = new JLabel("   "+a.getGenre());
                cast = new JLabel("     " + a.getCast());
                view=new JButton("view");
                remove = new JButton("remove");

                panel.add(title);
                panel.add(descr);
                panel.add(suitable);
                panel.add(kind);
                panel.add(cast);
                panel.add(view);
                panel.add(remove);
                view.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Reviews r = new Reviews(a, userSystem);
                        r.makeR();
                    }
                });

                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userSystem.getUser().removeFromFavorites(a);
                    }
                });
            }
        }

        //make close button and add on panel2
        closeButton=new JButton("back");
        panel2.add(closeButton);
        //action listener for close button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButton();
            }
        });
        GridLayout layout = new GridLayout(k,10);
        panel.setLayout(layout);
        if(k>=2)panel2.setLayout(layout);
        else panel2.setLayout(new GridLayout(2,1));

        //add panels
        add(panel);
        add(panel2);
        if(k==0){emptyLabel();}

        pack();
        setVisible(true);
    }
}
