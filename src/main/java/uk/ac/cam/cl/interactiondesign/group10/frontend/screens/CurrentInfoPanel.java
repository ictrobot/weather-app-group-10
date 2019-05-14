package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CurrentInfoPanel extends ScreenBase {

    private static final URL FXML_URL = CurrentInfoPanel.class.getResource("CurrentInfoPanel.fxml");

    static Parent load(Location location) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            CurrentInfoPanel activityScreen = loader.getController();
            activityScreen.initialize(location);
            return root;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CurrentInfoPanel", e);
        }
    }

    @FXML
    private Text textCurrentLocation;

    @FXML
    private Text textConditions;

    @FXML
    private Text textTemperature;

    @FXML
    private Text textPrecipitation;

    @FXML
    private ImageView imageWeather;

    private void initialize(Location location) {
        WeatherData.WeatherDataPoint current = location.getWeatherData().current;

        textCurrentLocation.setText(location.getName());
        textConditions.setText(current.darkSkySummary);
        textTemperature.setText(current.getTemperatureString());
        textPrecipitation.setText(current.getPrecipitationString());

        try (InputStream imageStream = ScrollScreenEntry.class.getResourceAsStream("/images/weather/" + current.darkSkyIcon +".png")) {
            imageWeather.setImage(new Image(imageStream));
        }
        catch (NullPointerException n) {
            n.printStackTrace();
            System.out.println("The weather recieved does not have an icon");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
