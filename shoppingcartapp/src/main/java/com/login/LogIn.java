package com.login;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class LogIn extends JFrame 
{
    private Map<String, String> db = new HashMap<>(); // database for login credentials
    private boolean correctLogIn = false; // tracks whether there has been a successful login

    // constructor
    public LogIn()
    {
        // Fill db
        readDB();
        showLogInFrame();
    }
    
    public void showLogInFrame()
    {
        String user = "";

        // Show frame

        while(correctLogIn == false)
        {
            // Get correct login credentials
            user = inputLogIn();
        }

        // Close frame

        // Create user
        createUser(user);
    }

    // Reads db.txt and writes it into db variable
    public void readDB()
    {
        
    }

    // Get input for login and return true if valid
    public String inputLogIn()
    {
        // Get input
        String user = "temp";

        // check and validate login
        if (checkLogIn("temp", "temp"))
        {
            correctLogIn = true;
        }

        return user;
    }

    // Check if login credentials match db
    public boolean checkLogIn(String username, String password)
    {
        // if password = password associated with username
        if (password == db.get(username)) 
        {
            return true;
        } 
        else 
        {
            return false;
        }
    }
    // Displays invalid Username or Password
    public void showError()
    {

    }

    public void createUser(String user)
    {
        // Open selection
        String classSelection = inputClass();

        // Create class based on class selection

        // Open shopping cart for specified user
    }

    public String inputClass()
    {
        // Get selection
        String selection = "temp";

        return selection;
    }
}
