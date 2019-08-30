package com.jreid.database;

import com.jreid.Figure;
import com.jreid.settings.Settings;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataHandler {
    public static void createTable() {
        try {
            Connection conn = Connector.getInstance();
            Statement stmt = conn.createStatement();
            stmt.execute(Settings.CREATE_TABLE_SQL);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DataHandler.class.getName());
            logger.log(Level.SEVERE, "Failed to create new SQL table.", e);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Error with the program's data system.");
            alert.showAndWait();
        }
    }

    public static void addRecord(Figure figure) {
        try {
            Connection conn = Connector.getInstance();
            PreparedStatement pstmt = conn.prepareStatement(Settings.INSERT_DATA_SQL);
            pstmt.setString(1, figure.getDescription());
            pstmt.setDouble(2, figure.getAmount());
            pstmt.setTimestamp(3, figure.getDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DataHandler.class.getName());
            logger.log(Level.SEVERE, "Failed to create add new record.", e);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Error with the program's data system.");
            alert.showAndWait();
        }
    }

    public static List<Figure> getAllRecords() {
        List<Figure> figures = new ArrayList<>();
        try {
            Connection conn = Connector.getInstance();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Settings.SELECT_DATA_SQL);

            while (rs.next()) {
                figures.add(new Figure(rs.getTimestamp("date"), rs.getDouble("amount"),
                        rs.getString("description")));
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DataHandler.class.getName());
            logger.log(Level.SEVERE, "Failed to get all records.", e);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Error with the program's data system.");
            alert.showAndWait();
        }
        return figures;
    }
}
