package uk.ac.cam.cl.interactiondesign.group10.frontend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;

import java.io.InputStreamReader;
import java.util.*;

public class Activities {

  private static final Map<String, List<Activity>> activityStrings = new HashMap<>();
  private static final Activity DEFAULT_ACTIVITY;
  private static final Random RANDOM = new Random();

  static {
    DEFAULT_ACTIVITY = new Activity("Why not read a book?", "books.png");

    JsonObject json = new JsonParser().parse(new InputStreamReader(Activities.class.getResourceAsStream("/data/activities.json"))).getAsJsonObject();

    for (String weatherIcon : json.keySet()) {
      ArrayList<Activity> activities = new ArrayList<>();
      for (JsonElement element : json.get(weatherIcon).getAsJsonArray()) {
        JsonObject entry = element.getAsJsonObject();
        Activity activity = new Activity(entry.get("text").getAsString(), entry.get("image").getAsString());
        activities.add(activity);
      }
      activityStrings.put(weatherIcon, activities);
    }
  }

  public static Activity getActivity(String currentIcon) {
    if (activityStrings.containsKey(currentIcon)) {
      List<Activity> list = activityStrings.get(currentIcon);
      int index = RANDOM.nextInt(list.size());
      return list.get(index);
    }
    return DEFAULT_ACTIVITY;
  }

  public static class Activity {
    public final String activityString;
    public final Image activityImage;

    public Activity(String activityString, String activityImagePath) {
      this.activityString = activityString;
      this.activityImage = ImageCache.loadImage("activities/" + activityImagePath);
    }
  }
}
