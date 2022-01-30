package io.github.soheshts.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.soheshts.model.Country;
import org.apache.commons.math3.util.Precision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Util {


    public static List<Country> readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//            String jsonText = readAll(rd);
            Gson gson = new Gson();
            List<Country> countries = gson.fromJson(rd, new TypeToken<List<Country>>() {
            }.getType());

            return countries;
        }
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        System.out.println(Precision.round(c*r,2));
        // calculate the result
        return Precision.round(c*r,2);
    }

}
