package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.geometry.HPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

class EntryView extends GridPane {

    private EntryController controller;

    EntryView(EntryController controller) {
        this.controller = controller;
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    private void makeGrid() {
        // three columns
        ColumnConstraints column1 = new ColumnConstraints();
        getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.RIGHT);
        column2.setHgrow(Priority.ALWAYS);
        getColumnConstraints().add(column2);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHalignment(HPos.LEFT);
        column3.setHgrow(Priority.ALWAYS);
        getColumnConstraints().add(column3);

        // three rows
        for (int i = 0; i < 3; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        Text time = new Text();
        add(time, 0, 0);
        controller.textTime = time.textProperty();

        Text conditions = new Text();
        conditions.setWrappingWidth(200);
        add(conditions, 1, 0, 2, 1);
        setHalignment(conditions, HPos.LEFT);
        controller.textConditions = conditions.textProperty();

        add(new Text("Temperature: "), 1, 1);
        add(new Text("Precipitation: "), 1, 2);

        Text temperature = new Text();
        add(temperature, 2, 1);
        controller.textTemperature = temperature.textProperty();

        Text precipitation = new Text();
        add(precipitation, 2, 2);
        controller.textPrecipitation = precipitation.textProperty();

        ImageView imageView = new ImageView();
        imageView.setFitHeight(80);
        imageView.setPreserveRatio(true);
        add(imageView, 0, 1, 1, 2);
        controller.imageProperty = imageView.imageProperty();
    }
}
