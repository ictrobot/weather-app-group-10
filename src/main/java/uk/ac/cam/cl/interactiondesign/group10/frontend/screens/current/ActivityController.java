package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.Activity;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;

class ActivityController extends BaseController {

    ObjectProperty<Image> imageActivityProperty;
    StringProperty textActivitySuggestion;

    ActivityController(ActivityView view, Location previousLocation) {
        super(view, previousLocation);
    }

    void initialize() {
        // get an activity for the current weather and set the text and image onscreen to match
        WeatherData.WeatherDataPoint current = currentLocation.getWeatherData().current;
        Activity activity = Activity.getActivity(current.darkSkyIcon);
        imageActivityProperty.setValue(activity.activityImage);
        textActivitySuggestion.setValue(activity.activityString);
    }

}
