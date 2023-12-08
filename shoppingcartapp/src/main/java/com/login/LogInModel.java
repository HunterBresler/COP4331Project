package com.login;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The LogInModel class represents the model in the MVC pattern for the login functionality.
 * It manages user credentials and provides methods to validate login attempts.
 */
public class LogInModel {
    private Map<String, UserCredentials> db = new HashMap<>();

    /**
     * Constructs a new LogInModel and initializes the user credentials database by reading from a file.
     */
    public LogInModel() {
        readDB();
    }

    /**
     * Validates the user credentials against the stored credentials in the database.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @param role The role selected by the user (e.g., Customer, Seller).
     * @return true if the credentials are valid, false otherwise.
     */
    public boolean validateCredentials(String username, String password, String role) {
        UserCredentials credentials = db.get(username + ":" + role);
        return credentials != null && credentials.getPassword().equals(password);
    }

    /**
     * Reads user credentials from a text file and stores them in the database.
     * The file format is expected to be 'username:password:role' on each line.
     */
    private void readDB() {
        try (BufferedReader reader = new BufferedReader(new FileReader("db.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    db.put(username + ":" + role, new UserCredentials(username, password, role));
                } else {
                    System.out.println("Invalid line format in db.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading db.txt");
        }
    }

    // Other necessary methods...
}
