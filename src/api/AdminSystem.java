package api;

/**
 * κλαση που ευθυνεται για τις λειτουργιες που μπορει να κανει ενας διαχειριστης
 */
public class AdminSystem {

    Database database;
    Admin admin;
    public AdminSystem(Admin admin, Database database){
        this.admin = admin;
        this.database = database;
    }

    public Database getDB(){
        return database;
    }
}
