package uk.ac.cam.cl.interactiondesign.group10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.cam.cl.interactiondesign.group10.backend.APIKeys;
import uk.ac.cam.cl.interactiondesign.group10.screens.LocationScreen;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        APIKeys.loadAPIKeys();

        Parent root = FXMLLoader.load(LocationScreen.FXML_URL);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
