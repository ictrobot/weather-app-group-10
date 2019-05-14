package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.Activities;

import java.io.IOException;
import java.net.URL;

public class ActivityScreen extends ScreenBase {

    private static final URL FXML_URL = ActivityScreen.class.getResource("ActivityScreen.fxml");

    static void show(Stage stage, Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            ActivityScreen activityScreen = loader.getController();
            activityScreen.initialize(location);

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ActivityScreen", e);
        }
    }

    private Location location;

    @FXML
    private Text textCurrentLocation;

    @FXML
    private Text textCurrentActivity;

    private void initialize(Location location) {
        this.location = location;

        textCurrentLocation.setText(location.getName());

        String currentIcon = location.getWeatherData().current.darkSkyIcon;
        String activityText = Activities.getActivity(currentIcon);
        System.out.println(currentIcon + " " + activityText);
        textCurrentActivity.setText(activityText);
    }

    public void goBack() {
        MainMenu.show(getStage(), location);
    }

}
