package api;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Κλάση που υλοποιεί μία κριτική που κάνει ένας χρήστης για μία ταινία-σειρά.
 */
public class Review implements Serializable {
    //user that makes the review
    private User user;
    private String text;
    private double score;
    private String firstName;

    //constructor for a new review
    public Review(User user,String text, double score, String firstName){
        if(text==null||firstName==null){
            return;
        }
        if(score<0||score>5){
            return;
        }
        this.user=user;
        this.text=text;
        this.score=score;
        this.firstName=firstName;
    }

    //set new review
    public void edit(String text, double score){
        this.text=text;
        this.score=score;
    }
    //return score
    public double getScore() {
        return score;
    }
    //return first name
    public String getFirstName(){
        return firstName;
    }
    //return text
    public String getText(){return text;}

    //return user
    public User getUser(){return user;}
}

