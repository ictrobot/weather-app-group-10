package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * PrecAnimation = Precipitation Animation
 * Instance of WAnimation
 * Provides all images to be in animation as well as frame limit calculation
 */
public class PrecAnimation extends WAnimation {

    private static List<String> raindropPaths =
            IntStream.range(0, 10).mapToObj(i -> "animations/raindrop/" + (i * 10) + ".png").collect(Collectors.toList());

    public PrecAnimation() {
        super(raindropPaths);
    }

    @Override
    int getFrameLimit(double value) {
        if (value < 0) value = 0;
        if (value > 1) value = 1;
        return (int) (value * (raindropPaths.size() - 1));
    }
}
