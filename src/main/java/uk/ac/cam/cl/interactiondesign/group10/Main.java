package uk.ac.cam.cl.interactiondesign.group10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIException;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIKeys;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current.MainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        tryLoadApiKeys();

        Location startingLocation = Location.detectLocation();
    }

    private void tryLoadApiKeys() {
        try {
            APIKeys.loadAPIKeys();
        } catch (APIException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("API Keys");
            alert.setHeaderText("Missing or Invalid API Keys");
            alert.setContentText("Please supply valid API Keys for the required APIs");
            alert.showAndWait();

            Platform.exit();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
