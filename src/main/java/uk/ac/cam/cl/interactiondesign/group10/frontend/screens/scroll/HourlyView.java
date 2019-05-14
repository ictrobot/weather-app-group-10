package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.scroll;

import uk.ac.cam.cl.interactiondesign.group10.backend.Location;

public class HourlyView extends ScrollView {

    public HourlyView(Location currentLocation) {
        controller = new HourlyController(this, currentLocation);
        makeGrid();
        populateGrid();
        controller.initialize();
    }

    @Override
    protected String getTitleString() {
        return "Hourly weather forecast for";
    }
}
