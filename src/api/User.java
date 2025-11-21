package api;

import java.util.ArrayList;

/**
 * Κλάση που υλοποιεί έναν User
 */
public class User extends Person{

    /**
     * Λίστα με τα αγαπημένα του χρήστη
     */
    ArrayList<Production> favorites;

    /**
     * Λίστα με τις ταινίες-σειρές που έχει αφήσει κριτική ο χρήστης
     */
    ArrayList<Production> myReviewed;
    public User(String name, String surname, String username, String password){
        super(name, surname, username, password);
        favorites = new ArrayList<>();
        myReviewed = new ArrayList<>();
    }

    public void addToFavorites(Production p){
        favorites.add(p);
    }
    public void removeFromFavorites(Production p){
        favorites.remove(p);
    }

    public void addMyReviewed(Production p){
        myReviewed.add(p);
    }
    public ArrayList<Production> getFavorites(){
        return favorites;
    }
    public ArrayList<Production> getMyReviewed(){return myReviewed;}
}


