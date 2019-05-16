package uk.ac.cam.cl.interactiondesign.group10.frontend.components;

import javafx.animation.AnimationTimer;

public class WTimer extends AnimationTimer {

    private WAnimation animation;

    public WTimer(WAnimation animation){
        this.start();
        this.animation = animation;
    }

    @Override
    public void handle(long now) {
        if(!animation.animationComplete){
            animation.nextFrame();
        } else {
            stop();
        }
    }
}
