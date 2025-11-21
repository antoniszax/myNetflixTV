package api;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Κλάση που υλοποιεί τη βάση που ευθύνεται για την αποθήκευση όλων των στοιχείων των ανθρώπων που χρησιμοποιούν την εφαρμογή
 * και όλων των καταλυμάτων που εγγράφονται στην εφαρμογή.
 * Επίσης, υλοποιεί την είσοδο στο σύστημα και την εγγραφή χρήστη σε αυτό.
 */

public class Database {

    ArrayList<Movie> movies;
    ArrayList<Show> shows;

    ArrayList<Person> people;
    HashMap<String, String> Login = new HashMap<>();
    ArrayList<String> genreList;

    /**
     * Κατασκευαστής της βάσης που θα υπάρχουν τα στοιχεία.
     * Διαβάζει από τα αρχεία People.ser, Movies.ser, Shows.ser, LoginCredentials.ser και αρχικοποιεί τις λίστες και το HashMap.
     * Ακόμη, δημιουργεί τη λίστα myReviewed του κάθε user και τοποθετεί τις ταινίες-σειρές στις οποίες έχει κάνει αξιολόγηση.
     */
    public Database() {
        movies = new ArrayList<>();
        shows = new ArrayList<>();
        people = new ArrayList<>();
        movies = readListFromFile("Movies.ser");
        shows = readListFromFile("Shows.ser");
        people = readListFromFile("People.ser");
        Login = readMapFromFile("LoginCredentials.ser");
        initializeGenreList();
    }

    /**
     * Κατασκευαστής για το SetUp που πραγματοποιεί η main.
     *
     * @param k Τυχαία μεταβλητή.
     */
    public Database(int k) {
        movies = new ArrayList<>();
        shows = new ArrayList<>();
        people = new ArrayList<>();
    }

