package uk.ac.cam.cl.interactiondesign.group10.frontend.components.animated;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WThermometer extends WAnimatedImage {

    private static double MIN_TEMP = -2;
    private static double MAX_TEMP = 28;

    public WThermometer() {
        super(50, MIN_TEMP, MAX_TEMP);
    }

    @Override
    List<String> getAnimationFrameNames() {
        return IntStream.range(1, 24).mapToObj(i -> "animations/thermometer/" + i + ".png").collect(Collectors.toList());
    }

}
