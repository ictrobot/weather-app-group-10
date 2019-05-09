package uk.ac.cam.cl.interactiondesign.group10.backend;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class GetLocation {

  private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(APIKeys.GOOGLE_GEOCODING).build();

  public static LatLng search(String s) {
    GeocodingResult[] await;
    try {
      await = GeocodingApi.geocode(context, s).await();
    } catch (Exception e) {
      throw new RuntimeException("Failed to lookup location", e);
    }
    if (await == null || await.length == 0) return null;
    return await[0].geometry.location;
  }

  public static LatLng detect() {
    return null;
  }
}
