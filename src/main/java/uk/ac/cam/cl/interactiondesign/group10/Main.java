package uk.ac.cam.cl.interactiondesign.group10;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIException;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIKeys;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current.MainView;

/**
 * Main entry point: run this class to start the project
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        tryLoadApiKeys();

        // Detect the location to use when first opening the app
        // Agreed that in the Hi-Fi prototype we could hard code to presume Cambridge
        Location startingLocation = Location.detectLocation();

        // setup the basic window
        primaryStage.setTitle("Weather App");
        primaryStage.setMinWidth(BaseView.MINIMUM_WIDTH);
        primaryStage.setMinHeight(BaseView.MINIMUM_HEIGHT);
        primaryStage.getIcons().add(ImageCache.weatherImage("cloudy"));

        // set to the main menu
        primaryStage.setScene(new Scene(new MainView(startingLocation)));

        // show the window now that it is setup
        primaryStage.show();
    }

    private void tryLoadApiKeys() {
        // try loading the API keys from the secrets file
        // if this fails display a dialog box explaining and then exit
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
        // start JavaFX
        launch(args);
    }

}
