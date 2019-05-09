package uk.ac.cam.cl.interactiondesign.group10.backend;

import java.io.InputStream;
import java.util.Properties;

public class APIKeys {

    static String GOOGLE_GEOCODING;
    static String DARKSKY_WEATHER;

    public static void loadAPIKeys() {
        String google = null, darkSky = null;

        try (InputStream is = APIKeys.class.getResourceAsStream("/secrets.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            google = properties.getProperty("GOOGLE_GEOCODING_API_KEY");
            darkSky = properties.getProperty("DARK_SKY_API_KEY");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (google == null || darkSky == null || google.isEmpty() || darkSky.isEmpty())
            throw new RuntimeException("Missing API Keys");
        GOOGLE_GEOCODING = google;
        DARKSKY_WEATHER = darkSky;
    }

}
