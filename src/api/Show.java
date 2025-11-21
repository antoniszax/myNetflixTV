package api;

import java.util.ArrayList;

/**
 * Αναπαριστάμία σειρά
 */
public class Show extends Production{
    /**
     * Λίστα που έχει τις σεζόν μίας σειράς
     */
    ArrayList<Season> seasons;

    /**
     * Κατασκευαστής ταινίας, δέχεται τα στοιχεία μίας παραγωγής και αυτά που
     *  τη διαφοροποιούν από μία σειρά δηλαδή διάρκεια σε λεπτα και έτος δημιουργίας
     * @param title
     * @param description
     * @param isAppropriate
     * @param genre
     * @param mainCharacters
     */
    public Show(String title, String description, boolean isAppropriate, String genre, String mainCharacters) {
        super(title, description, isAppropriate, genre, mainCharacters);
        //this.relatedShows = new ArrayList<>();
        this.seasons = new ArrayList<>();
    }

    /**
     * Μέθοδος που διαγράφει τη τελευταία σεζόν μίας σειράς
     */
    public void deleteLastSeason(){
        int a = seasons.size()-1;
        seasons.remove(a);
    }

    /**
     * Μέθοδος που επιστρέφει τη λίστα με τις σεζόν μίας ταινίας
     * @return
     */
    public ArrayList<Season> getSeasons(){return seasons;}

    /**
     * Μέθοδος που προσθέτει μία σεζόν ελέγχοντας πρώτα αν δεν υπάρχει
     * @param season
     */
    public void addSeason(Season season)
    {
        if(!seasons.contains(season))
            seasons.add(season);
    }

    /**
     * Μέθοδος για εμφάνιση σε gui της αντίστοιχης λέξης αν πρόκειται για μία σεζόν ή πολλές
     * @return
     */
    public String sdetails(){
        int a = seasons.size();
        String b = String.valueOf(a);
        if (a>0)
            if(a>1)
                return b.concat(" Seasons");
        return b.concat(" Season");
    }

    /**
     * Μέθοδος που δέχεται μία σεζόν και τη διαγράφει από τη λίστα με τις σεζόν
     * @param season
     */
    public void deleteSeason(Season season){
        seasons.remove(season);
    }

    /**
     * Μέθοδος που δέχεται τον αριθμό μίας σεζόν και την επιστρέφει
     * @param numberOfSeason
     * @return
     */
    public Season getSeason(int numberOfSeason)
    {
        if(numberOfSeason>0 && numberOfSeason<=seasons.size())
            for(Season s: seasons)
            {
                if(s.getNumberOfSeason()==numberOfSeason)
                {
                    return s;
                }

            }
        return null;
    }

}
