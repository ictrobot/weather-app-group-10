package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

public class PrecAnimation extends WAnimation {

    private static String[] raindropPaths = {
            "images/Animations/raindrop/0.png",
            "images/Animations/raindrop/10.png",
            "images/Animations/raindrop/20.png",
            "images/Animations/raindrop/30.png",
            "images/Animations/raindrop/40.png",
            "images/Animations/raindrop/50.png",
            "images/Animations/raindrop/60.png",
            "images/Animations/raindrop/70.png",
            "images/Animations/raindrop/80.png",
            "images/Animations/raindrop/90.png",
            "images/Animations/raindrop/100.png"
    };

    public PrecAnimation(double value, int fitWidth) {
        super(raindropPaths, value, fitWidth);
    }

    @Override
    int getFrameLimit(double value) {
        return (int) Math.round(value/10.0);
    }
}
