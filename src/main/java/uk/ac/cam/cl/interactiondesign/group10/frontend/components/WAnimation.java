package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class WAnimation {

    private ImageView[] frames;
    private int currentFrame;
    private int frameLimit;
    public boolean animationComplete;

    protected WAnimation(String[] imagePaths, double value, int fitWidth){
        int imageCount = imagePaths.length;
        frames = new ImageView[imageCount];
        for(int i = 0; i < imageCount; i++){
            frames[i] = new ImageView(new Image(imagePaths[i]));
        }
        currentFrame = -1;
        frameLimit = getFrameLimit(value);
        animationComplete = false;
    }

    abstract int getFrameLimit(double value);

    public ImageView nextFrame() {
        if(currentFrame<frameLimit){
            currentFrame++;
        }else {
            animationComplete=true;
        }
        return frames[currentFrame];
    }

    public ImageView currentFrame(){
        return frames[currentFrame];
    }
}
