package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.forecast;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;

abstract class ForecastController extends BaseController {

    StringProperty locationText;
    ObservableList<Node> scrollChildren;

    ForecastController(ForecastView view, Location currentLocation) {
        super(view, currentLocation);
    }

    protected void initialize() {
        // setup dynamic text (location name)
        locationText.setValue(currentLocation.getName());
    }

}
