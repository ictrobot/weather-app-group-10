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

public class WeatherData {

    private final TimeZone timezone;
    public final WeatherDataPoint current;
    public final List<WeatherDataPoint> hourly;
    public final List<WeatherDataPoint> daily;

    private WeatherData(JsonNode body) {
        JSONObject root = body.getObject();
        timezone = TimeZone.getTimeZone(root.getString("timezone"));
        current = new WeatherDataPoint(root.getJSONObject("currently"), t -> "Now");

        List<WeatherDataPoint> hourlyList = new ArrayList<>();
        for (Object o : root.getJSONObject("hourly").getJSONArray("data")) {
            if (hourlyList.size() >= 24) break;
            hourlyList.add(new WeatherDataPoint((JSONObject) o, this::resolveHourly));
        }
        this.hourly = Collections.unmodifiableList(hourlyList);

        List<WeatherDataPoint> dailyList = new ArrayList<>();
        for (Object o : root.getJSONObject("daily").getJSONArray("data")) {
            dailyList.add(new WeatherDataPoint((JSONObject) o, this::resolveDaily));
        }
        this.daily = Collections.unmodifiableList(dailyList);
    }

    private String resolveHourly(long timestamp) {
        DateFormat hourly = new SimpleDateFormat("haa");
        hourly.setTimeZone(timezone);
        return "At " + hourly.format(new Date(timestamp)) + ":";
    }

    private String resolveDaily(long timestamp) {
        DateFormat daily = new SimpleDateFormat("dd/MM");
        daily.setTimeZone(timezone);
        return "On " + daily.format(new Date(timestamp)) + ":";
    }

    static WeatherData getWeatherData(double latitude, double longitude) throws APIException {
        String url = "https://api.darksky.net/forecast/" + APIKeys.DARKSKY_WEATHER
                + "/" + latitude + "," + longitude
                + "?lang=en&units=uk2";
        System.out.println("WeatherData: requesting data for " + latitude + ", " + longitude);

        try {
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            return new WeatherData(response.getBody());
        } catch (UnirestException e) {
            throw new APIException("Weather data request failed ", e);
        } catch (JSONException e) {
            throw new APIException("Failed to parse weather response", e);
        }
    }

    public class WeatherDataPoint {
        public final long timestamp;
        public final String timestampText;
        public final String darkSkySummary;
        public final String darkSkyIcon;
        public final double temperature;
        public final double precipitationProbability; // between 0 and 1

        private WeatherDataPoint(JSONObject datapoint, LongFunction<String> timestampTextFn) {
            timestamp = datapoint.getLong("time") * 1000; // multiply by 1000 to turn in ms
            timestampText = timestampTextFn.apply(timestamp);
            darkSkySummary = datapoint.getString("summary");
            darkSkyIcon = datapoint.getString("icon"); // TODO show high and low daily temperature?
            temperature = datapoint.optDouble("temperature", datapoint.optDouble("temperatureHigh"));
            precipitationProbability = datapoint.getDouble("precipProbability");
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
            return String.format("%.1f%%", precipitationProbability);
        }
    }
}
