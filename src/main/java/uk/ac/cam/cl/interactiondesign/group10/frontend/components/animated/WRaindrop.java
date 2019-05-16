package uk.ac.cam.cl.interactiondesign.group10.frontend.components.animated;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WRaindrop extends WAnimatedImage {

    private static double MIN_PRECIP = 0;
    private static double MAX_PRECIP = 1;

    public WRaindrop() {
        super(50, MIN_PRECIP, MAX_PRECIP);
    }

    @Override
    List<String> getAnimationFrameNames() {
        return IntStream.range(0, 10).mapToObj(i -> "animations/raindrop/" + (i * 10) + ".png").collect(Collectors.toList());
    }
}
