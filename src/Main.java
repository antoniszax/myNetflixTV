/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */

/**
 * Πραγματοποιεί αρχικοποίηση-εγγραφή και αποθήκευση στη λίστα people και στο αρχείο People.ser 2 χρηστών και 2 παρόχων.
 * Αποθηκεύει, ακόμη, τα στοιχεία εισόδου τους (username, password) στο HashMap Login.
 * Επίσης, πραγματοποιεί αρχικοποίηση-εγγραφή και αποθήκευση στις λίστες Shows και Μovies καθώς και στα αρχεία Movies.ser και Shows.ser.
 * Τέλος, αποθηκεύει το HashMap Login με τα username και τα password κάθε ανθρώπου στο αρχείο LoginCredentials.ser.
 */

/**
 * Runnable αρχεία αυτό και το Login από το πακέτο gui
 * Σε αυτό το αρχείο γίνεται η αρχικοποίηση και στο Login η σύνδεση και περιήγηση στην εφαρμογή
 */

import api.*;
public class Main {

    private static Database database;
    public static void main(String[] args) {
        //0 τυχαίος ακέραιος για τον ειδικό κατασκευαστή της κλάσης Database για το
        //SetUp που πραγματοποιεί η main
        database = new Database(0);

        //Όλες οι απαραίτητες αρχικοποιήσεις και προσθήκες στις λίστες και στο χάρτη
        Admin admin1 = new Admin("Antonis", "Zax", "admin1", "password1");
        Admin admin2 = new Admin("Chris", "Gkaniatsas", "admin2", "password2");
        User user1 = new User("George", "Meditskos", "user1", "password1");
        User user2 = new User("Konstantinos", "Prousalis", "user2", "password2");
        database.addPerson(admin1);
        database.addLogins(admin1.getUsername(), admin1.getPassword());
        database.addPerson(admin2);
        database.addLogins(admin2.getUsername(), admin2.getPassword());
        database.addPerson(user1);
        database.addLogins(user1.getUsername(), user1.getPassword());
        database.addPerson(user2);
        database.addLogins(user2.getUsername(), user2.getPassword());

        Movie movie1 = new Movie("Oppenheimer", "Viewing the story of the first ever atomic bomb", true, 2023, 134, "Sci-Fi", "Cillian Murphy, Emily Blunt, Matt Damon");
        Movie movie2 = new Movie("Polar Express", "A train has a mission to travel to Christmas", true, 2012, 101, "Sci-Fi", "Tom Hanks, Josh Haderson, Eddie Deezen");
        Movie movie3 = new Movie("Five nights at Freddy's", "The first game revolves around a character called Mike Schmidt, who begins working as a night security guard at Freddy Fazbear's Pizza, where the animatronics move at night and supposedly kill anyone they see by stuffing them into a spare animatronic suit", false, 2023, 144, "Horror", "Josh Haderson, Elizabeth Layle, Paper Rubio");
        Movie movie4 = new Movie("Mechanic", "Bishop's most formidable foe kidnaps the love of his life in order to make him complete three impossible assassinations and make them look like accidents", true, 2016, 98, "Action", "Jason Statham, Jessica Alba, Michel Yo");
        Movie movie5 = new Movie("Dumb and Dumber", "After a woman leaves a briefcase at the airport terminal, a dumb limo driver and his dumber friend set out on a hilarious cross-country road trip to Aspen to return it", true, 1994, 93, "Comedy", "Jim Carrey, Jeff Daniels");

        Review r11 = new Review(user1, "Amazing, one of the best", 4.6, user1.getName());
        Review r12 = new Review(user2, "Could me better", 3.2, user2.getName());
        movie1.addReview(r11);
        movie1.addReview(r12);

        Review r21 = new Review(user1, "I enjoyed a lot", 4.3, user1.getName());
        Review r22 = new Review(user2, "The best i've seen!", 5.0, user2.getName());
        movie2.addReview(r21);
        movie2.addReview(r22);

        Review r31 = new Review(user1, "Really scary as i wanted", 4.8, user1.getName());
        Review r32 = new Review(user2, "The best Horror movie!", 5.0, user2.getName());
        movie3.addReview(r31);
        movie3.addReview(r32);

        Review r41 = new Review(user1, "It's always those statham movies!", 5.0, user1.getName());
        Review r42 = new Review(user2, "I really suggest to everyone", 4.8, user2.getName());
        movie4.addReview(r41);
        movie4.addReview(r42);

        Review r51 = new Review(user1, "I didn't find it that funny", 2.6, user1.getName());
        Review r52 = new Review(user2, "I've burst out laughing!", 4.7, user2.getName());
        movie5.addReview(r51);
        movie5.addReview(r52);

        database.addMovie(movie1);
        database.addMovie(movie2);
        database.addMovie(movie3);
        database.addMovie(movie4);
        database.addMovie(movie5);

        Show show1 = new Show("Prison Break", "Michael commits a crime in order to get arrested and go to the same prison as his brother who will face death penalty in less than a month, will he manage to escape?", true, "Action", "Wentworth Muller, Dominik Purcell, Robert Kneeper");
        Season s11 = new Season(1, 2005);
        Season s12 = new Season(2, 2007);
        show1.addSeason(s11);
        show1.addSeason(s12);
        Episode ep111 = new Episode(1, 44);
        Episode ep112 = new Episode(2, 48);
        Episode ep113 = new Episode(3, 53);
        s11.addEpisode(ep111);
        s11.addEpisode(ep112);
        s11.addEpisode(ep113);
        Episode ep121 = new Episode(1, 49);
        Episode ep122 = new Episode(2, 52);
        Episode ep123 = new Episode(3,58);
        s12.addEpisode(ep121);
        s12.addEpisode(ep122);
        s12.addEpisode(ep123);

        Show show2 = new Show("Peaky Blinders", "The criminals Peaky Blinders from Birmingham of England are trying to become rich at any cost after the WW1", true, "Drama", "Cillian Murphy, Fin Cole, Paul Anderson");
        Season s21 = new Season(1, 2013);
        Season s22 = new Season(1, 2015);
        show2.addSeason(s21);
        show2.addSeason(s22);
        Episode ep211 = new Episode(1, 55);
        Episode ep212 = new Episode(2, 54);
        Episode ep213 = new Episode(3, 55);
        s21.addEpisode(ep211);
        s21.addEpisode(ep212);
        s21.addEpisode(ep213);
        Episode ep221 = new Episode(1, 58);
        Episode ep222 = new Episode(2, 50);
        Episode ep223 = new Episode(3, 53);
        s22.addEpisode(ep221);
        s22.addEpisode(ep222);
        s22.addEpisode(ep223);

        Show show3 = new Show("How to get away with murder", "A sexy, suspense-driven legal thriller about a group of ambitious law students and their brilliant, mysterious criminal defense professor. They become entangled in a murder plot that will shake the entire university and change the course of their lives", true, "Drama", "Viola Davis, Billy Brown, Alfred Enoch");
        Season s31 = new Season(1, 2014);
        Season s32 = new Season(2, 2016);
        show3.addSeason(s31);
        show3.addSeason(s32);
        Episode ep311 = new Episode(1, 62);
        Episode ep312 = new Episode(2, 59);
        Episode ep313 = new Episode(3, 60);
        s31.addEpisode(ep311);
        s31.addEpisode(ep312);
        s31.addEpisode(ep313);
        Episode ep321 = new Episode(1, 55);
        Episode ep322 = new Episode(2, 56);
        Episode ep323 = new Episode(3, 49);
        s32.addEpisode(ep321);
        s32.addEpisode(ep322);
        s32.addEpisode(ep323);

        Show show4 = new Show("Friends", "Friends is about 6 friends who go through just about every life experience imaginable together; love, marriage, divorce, children, heartbreaks, fights, new jobs and job losses and all sorts of drama", true, "Comedy", "Jennifer Aniston, Matthew Perry, Matt LeBlanc");
        Season s41 = new Season(1, 1994);
        Season s42 = new Season(2, 2004);
        show4.addSeason(s41);
        show4.addSeason(s42);
        Episode ep411 = new Episode(1, 45);
        Episode ep412 = new Episode(2, 43);
        Episode ep413 = new Episode(3, 43);
        s41.addEpisode(ep411);
        s41.addEpisode(ep412);
        s41.addEpisode(ep413);
        Episode ep421 = new Episode(1, 48);
        Episode ep422 = new Episode(2, 41);
        Episode ep423 = new Episode(3, 46);
        s42.addEpisode(ep421);
        s42.addEpisode(ep422);
        s42.addEpisode(ep423);

        Show show5 = new Show("13 Reasons why", "Hannah Baker commits suicide and leaves behind her 13 tapes, 1 for every reason she ended her life", false, "Drama", "Alisha Boe, Dylan Minnette, Katherine Langford");
        Season s51 = new Season(1, 2017);
        Season s52 = new Season(2, 2020);
        show5.addSeason(s51);
        show5.addSeason(s52);
        Episode ep511 = new Episode(1, 48);
        Episode ep512 = new Episode(2, 52);
        Episode ep513 = new Episode(3, 45);
        s51.addEpisode(ep511);
        s51.addEpisode(ep512);
        s51.addEpisode(ep513);
        Episode ep521 = new Episode(1, 49);
        Episode ep522 = new Episode(1, 44);
        Episode ep523 = new Episode(1, 55);
        s51.addEpisode(ep521);
        s51.addEpisode(ep522);
        s51.addEpisode(ep523);

        Review r1 = new Review(user1, "My favorite from now on", 4.9, user1.getName());
        Review r2 = new Review(user2, "Could me better", 3.2, user2.getName());
        show1.addReview(r1);
        show1.addReview(r2);

        Review r3 = new Review(user1, "Didn't like it", 2.2, user1.getName());
        Review r4 = new Review(user2, "Old school england, very good", 4.3, user2.getName());
        show2.addReview(r3);
        show2.addReview(r4);

        Review r5 = new Review(user1, "Horrible with a lot of violence scenes", 1.2, user1.getName());
        Review r6 = new Review(user2, "So smart case", 4.8, user2.getName());
        show3.addReview(r5);
        show3.addReview(r6);

        Review r7 = new Review(user1, "The best company I could ever have", 4.9, user1.getName());
        Review r8 = new Review(user2, "Makes my day", 4.9, user2.getName());
        show4.addReview(r7);
        show4.addReview(r8);

        Review r9 = new Review(user1, "I expected more", 3.0, user1.getName());
        Review r10 = new Review(user2, "The show every teenager should watch", 4.4, user2.getName());
        show5.addReview(r9);
        show5.addReview(r10);

        user1.addToFavorites(show1);
        user1.addToFavorites(movie4);
        user2.addToFavorites(show4);
        user2.addToFavorites(movie2);

        user1.addMyReviewed(movie1);
        user1.addMyReviewed(movie2);
        user1.addMyReviewed(movie3);
        user1.addMyReviewed(movie4);
        user1.addMyReviewed(movie5);
        user2.addMyReviewed(movie1);
        user2.addMyReviewed(movie2);
        user2.addMyReviewed(movie3);
        user2.addMyReviewed(movie4);
        user2.addMyReviewed(movie5);

        user1.addMyReviewed(show1);
        user1.addMyReviewed(show2);
        user1.addMyReviewed(show3);
        user1.addMyReviewed(show4);
        user1.addMyReviewed(show5);
        user2.addMyReviewed(show1);
        user2.addMyReviewed(show2);
        user2.addMyReviewed(show3);
        user2.addMyReviewed(show4);
        user2.addMyReviewed(show5);

        database.addShow(show1);
        database.addShow(show2);
        database.addShow(show3);
        database.addShow(show4);
        database.addShow(show5);

        //save lists in files
        database.saveInFiles();

        System.out.println("SetUp completed succesfully!!!");
    }
}
