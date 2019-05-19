package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

/**
 * Daily forecast screen
 */
public class DailyView extends ForecastView {

    public DailyView(Location currentLocation) {
        controller = new DailyController(this, currentLocation);
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    @Override
    protected String getTitleString() {
        return "Daily weather forecast for";
    }
}
