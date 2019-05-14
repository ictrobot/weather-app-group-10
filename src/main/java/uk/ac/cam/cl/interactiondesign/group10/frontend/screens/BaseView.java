package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public abstract class BaseView extends GridPane {

    public BaseView() {
        setPadding(new Insets(20f));
        setPrefWidth(9 * 40);
        setPrefHeight(16 * 40);
        setMinWidth(9 * 30);
        setMinHeight(16 * 30);
    }

}
