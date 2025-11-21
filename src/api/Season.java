package api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Κλάση που αναπαριστά μία σεζόν
 */
public class Season implements Serializable {

    private int numberOfSeason, dateOfRelease;
    /**
     * Λίστα με τα επεισόδια μίας σεζόν
     */
    ArrayList<Episode> episodes;

    /**
     * Κατασκευαστής της σεζόν
     * @param numberOfSeasons ο αριθμός της σεζόν
     * @param dateOfRelease η ημερομηνία που προβλήθηκε πρώτη φορά
     */
    public Season(int numberOfSeasons, int dateOfRelease)
    {
        this.numberOfSeason= numberOfSeasons;
        this.dateOfRelease = dateOfRelease;
        episodes = new ArrayList<>();
    }

    /**
     * Setters and getters
     * @param numberOfSeason
     */
    public void setNumberOfSeason(int numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public void setDateOfRelease(int dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public int getDateOfRelease() {
        return dateOfRelease;
    }

    /**
     * Μέθοδος που επιστρέφει τη λίστα με τα επεισόδια μίας σεζόν
     * @return
     */
    public ArrayList<Episode> getEpisodes(){return episodes;}

    /**
     * Μέθοδος που προσθέτει ένα επεισόδιο στη λίστα με τα επεισόδια της σεζόν
     * @param episode
     */
    public void addEpisode(Episode episode)
    {
        episodes.add(episode);
    }

    /**
     * Μέθοδος που διαγράφει το τελευταίο επεισόδιο της σεζόν
     */
    public void deleteLastEpisode(){
        int a = episodes.size()-1;
        episodes.remove(a);
    }

    /**
     * Μέθοδος που δέχεται ένα επεισόδιο και το διαγράφει
     * @param episode
     */
    public void deleteEpisode(Episode episode){episodes.remove(episode);}

    /**
     * Μέθοδος που δέχεται τον αριθμό επεισοδίου και επιστρέφει το αντίστοιχο επεισόδιο
     * @param numberOfEpisode
     * @return
     */
    public Episode getEpisode(int numberOfEpisode)
    {
        for(Episode ep: episodes)
        {
            if(ep.getNumberOfEpisode()==numberOfEpisode)
                return ep;
        }
        return null;
    }


}
