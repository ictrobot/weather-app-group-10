package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WAnimation = Weather App Animation
 * Abstract class defining how an animation will occur
 * Frame limit function to be defined by the instance of WAnimate
 */
abstract class WAnimation extends ImageView {
    private List<Image> frames;
    private int frameIndex;
    private boolean animationComplete;

    WAnimation(List<String> imagePaths){
        frames = imagePaths.stream().map(ImageCache::loadImage).collect(Collectors.toList());
        frameIndex = 0;
        animationComplete = false;

        setImage(frames.get(frameIndex));
        setPreserveRatio(true);
    }

    // Start the animation
    public void setupAnimation(double value){
        if(!animationComplete) {
            int frameLimit = getFrameLimit(value);
            if (frameLimit > 0) {
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> this.nextFrame()));
                timeline.setCycleCount(frameLimit);
                timeline.play();

                animationComplete = true;
            }
        }
    }

    abstract int getFrameLimit(double value);

    private void nextFrame() {
        frameIndex++;
        setImage(frames.get(frameIndex));
    }
}
