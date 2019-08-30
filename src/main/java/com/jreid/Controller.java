package com.jreid;

import com.jfoenix.controls.JFXButton;
import com.jreid.database.DataHandler;
import com.jreid.extras.PriceTableCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<Figure> table;

    @FXML
    private TableColumn<Figure, String> descColumn;

    @FXML
    private TableColumn<Figure, Double> amntColumn;

    @FXML
    private TableColumn<Figure, String> dateColumn;

    @FXML
    private JFXButton addBtn;

    public static Stage stage;

    public void loadAboutPage() {
        Stage thisStage = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/about.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 200, 100);
            thisStage = new Stage();
            thisStage.setTitle("About This Application");
            thisStage.setScene(scene);
            thisStage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Catastrphic program error, closing the application.");
            alert.showAndWait();
            if (thisStage != null) {
                thisStage.close();
            }
            stage.close();
        }
    }

    public void loadAddPage() {
        Stage thisStage = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/adddata.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            thisStage = new Stage();
            thisStage.setTitle("Add Accounting Data");
            thisStage.setScene(scene);
            thisStage.show();
            AddController.mainController = this;
            AddController.thisStage = thisStage;
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);

            Alert alert = new Alert(Alert.AlertType.ERROR, "Catastrphic program error, closing the application.");
            alert.showAndWait();
            if (thisStage != null) {
                thisStage.close();
            }
            stage.close();
        }
    }

    public void setupTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amntColumn.setCellValueFactory(new PropertyValueFactory<Figure, Double>("amount"));
        amntColumn.setCellFactory(tc -> new PriceTableCell<Figure>());
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void addItemToTable(Figure figure) {
        table.getItems().add(figure);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Menu m = new Menu("Menu");
        MenuItem about = new MenuItem("About");
        m.getItems().add(about);
        menuBar.getMenus().add(m);
        about.setOnAction(e -> {
            loadAboutPage();
        });
        addBtn.setOnAction(e -> {
            loadAddPage();
        });

        setupTable();

        DataHandler.createTable();

        for (Figure figure : DataHandler.getAllRecords()) {
            addItemToTable(figure);
        }
    }
}
