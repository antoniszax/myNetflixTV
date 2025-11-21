package gui;

import api.Review;
import api.UserSystem;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Hashtable;

public class EditReview extends JFrame {
    private UserSystem userSystem;
    private Review review;
    private double value;
    private static JLabel firstName;
    private static JLabel text;
    private static JLabel date;
    private static JLabel score;
    private static JTextField textTextField;
    private static JSlider slider;
    private static JButton done;
    private static JLabel success;
    public EditReview(UserSystem userSystem, Review review){
        this.userSystem=userSystem;
        this.review=review;
    }
    public void fillin(){
        success.setText("Fill in all required fields");
    }

    public void closeButton(){
        setVisible(false);
    }
    public void editReview(){
        //create JFrame
        setTitle("Edit review");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(350, 300);

        //make panel
        JPanel panel = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Review form");
        panel.setBorder(border);
        GridLayout layout = new GridLayout(6, 2);
        panel.setLayout(layout);

        //make labels
        firstName=new JLabel("First name: "+userSystem.getUser().getName());
        date=new JLabel("Date: "+String.valueOf(LocalDate.now()));
        text=new JLabel("Write a review: ");
        textTextField= new JTextField(10);
        textTextField.setText(review.getText());
        score=new JLabel("Score: ");
        panel.add(firstName);
        panel.add(date);
        panel.add(text);
        panel.add(textTextField);
        panel.add(score);

        //make slider
        slider=new JSlider(JSlider.HORIZONTAL,0,10, (int) (review.getScore()*2));
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        //Create the label table
        Hashtable labelTable = new Hashtable();
        labelTable.put( 0 , new JLabel("0") );
        labelTable.put( 2, new JLabel("1") );
        labelTable.put( 4, new JLabel("2") );
        labelTable.put( 6, new JLabel("3") );
        labelTable.put( 8, new JLabel("4") );
        labelTable.put( 10, new JLabel("5") );
        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);
        panel.add(slider);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value=slider.getValue()/2.0;
            }
        });

        //add panel to frame
        add(panel,BorderLayout.CENTER);

        //make panel2
        JPanel panel2=new JPanel();

        //make done button
        done=new JButton("done");
        panel2.add(done);
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textt = textTextField.getText();
                if(textt.equals(""))fillin();
                else {
                    userSystem.editReview(review,textt,value);
                    closeButton();
                }
            }
        });

        //add panel2 to frame
        add(panel2,BorderLayout.PAGE_END);

        setVisible(true);
    }
}

