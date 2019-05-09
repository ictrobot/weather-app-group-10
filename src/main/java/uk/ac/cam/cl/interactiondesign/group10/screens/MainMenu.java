package uk.ac.cam.cl.interactiondesign.group10.screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends ScreenBase {

    public static final URL FXML_URL = MainMenu.class.getResource("MainMenu.fxml");

    static void showMainMenu(ScreenBase current, Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            MainMenu mainMenu = loader.getController();
            mainMenu.initialize(location);

            current.getStage().setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load mainmenu", e);
        }
    }


    Location location;

    @FXML
    private Text textCurrentLocation;

    @FXML
    private Text textCurrentTemp;

    private void initialize(Location location) {
        this.location = location;

        textCurrentLocation.setText(location.getName());

        // \u00B0 is the degrees symbol. Unicode literal used to avoid display issues
        textCurrentTemp.setText(location.getWeatherData().temperature + "\u00B0C");
    }

}
