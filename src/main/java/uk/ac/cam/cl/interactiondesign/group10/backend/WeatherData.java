package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.LongFunction;

/**
 * Weather data for a location is made up of the current data points, multiple hourly and daily data points
 * And the timezone used to resolve the returned UNIX timestamps
 */
public final class WeatherData {

    private final TimeZone timezone;
    public final WeatherDataPoint current;
    public final List<WeatherDataPoint> hourly;
    public final List<WeatherDataPoint> daily;

    private WeatherData(JsonNode body) {
        JSONObject root = body.getObject();
        // timezone needed to correctly understand timestamps
        timezone = TimeZone.getTimeZone(root.getString("timezone"));
        // parse current data point
        current = new WeatherDataPoint(root.getJSONObject("currently"), t -> "Now");

        // parse hourly data points
        List<WeatherDataPoint> hourlyList = new ArrayList<>();
        for (Object o : root.getJSONObject("hourly").getJSONArray("data")) {
            // don't show any more than 24 hourly points, limits to a day
            if (hourlyList.size() >= 24) break;

            hourlyList.add(new WeatherDataPoint((JSONObject) o, this::resolveHourly));
        }
        this.hourly = Collections.unmodifiableList(hourlyList);

        // parse daily data points
        List<WeatherDataPoint> dailyList = new ArrayList<>();
        for (Object o : root.getJSONObject("daily").getJSONArray("data")) {
            dailyList.add(new WeatherDataPoint((JSONObject) o, this::resolveDaily));
        }
        this.daily = Collections.unmodifiableList(dailyList);
    }

    private String resolveHourly(long timestamp) {
        // used to convert timestamps to understandable strings for hourly forecasts
        // returns a string like "At 1:00AM:"
        DateFormat hourly = new SimpleDateFormat("haa");
        hourly.setTimeZone(timezone);
        return "At " + hourly.format(new Date(timestamp)) + ":";
    }

    private String resolveDaily(long timestamp) {
        // used to convert timestamps to understandable strings for daily forecasts
        // returns a string like "On 20/05:"
        DateFormat daily = new SimpleDateFormat("dd/MM");
        daily.setTimeZone(timezone);
        return "On " + daily.format(new Date(timestamp)) + ":";
    }

    /**
     * Represents the weather forecast at a certain point in time
     */
    public final class WeatherDataPoint {
        public final long timestamp;
        public final String timestampText;
        public final String darkSkySummary;
        public final String darkSkyIcon;
        public final double temperature;
        public final double precipitationProbability; // between 0 and 1

        private WeatherDataPoint(JSONObject datapoint, LongFunction<String> timestampTextFn) {
            timestamp = datapoint.getLong("time") * 1000; // multiply by 1000 to turn in ms
            darkSkySummary = datapoint.getString("summary");
            darkSkyIcon = datapoint.getString("icon");
            precipitationProbability = datapoint.getDouble("precipProbability");

            // use temperature if available. On daily forecasts the low and high temperatures are given instead
            // so fall back to using the daily high temperature
            temperature = datapoint.optDouble("temperature", datapoint.optDouble("temperatureHigh"));

            // function passed in as a parameter used to convert the timestamp into user understandable text
            // different function specified for each type of data point
            timestampText = timestampTextFn.apply(timestamp);
        }

        /**
         * @return string representing temperature in the format "xx.x°C"
         */
        public String getTemperatureString() {
            // \u00B0 is the degrees symbol. Unicode literal used to avoid display issues
            return String.format("%.1f\u00B0C", temperature);
        }

        /**
         * @return string representing precipitation probability in the format "xx.x%"
         */
        public String getPrecipitationString() {
            return String.format("%.1f%%", precipitationProbability * 100);
        }
    }

    // STATIC

    static WeatherData getWeatherData(double latitude, double longitude) throws APIException {
        // construct url using secret API key, latitude and longitude
        String url = "https://api.darksky.net/forecast/" + APIKeys.DARKSKY_WEATHER
                + "/" + latitude + "," + longitude
                + "?lang=en&units=uk2";
        System.out.println("WeatherData: requesting data for " + latitude + ", " + longitude);

        try {
            // Use Unirest to simply make the request and get the response as JSON
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            // Construct a WeatherData instance which interprets the data
            return new WeatherData(response.getBody());
        } catch (UnirestException e) {
            throw new APIException("Weather data request failed ", e);
        } catch (JSONException e) {
            throw new APIException("Failed to parse weather response", e);
        }
    }
}
