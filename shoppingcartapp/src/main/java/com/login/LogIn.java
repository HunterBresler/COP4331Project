package com.login;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class LogIn extends JFrame 
{
    private Map<String, String> db = new HashMap<>(); // database for login credentials

    // constructor
    public LogIn()
    {
        // Fill db
        readDB();
    }
    
    public void showFrame()
    {

    }

    // Reads db.txt and writes it into db variable
    public void readDB()
    {
        
    }

    // Get input for login and return true if valid
    public String input()
    {
        // Get input
        checkLogIn("temp", "temp");
        return "";
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
}
