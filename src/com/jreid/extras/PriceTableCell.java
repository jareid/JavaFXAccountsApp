package com.jreid.extras;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class PriceTableCell<S> extends TableCell<S, Double> {

    private final AnchorPane pane ;
    private final Label valueLabel ;
    // locale-aware currency format to use for formatting
    private DecimalFormat format;

    public PriceTableCell() {
        // grab an instance
        format = (DecimalFormat) NumberFormat.getCurrencyInstance();
        //get the currency symbol
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("€");
        format.setDecimalFormatSymbols(symbols);

        valueLabel = new Label();
        pane = new AnchorPane(valueLabel);
        AnchorPane.setRightAnchor(valueLabel, 0.0);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Double price, boolean empty) {
        super.updateItem(price, empty);
        if (empty) {
            setGraphic(null);
        } else {
            // manual formatting
            //String text = String.format("%,d.%02d", price / 100, Math.abs(price % 100));
            if (price < 0) {
                valueLabel.setStyle("-fx-text-fill: #eb4034");
            }
            format.setMaximumFractionDigits(2);
            format.setMinimumFractionDigits(2);
            valueLabel.setText(format.format(price));
            setGraphic(pane);
        }
    }
}