    /**
     * Προσθέτει μία ταινία στη λίστα movies
     *
     * @param movie η ταινία που δίνεται ως όρισμα για να προστεθεί στη λίστα
     */

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Μέθοδος που δέχεται μία ταινία και τη διαγράφει από τη λίστα
     *
     * @param movie η ταινία που θα διαγραφτεί
     */
    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }


    /**
     * Προσθέτει μία σειρά στη λίστα με τις σειρές
     *
     * @param show η σειρά που θα προστεθεί
     */
    public void addShow(Show show) {
        shows.add(show);
    }

    /**
     * Μέθοδος που δέχεται μία σειρά και τη διαγράφει από τη λίστα με τις σειρές
     *
     * @param show η σειρά που αφαιρείται.
     */
    public void deleteShow(Show show) {
        shows.remove(show);
    }

    /**
     * προσθέτει έναν άνθρωπο στη λίστα με τους ανθρώπους
     *
     * @param p ο άνθρωπος
     */
    public void addPerson(Person p) {
        people.add(p);
    }


    /**
     * Μέθοδος που πραγματοποιεί εγγραφή χρήστη στο σύστημα ελέγχοντας
     * να μην χρησιμοποιείται το όνομα χρήστη που δίνεται.
     *
     * @param username το όνομα χρήστη του νέου χρήστη (αν δε το έχει άλλος χρήστης
     * @param password ο κωδικος του χρήστη
     */
    public void addLogins(String username, String password) {
        if (!Login.containsKey(username))
            Login.put(username, password);
        else
            System.out.println("username already exists!");
    }


    private void initializeGenreList() {
        genreList = new ArrayList<>();
        genreList.add("Horror");
        genreList.add("Drama");
        genreList.add("Sci-Fi");
        genreList.add("Comedy");
        genreList.add("Action");

    }

    /**
     * Μέθοδος για την εγγραφή όλων των χρήσιμων δεδομένων του προγράμματος σε αρχεία.
     * Συγκεκριμένα, αποθηκεύονται οι λίστες με τους χρήστες και παρόχους του συστήματος, το σύνολο των ταινιών και σειρών και τα στοιχεία σύνδεσης των χρηστών και παρόχων.
     */
    public void saveInFiles() {
        writeListToFile("People.ser", people);
        writeListToFile("Shows.ser", shows);
        writeListToFile("Movies.ser", movies);
        writeMapToFile("LoginCredentials.ser", Login);
    }

    /**
     * Μέθοδος που εγγράφει μια λίστα σε αρχείο με συγκεκριμένο path.
     *
     * @param path Διεύθυνση αρχείου στο οποίο γίνεται η εγγραφή.
     * @param list Λίστα την οποία εγγράφουμε στο αρχείο.
     */
    public void writeListToFile(String path, ArrayList list) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));) {
            obj.writeObject(list);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μέθοδος που πραγματοποιεί την εγγραφή ενός νέου χρήστη στο σύστημα.
     * Η εγγραφή περιλαμβάνει:
     * * * * Προσθήκη στοιχείων εισόδου στο map Login
     * * * * Προσθήκη του νέου χρήστη στη λίστα people
     *
     * @param name     Όνομα.
     * @param surname  Επίθετο.
     * @param username Όνομα χρήστη ή παρόχου.
     * @param password Κωδικός χρήστη ή παρόχου.
     * @return Επιστρέφει τον άνθρωπο (τύπος Person) ο οποίος εγγράφτηκε. Αν το username υπάρχει ήδη επιστρέφει null.
     */
    public Person register(String name, String surname, String username, String password) {
        //username already exists
        if (Login.containsKey(username)) {
            return null;
        }


        User newPerson = new User(name, surname, username, password);
        Login.put(username, password);
        people.add(newPerson);
        return newPerson;

    }

    /**
     * Μέθοδος που εγγράφει ένα HashMap σε αρχείο με συγκεκριμένο path.
     *
     * @param path Διεύθυνση αρχείου στο οποίο γίνεται η εγγραφή.
     * @param map  HashMap το οποίο εγγράφουμε στο αρχείο.
     */
    public void writeMapToFile(String path, HashMap map) {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(path));) {
            obj.writeObject(map);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μέθοδος που διαβάζει μια λίστα από αρχείο με συγκεκριμένο path.
     *
     * @param path Διεύθυνση αρχείου από το οποίο γίνεται η ανάγνωση.
     * @return Επιστρέφει τη λίστα που διαβάστηκε.
     */
    public ArrayList readListFromFile(String path) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(path));) {
            if (path.equals("People.ser")) {
                ArrayList<Person> people = (ArrayList<Person>) obj.readObject();
                obj.close();
                return people;
            } else if (path.equals("Shows.ser")) {
                ArrayList<Show> show = (ArrayList<Show>) obj.readObject();
                obj.close();
                return show;
            }
            ArrayList<Movie> movie = (ArrayList<Movie>) obj.readObject();
            obj.close();
            return movie;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     * Μέθοδος που διαβάζει ένα HashMap από αρχείο με συγκεκριμένο path.
     *
     * @param path Διεύθυνση αρχείου από το οποίο γίνεται η ανάγνωση.
     * @return Επιστρέφει το HashMap που διαβάστηκε.
     */
    public HashMap readMapFromFile(String path) {
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(path));) {
            HashMap<String, String> map = (HashMap<String, String>) obj.readObject();
            obj.close();
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Μέθοδος που πραγματοποιεί την είσοδο ενός χρήστη στο σύστημα με το username και το password του.
     * Γίνεται ταυτοποίηση με αναζήτηση στη λίστα people.
     *
     * @param username Όνομα χρήστη
     * @param password Κωδικός χρήστη
     * @return Επιστρέφει τον άνθρωπο (τύπος Person) που εισήχθη στο σύστημα. Αν η ταυτοποίηση αποτύχει επιστρέφει null.
     */
    public Person login(String username, String password) {
        for (Person a : people) {
            if (a.getUsername().equals(username)) {
                if (a.getPassword().equals(password)) {
                    return a;
                }
            }
        }
        return null;
    }

  /*  public void showGenres()
    {
        for(String g: genreList)
        {
            System.out.println(g);
        }
    }*/


    /**
     * Επιστρέφεται η λίστα με όλες τις ταινίες
     *
     * @return movies η λίστα
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * Επιστρέφεται η λίστα με όλες τις σειρές
     *
     * @return shows η λίστα
     */
    public ArrayList<Show> getShows() {
        return shows;
    }


    /**
     * Μέθοδος που παίρνει τα φίλτρα που δίνει ο χρήστης και κάνει αναζήτηση
     *
     * @return
     */


    public ArrayList search(String title, String cast, boolean isAppropriate, String genre, double leastRating, Database db) {
        //λίστα που θα περιέχονται οι παραγωγές με τα φίλτα αναζήτησης που δόθηκαν
        ArrayList<Production> searchResults = new ArrayList<>();

        if(title.equals("") && cast.equals("")){
            for(Movie a: db.getMovies()){
                if(a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
            for(Show a: db.getShows()){
                if(a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
        }

        if(title.equals("") && !cast.equals("")){
            for(Movie a:db.getMovies()){
                if(a.getCast().toLowerCase().contains(cast.toLowerCase()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
            for(Show a:db.getShows()){
                if(a.getCast().toLowerCase().contains(cast.toLowerCase()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
        }

        if(!title.equals("") && cast.equals("")){
            for(Movie a:db.getMovies()){
              if(title.equalsIgnoreCase(a.getTitle()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                  searchResults.add(a);
            }
            for(Show a:db.getShows()){
                if(title.equalsIgnoreCase(a.getTitle()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
        }

        if(!title.equals("") && !cast.equals("")){
            for(Movie a:db.getMovies()){
              if(title.equalsIgnoreCase(a.getTitle()) && a.getCast().toLowerCase().contains(cast.toLowerCase()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                  searchResults.add(a);
            }
            for(Show a:db.getShows()){
                if(title.equalsIgnoreCase(a.getTitle()) && a.getCast().toLowerCase().contains(cast.toLowerCase()) && a.getAppropriation()==isAppropriate && a.getGenre()==genre && a.averageScore()>leastRating)
                    searchResults.add(a);
            }
        }


        return searchResults;
    }

}

