package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.current;

import javafx.beans.property.StringProperty;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.backend.WeatherData;
import uk.ac.cam.cl.interactiondesign.group10.frontend.Activities;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;

class ActivityController extends BaseController {

    StringProperty textActivitySuggestion;

    ActivityController(ActivityView view, Location previousLocation) {
        super(view, previousLocation);
    }

    void initialize() {
        WeatherData.WeatherDataPoint current = currentLocation.getWeatherData().current;
        String activity = Activities.getActivity(current);
        textActivitySuggestion.setValue(activity);
    }

}
