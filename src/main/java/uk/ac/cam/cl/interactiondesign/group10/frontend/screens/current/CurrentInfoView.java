package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

class CurrentInfoView extends GridPane {

    private CurrentInfoController controller;

    CurrentInfoView(CurrentInfoController currentInfoController) {
        this.controller = currentInfoController;
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    private void makeGrid() {
        // three columns
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.NEVER);
        column1.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        column2.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHgrow(Priority.NEVER);
        column3.setHalignment(HPos.CENTER);
        getColumnConstraints().add(column3);

        // six rows
        for (int i = 0; i < 6; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setValignment(VPos.CENTER);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text location = new Text();
        add(location, 0, 0, 3, 1);
        controller.textLocation = location.textProperty();

        ImageView weatherIcon = new ImageView();
        weatherIcon.setFitHeight(200);
        weatherIcon.setPreserveRatio(true);
        add(weatherIcon, 1, 1);
        controller.imageProperty = weatherIcon.imageProperty();

        Text conditions = new Text();
        add(conditions, 1, 2);
        controller.textConditions = conditions.textProperty();

        // temperature

        ImageView thermometer = new ImageView(ImageCache.loadImage("other/thermometer.png"));
        thermometer.setFitWidth(50);
        thermometer.setPreserveRatio(true);
        add(thermometer, 0, 1, 1, 3);

        add(new Text("Temperature: "), 0, 4);

        Text temperature = new Text();
        add(temperature, 0, 5);
        controller.textTemperature = temperature.textProperty();

        // precipitation

        ImageView raindrop = new ImageView(ImageCache.loadImage("other/raindrop.png"));
        raindrop.setFitWidth(50);
        raindrop.setPreserveRatio(true);
        add(raindrop, 2, 1, 1, 3);

        add(new Text("Precipitation: "), 2, 4);

        Text precipitation = new Text();
        add(precipitation, 2, 5);
        controller.textPrecipitation = precipitation.textProperty();
    }
}