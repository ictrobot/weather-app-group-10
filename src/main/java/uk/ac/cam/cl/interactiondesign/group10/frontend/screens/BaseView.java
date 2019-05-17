package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Every screen is made up of a View and Controller class.
 * The screen's View class extends BaseView which in turn extends GridPane
 * It is responsible for constructing the needed components and their layout
 */
public abstract class BaseView extends GridPane {

    public static final int MINIMUM_WIDTH = 9 * 40;
    public static final int MINIMUM_HEIGHT = 16 * 40;

    public BaseView() {
        // setup defaults

        // padding around the edge of the screen
        setPadding(new Insets(20f));

        // preferred sizing
        setPrefWidth(MINIMUM_WIDTH);
        setPrefHeight(MINIMUM_HEIGHT);

        // minimum sizing
        setMinWidth(MINIMUM_WIDTH);
        setMinHeight(MINIMUM_HEIGHT);

        // padding around components
        setHgap(10);
        setVgap(10);
    }

    /**
     * Helper method to set the background colour of the screen
     */
    void setBackgroundColor(Color colour) {
        BackgroundFill backgroundFill = new BackgroundFill(colour, null, null);
        setBackground(new Background(backgroundFill));
    }

}
