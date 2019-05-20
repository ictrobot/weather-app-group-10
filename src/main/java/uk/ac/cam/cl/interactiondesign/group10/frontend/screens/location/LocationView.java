package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.location;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WButton;
import uk.ac.cam.cl.interactiondesign.group10.frontend.components.WText;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;

/**
 * Change location screen
 */
public class LocationView extends BaseView {

    private LocationController controller;

    public LocationView(Location currentLocation) {
        controller = new LocationController(this, currentLocation);
        makeGrid();
        populateGrid();
    }

    private void makeGrid() {
        // one column
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.CENTER);
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);

        // six rows
        for (int i = 0; i < 6; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            getRowConstraints().add(rowConstraints);
        }
    }

    private void populateGrid() {
        // top title text
        Text title = new WText("Change location", true);
        add(title, 0, 0);

        // uk map image
        ImageView ukMap = new ImageView(ImageCache.loadImage("other/uk.png"));
        ukMap.setPreserveRatio(true);
        ukMap.fitHeightProperty().bind(heightProperty().multiply(0.5));
        add(ukMap, 0, 1);

        // current location button
        Button useMyLocation = new WButton("Use my location");
        useMyLocation.setOnAction(controller::doLocate);
        add(useMyLocation, 0, 2);

        add(new WText("Or, enter a location:"), 0, 3);

        // text input field for the user to type in a place to search
        TextField locationInput = new TextField();
        locationInput.setPromptText("Location");
        locationInput.setOnAction(controller::doSearch);
        add(locationInput, 0, 4);
        controller.searchStringProperty = locationInput.textProperty();

        // location search button
        Button search = new WButton("Search");
        search.setOnAction(controller::doSearch);
        add(search, 0, 5);

        // finally, back button
        Button back = new WButton("Back");
        back.setOnAction(controller::gotoMainMenu);
        add(back, 0, 6);
    }
}
