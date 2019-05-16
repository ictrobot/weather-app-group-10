package uk.ac.cam.cl.interactiondesign.group10.frontend.components.animated;


import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uk.ac.cam.cl.interactiondesign.group10.frontend.ImageCache;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

abstract class WAnimatedImage extends ImageView {

    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(runnable -> {
        Thread thread = new Thread(runnable, "Animation scheduling thread");
        thread.setDaemon(true);
        return thread;
    });

    private final List<Image> images;
    private final double minTargetValue;
    private final double maxTargetValue;
    private int currentImageIndex;
    private ScheduledFuture scheduledTask;
    private final DoubleProperty targetProperty = new SimpleDoubleProperty(); // e.g. target temperature for thermometer

    WAnimatedImage(int frameTime, double minTargetValue, double maxTargetValue) {
        super();
        this.minTargetValue = minTargetValue;
        this.maxTargetValue = maxTargetValue;

        // pre cache all animation frames
        images = getAnimationFrameNames().stream().map(ImageCache::loadImage).collect(Collectors.toList());

        // default to first image
        setImageIndex(0);

        // default sizing
        setFitWidth(50);
        setPreserveRatio(true);

        // when the target value is changed, reset animation to the start
        targetProperty.addListener(observable -> setImageIndex(0));

        // when the parent of this component changes
        parentProperty().addListener(observable -> {
            Parent parent = getParent();
            // if we have been removed from a parent and we have a scheduled task, cancel it
            if (parent == null && scheduledTask != null) {
                scheduledTask.cancel(false);
            }
            // if we were just added to a parent, setup scheduled task
            if (parent != null) {
                scheduledTask = executor.scheduleAtFixedRate(
                        () -> Platform.runLater(this::callback), 0, frameTime, TimeUnit.MILLISECONDS);
            }
        });
    }

    private void callback() {
        // calculate proportion filled using the target, minimum & maximum
        double proportion = (targetProperty.get() - minTargetValue) / (maxTargetValue - minTargetValue);
        // find the corresponding target image index
        int targetImage;
        if (proportion < 0) {
            targetImage = 0;
        } else if (proportion > 1) {
            targetImage = images.size() - 1;
        } else {
            targetImage = (int) (proportion * (images.size() - 1));
        }
        // increment current index if needed
        if (currentImageIndex < targetImage) setImageIndex(currentImageIndex + 1);
    }

    private void setImageIndex(int index) {
        setImage(images.get(index));
        currentImageIndex = index;
    }

    /** Used for the animation frames */
    abstract List<String> getAnimationFrameNames();

    public DoubleProperty getTargetProperty() {
        return targetProperty;
    }

}
