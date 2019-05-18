package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ThermoAnimation = Thermometer Animation
 * Instance of WAnimation
 * Provides all images to be in animation as well as frame limit calculation
 */
public class ThermoAnimation extends WAnimation {

    private static List<String> thermoPaths =
            IntStream.range(1, 24).mapToObj(i -> "animations/thermometer/" + i + ".png").collect(Collectors.toList());

    public ThermoAnimation() {
        super(thermoPaths);
    }


    @Override
    int getFrameLimit(double value) {
        double minValue = -5;
        double maxValue = 30;
        double proportion = (value - minValue) / (maxValue - minValue);
        if (proportion < 0) proportion = 0;
        if (proportion > 1) proportion = 1;
        return (int) (proportion * (thermoPaths.size() - 1));
    }
}
