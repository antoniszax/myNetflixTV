package api;

import java.util.ArrayList;

/**
 * Κλάση που αναπαριστά μία ταινία
 */
public class Movie extends Production {




    private int dateOfRelease, duration;

    /**
     * Κατασκευαστής ταινίας, δέχεται τα στοιχεία μίας παραγωγής και αυτά που
     * τη διαφοροποιούν από μία σειρά δηλαδή διάρκεια σε λεπτα και έτος δημιουργίας
     * @param title
     * @param description
     * @param isAppropriate
     * @param dateOfRelease
     * @param duration
     * @param genre
     * @param mainCharacters
     */
    public Movie(String title, String description, boolean isAppropriate, int dateOfRelease, int duration, String genre, String mainCharacters)
    {
        super(title,description,isAppropriate,genre,mainCharacters);
        this.dateOfRelease = dateOfRelease;
        this.duration=duration;
    }

    /**
     * Setters and getters
     * @param d
     */
    public void setDateOfRelease(int d) {dateOfRelease=d;}




    public int getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDuration(int d){duration=d;}
    public int getDuration()
    {
        return duration;
    }



}
