package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

import java.util.function.DoubleConsumer;

class CurrentInfoController {

    private final Location location;

    StringProperty textLocation;
    StringProperty textConditions;
    StringProperty textTemperature;
    StringProperty textPrecipitation;
    ObjectProperty<Image> imageProperty;

    DoubleConsumer thermoAnimationSetup;
    DoubleConsumer precAnimationSetup;

    CurrentInfoController(Location location) {
        this.location = location;
    }

    void initialize() {
        WeatherData.WeatherDataPoint current = location.getWeatherData().current;
        textLocation.setValue(location.getName());
        textConditions.setValue(current.darkSkySummary);
        textTemperature.setValue(current.getTemperatureString());
        textPrecipitation.setValue(current.getPrecipitationString());
        imageProperty.setValue(ImageCache.weatherImage(current.darkSkyIcon));

        thermoAnimationSetup.accept(current.temperature);
        precAnimationSetup.accept(current.precipitationProbability);
    }

}
