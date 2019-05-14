package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.location.LocationView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast.DailyView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast.HourlyView;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends ScreenBase {

    private static final URL FXML_URL = MainMenu.class.getResource("MainMenu.fxml");

    public static void show(Stage stage, Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            MainMenu mainMenu = loader.getController();
            mainMenu.initialize(location);

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load MainMenu", e);
        }
    }


    private Location location;

    @FXML
    private AnchorPane currentInfoPane;

    private void initialize(Location location) {
        this.location = location;

        Parent info = CurrentInfoPanel.load(location);
        currentInfoPane.getChildren().add(info);
    }

    public void showActivity() {
        ActivityScreen.show(getStage(), location);
    }

    public void showHourly() {
        getStage().setScene(new Scene(new HourlyView(location)));
    }

    public void showDaily() {
        getStage().setScene(new Scene(new DailyView(location)));
    }

    public void changeLocation() {
        getStage().setScene(new Scene(new LocationView(location)));
    }

}
