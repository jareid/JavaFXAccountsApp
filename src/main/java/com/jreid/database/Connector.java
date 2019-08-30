package com.jreid.database;

import com.jreid.settings.Settings;
import javafx.scene.control.Alert;
import org.sqlite.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connector {
    private static Connector connector = null;

    public static Connection conn = null;

    public Connector() {
        try {
            Class.forName("org.sqlite.JDBC"); // Force load the sqlite driver
            String url = "jdbc:sqlite:" + Settings.DATABASE_FLAT_FILE; // db parameters

            conn = DriverManager.getConnection(url); // create a connection to the database

            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.INFO, "Connection to SQLite has been established.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Connect to a sample database
     */
    public static Connection getInstance() {
        if (connector == null) {
            connector = new Connector();
        }
        return conn;
    }
}