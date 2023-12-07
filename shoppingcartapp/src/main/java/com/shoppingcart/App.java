package com.shoppingcart;

import com.login.LogIn;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Print the current working directory
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI creation
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LogIn(); // Create an instance of the LogIn class, which will display the GUI
            }
        });
    }
}
