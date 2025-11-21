package gui;

import api.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Reviews extends JFrame {


    AdminSystem adminSystem;
    UserSystem userSystem;
    Production production;
    private static JLabel name;
    private static JLabel type;
    private static JLabel kind;
    private static JLabel description;
    private static JLabel facilities;
    private static JLabel facilities2;
    private static JLabel details;
    private static JLabel numOfReviews;
    private static JLabel avgScore;
    private static JButton close;
    private static JButton edit;
    private static JButton delete;
    private static JButton fave;
    private static JButton review;
    private static JLabel firstName;
    private static JLabel text;
    private static JLabel score;
    private static JPanel panel;
    private static JPanel panel2;
    private static JLabel empty;
    public Reviews(Production production, AdminSystem adminSystem){
        this.adminSystem = adminSystem;
        this.production = production;
    }

    public Reviews(Production production, UserSystem userSystem){
        this.production = production;
        this.userSystem = userSystem;
    }
    public void emptyLabel(){
        empty=new JLabel("You have no reviews");
        panel2.add(empty);
    }

    public void closeButton(){
        setVisible(false);
    }

    public void makeR(){
        //create JFrame
        setTitle("Production page");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridLayout(1, 1));

        //make panel1
        panel = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Information");
        panel.setLayout(new GridLayout(12,1));
        panel.setBorder(border);

        //make panel for reviews list
        panel2=new JPanel();
        TitledBorder border2 = BorderFactory.createTitledBorder("Reviews");

        //make main labels
        if(production instanceof Movie)
            type = new JLabel("MOVIE");
        else type = new JLabel("SHOW");
        name=new JLabel(production.getTitle());
        kind=new JLabel(production.getGenre());
        description=new JLabel(production.getDescription());
        panel.add(type);
        panel.add(name);
        panel.add(kind);
        panel.add(description);
        facilities2=new JLabel("Details");
        panel.add(facilities2);
        if(production instanceof Show) {
            details = new JLabel(((Show) production).sdetails());
            panel.add(details);
        }

        numOfReviews=new JLabel("Number of reviews: "+String.valueOf(production.getReviews().size()));
        avgScore=new JLabel("Avg score: "+String.valueOf(production.averageScore()));
        panel.add(numOfReviews);
        panel.add(avgScore);


        //for providers
        if(userSystem==null) {
            //add edit button
            edit = new JButton("edit");
            panel.add(edit);
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (production instanceof Movie) {
                        EditMovies em = new EditMovies((Movie)production, adminSystem);
                        em.makeMo();
                    }
                    else{
                        EditShows es = new EditShows((Show)production, adminSystem);
                        es.makeS();
                    }
                }
            });
            //add delete button
            delete = new JButton("delete");
            panel.add(delete);
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(production instanceof Movie)
                        adminSystem.getDB().deleteMovie((Movie)production);
                    else adminSystem.getDB().deleteShow((Show)production);
                    closeButton();
                }
            });
            //make labels for reviews
            for(Review r:production.getReviews()){
                User user = r.getUser();
                firstName=new JLabel("* "+user.getName()+": ");
                text=new JLabel("   "+r.getText());
                score=new JLabel("   Score: "+r.getScore());
                panel2.add(firstName);
                panel2.add(text);
                panel2.add(score);
            }
            //if there is no review
            if(production.getReviews().size()==0)emptyLabel();
        }

        //for users
        if(adminSystem==null){
            //add review button if user has not already made a review
            if(!userSystem.getUser().getMyReviewed().contains(production)) {
                fave = new JButton("Add to favorites");
                review = new JButton("review");
                panel.add(fave);
                panel.add(review);
                review.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MakeReview mr = new MakeReview(userSystem, production);
                        mr.makeReview();
                    }
                });

                fave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        userSystem.getUser().addToFavorites(production);
                    }
                });
            }
            //make labels for reviews
            for(Review r:production.getReviews()){
                firstName=new JLabel("* "+r.getFirstName()+": ");
                text=new JLabel("   "+r.getText());
                score=new JLabel("   Score: "+r.getScore());
                edit=new JButton("edit review");
                delete=new JButton("delete review");
                panel2.add(firstName);
                panel2.add(text);
                panel2.add(score);
                //compare based in username to place edit and delete buttons
                if(r.getUser().getUsername().equals(userSystem.getUser().getUsername())) {
                    panel2.add(edit);
                    edit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EditReview er=new EditReview(userSystem,r);
                            er.editReview();
                        }
                    });
                    panel2.add(delete);
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userSystem.deleteReview(production,r);
                            closeButton();
                        }
                    });
                }
            }
            //if there is no review
            if(production.getReviews().size()==0)emptyLabel();
        }

        //add close button
        close=new JButton("back");
        panel.add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {closeButton();}
        });

        //add panel to frame
        add(panel);

        //add panel2 to frame
        panel2.setLayout(new GridLayout(production.getReviews().size()*4,1));
        panel2.setBorder(border2);
        add(panel2, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }
}
