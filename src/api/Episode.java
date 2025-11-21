package api;

import java.io.Serializable;

/**
 * Κλάση που υλοποιεί ένα επεισόδιο
 */
public class Episode implements Serializable
{
    private int numberOfEpisode,duration;

    /**
     * Κατασκευαστής επεισοδίου
     * @param numberOfEpisode ο αριθμός του επεισοδίου
     * @param duration η διάρκεια του επεισοδίου
     */
    public Episode(int numberOfEpisode,int duration)
    {
        this.numberOfEpisode=numberOfEpisode;
        this.duration= duration;
    }

    /**
     * Setters and getters
     * @return
     */
    public int getNumberOfEpisode()
    {
        return numberOfEpisode;
    }
    public int getDuration()
    {
        return duration;
    }

    public void setNumberOfEpisode(int numberOfEpisode)
    {
        this.numberOfEpisode= numberOfEpisode;
    }

    public void setDuration(int duration)
    {
        this.duration=duration;
    }

}
