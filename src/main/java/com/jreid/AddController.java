package com.jreid;

import com.jfoenix.controls.JFXButton;
import com.jreid.database.DataHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    public static Controller mainController;
    public static Stage thisStage;

    @FXML
    private DatePicker date;

    @FXML
    private TextField amount;

    @FXML
    private TextField description;

    @FXML
    private JFXButton addButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnAction(event -> {
            //String dateStr = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Timestamp timestamp = Timestamp.valueOf(date.getValue().atStartOfDay());
            String descriptionStr = description.getText();
            Double amountStr = Double.NaN;
            try {
                amountStr = Double.parseDouble(amount.getText());
            } catch (NumberFormatException nfe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The value entered into amount is not a numeric value.");
                alert.showAndWait();
            }
            if (!amountStr.isNaN()) {
                Figure figure = new Figure(timestamp,amountStr,descriptionStr);
                mainController.addItemToTable(figure);
                DataHandler.addRecord(figure);
                thisStage.close();
            }
        });
    }
}
