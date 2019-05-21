package uk.ac.cam.cl.interactiondesign.group10.frontend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * An activity is made up the string and image to be displayed.
 * This class loads them from a JSON data file and provides a function to randomly select one relevant to the weather
 */
public class Activity {

  public final String activityString;
  public final Image activityImage;

  private Activity(String activityString, String activityImagePath) {
    this.activityString = activityString;
    this.activityImage = ImageCache.loadImage("activities/" + activityImagePath);
  }

  // STATIC

  private static final Map<String, List<Activity>> activityStrings = new HashMap<>();
  private static final Activity DEFAULT_ACTIVITY;
  private static final Random RANDOM = new Random();

  static {
    DEFAULT_ACTIVITY = new Activity("Why not read a book?", "books.png");

    // try loading JSON data file
    // { "icon-name": [{"text": "", "image": ""}, ...], ...}
    try (InputStream jsonInStream = Activity.class.getResourceAsStream("/data/activities.json")) {
      JsonParser parser = new JsonParser();
      JsonObject json = parser.parse(new InputStreamReader(jsonInStream, StandardCharsets.UTF_8)).getAsJsonObject();
      // interpret the JSON data and store in the map from weather icon string to a list of activities
      for (String weatherIcon : json.keySet()) {
        ArrayList<Activity> activities = new ArrayList<>();
        for (JsonElement element : json.get(weatherIcon).getAsJsonArray()) {
          JsonObject entry = element.getAsJsonObject();
          Activity activity = new Activity(entry.get("text").getAsString(), entry.get("image").getAsString());
          activities.add(activity);
        }
        activityStrings.put(weatherIcon, activities);
      }
    } catch (Exception e) {
      // not completely fatal, can fall back on the default activity
      System.err.println("Failed to load activities from JSON data file");
      e.printStackTrace();
    }
  }

  public static Activity getActivity(String currentIcon) {
    // lookup weather icon to find a list of relevant activities and randomly return one
    if (activityStrings.containsKey(currentIcon)) {
      List<Activity> list = activityStrings.get(currentIcon);
      int index = RANDOM.nextInt(list.size());
      return list.get(index);
    }
    // if the weather icon is not in the map return a default activity
    return DEFAULT_ACTIVITY;
  }
}
