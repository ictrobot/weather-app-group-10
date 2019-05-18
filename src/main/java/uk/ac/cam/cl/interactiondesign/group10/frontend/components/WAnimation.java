package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

abstract class WAnimation {

    private Image[] frames;
    private ImageView currentFrame = new ImageView();
    private int frameIndex;
    private int frameLimit;
    private boolean animationComplete;

    protected WAnimation(String[] imagePaths, double value, int fitWidth){
        int imageCount = imagePaths.length;
        frames = new Image[imageCount];
        for(int i = 0; i < imageCount; i++){
            frames[i] = new Image(imagePaths[i]);
        }
        frameIndex = 0;
        frameLimit = getFrameLimit(value);
        animationComplete = false;

        currentFrame.setImage(frames[frameIndex]);
        currentFrame.setFitWidth(fitWidth);
        currentFrame.setPreserveRatio(true);
    }

    public void animate(){
        if(!animationComplete){
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> { this.nextFrame();}));
            timeline.setCycleCount(frameLimit);
            timeline.play();
            animationComplete=true;
        }else {
            currentFrame.setImage(frames[frameLimit]);
        }
    }

    abstract int getFrameLimit(double value);

    private ImageView nextFrame() {
        frameIndex++;
        currentFrame.setImage(frames[frameIndex]);
        return currentFrame;
    }

    public ImageView currentFrame(){
        return currentFrame;
    }
}
