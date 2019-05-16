package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

public class PrecAnimation extends WAnimation {

    public PrecAnimation(String[] imagePaths, double value, int fitWidth) {
        super(imagePaths, value, fitWidth);
    }

    @Override
    int getFrameLimit(double value) {
        return (int) Math.round(value/10.0);
    }
}
