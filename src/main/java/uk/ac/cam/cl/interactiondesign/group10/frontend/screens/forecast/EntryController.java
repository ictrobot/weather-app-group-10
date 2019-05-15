package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

class EntryController {

    private final WeatherData.WeatherDataPoint data;

    StringProperty textTime;
    StringProperty textConditions;
    StringProperty textTemperature;
    StringProperty textPrecipitation;
    ObjectProperty<Image> imageProperty;

    EntryController(WeatherData.WeatherDataPoint data) {
        this.data = data;
    }

    void initialize() {
        textTime.setValue(data.timestampText);
        textConditions.setValue(data.darkSkySummary);
        textTemperature.setValue(data.getTemperatureString());
        textPrecipitation.setValue(data.getPrecipitationString());
        imageProperty.setValue(getWeatherImage());
    }

    protected Image getWeatherImage() {
        return ImageCache.weatherImage(data.darkSkyIcon);
    }

}
