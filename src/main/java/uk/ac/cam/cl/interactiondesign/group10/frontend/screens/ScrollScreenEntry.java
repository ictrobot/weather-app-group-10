package uk.ac.cam.cl.interactiondesign.group10.frontend.screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

import java.io.IOException;
import java.net.URL;

public class ScrollScreenEntry extends ScreenBase {

    private static final URL FXML_URL = ScrollScreenEntry.class.getResource("ScrollScreenEntry.fxml");

    static Parent load(WeatherData.WeatherDataPoint data) {
        try {
            FXMLLoader loader = new FXMLLoader(FXML_URL);
            Parent root = loader.load();
            ScrollScreenEntry activityScreen = loader.getController();
            activityScreen.initialize(data);
            return root;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ActivityScreen", e);
        }
    }

    private WeatherData.WeatherDataPoint data;

    @FXML
    private Text textTime;

    @FXML
    private Text textConditions;

    @FXML
    private Text textTemperature;

    @FXML
    private Text textPrecipitation;

    @FXML
    private ImageView imageWeather;

    private void initialize(WeatherData.WeatherDataPoint data) {
        this.data = data;

        textTime.setText(data.timestampText);
        textConditions.setText(data.darkSkySummary);
        textTemperature.setText(data.getTemperatureString());
        textPrecipitation.setText(data.getPrecipitationString());
        imageWeather.setImage(ImageCache.weatherImage(data.darkSkyIcon));
    }

}
