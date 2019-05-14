package uk.ac.cam.cl.interactiondesign.group10.frontend.screens.scroll;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import uk.ac.cam.cl.interactiondesign.group10.backend.Location;
import uk.ac.cam.cl.interactiondesign.group10.frontend.screens.BaseController;

abstract class ScrollController extends BaseController {

    StringProperty locationText;
    ObservableList<Node> scrollChildren;

    ScrollController(ScrollView view, Location currentLocation) {
        super(view, currentLocation);
    }

    protected void initialize() {
        locationText.setValue(currentLocation.getName());
    }

}
