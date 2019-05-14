package uk.ac.cam.cl.interactiondesign.group10.frontend;

import java.util.*;

public class Activities {

  private static Map<String, List<String>> activityStrings = new HashMap<>();
  private static String UNKNOWN = "Unknown";
  private static Random RANDOM = new Random();

  static {
    List<String> list;

    activityStrings.put("clear-day", list = new ArrayList<>());
    list.add("Clear example");

    activityStrings.put("rain", list = new ArrayList<>());
    list.add("Rain example");

    activityStrings.put("snow", list = new ArrayList<>());
    list.add("Snow example");

    activityStrings.put("sleet", list = new ArrayList<>());
    list.add("Sleet example");

    activityStrings.put("wind", list = new ArrayList<>());
    list.add("Wind example");

    activityStrings.put("fog", list = new ArrayList<>());
    list.add("Fog example");

    activityStrings.put("cloudy", list = new ArrayList<>());
    activityStrings.put("partly-cloudy-day", list);
    list.add("Cloudy example");

    activityStrings.put("partly-cloudy-night", list = new ArrayList<>());
    activityStrings.put("clear-night", list);
    list.add("Night example");
  }

  public static String getActivity(String currentIcon) {
    if (activityStrings.containsKey(currentIcon)) {
      List<String> list = activityStrings.get(currentIcon);
      int index = RANDOM.nextInt(list.size());
      return list.get(index);
    }
    return UNKNOWN;
  }
}
