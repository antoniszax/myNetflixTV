package api;



/**
 *  κλαση που ευθυνεται για τις λειτουργιες που μπορει να κανει ενας χρηστης
 */
public class UserSystem {

    User user;
    Database database;
    public UserSystem(User user, Database database){
        this.user = user;
        this.database = database;
    }

    public Database getDB(){
        return database;
    }
    public User getUser(){
        return user;
    }
    public void deleteReview(Production p, Review r){
        p.getReviews().remove(r);
    }
    public Review editReview(Review r, String text, double score){
        /*1*/r.edit(text,score);
        return r;
    }
    public Review makeReview(Production p, User user, String text, double score){
        /*1*/Review review=new Review(user,text,score,user.getName());
        /*2*/user.addMyReviewed(p);
        /*3*/p.addReview(review);
        return review;
    }
}

