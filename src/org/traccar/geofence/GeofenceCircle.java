package org.traccar.geofence;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.traccar.helper.DistanceCalculator;

public class GeofenceCircle extends GeofenceGeometry {

    private double centerLatitude;
    private double centerLongitude;
    private double radius;

    public GeofenceCircle() {
        super();
    }

    public GeofenceCircle(String wkt) throws ParseException {
        super();
        fromWkt(wkt);
    }

    public GeofenceCircle(double latitude, double longitude, double radius) {
        super();
        this.centerLatitude = latitude;
        this.centerLongitude = longitude;
        this.radius = radius;
    }

    @Override
    public boolean containsPoint(double latitude, double longitude) {
        return DistanceCalculator.distance(centerLatitude, centerLongitude, latitude, longitude) <= radius;
    }

    @Override
    public String toWkt() {
        String wkt = "";
        wkt = "CIRCLE (";
        wkt += String.valueOf(centerLatitude);
        wkt += " ";
        wkt += String.valueOf(centerLongitude);
        wkt += ", ";
        DecimalFormat format = new DecimalFormat("0.#");
        wkt += format.format(radius);
        wkt += ")";
        return wkt;
    }

    @Override
    public void fromWkt(String wkt) throws ParseException {
        if (!wkt.startsWith("CIRCLE")) {
            throw new ParseException("Mismatch geometry type", 0);
        }
        String content = wkt.substring(wkt.indexOf("(") + 1, wkt.indexOf(")"));
        if (content == null || content.equals("")) {
            throw new ParseException("No content", 0);
        }
        String[] commatokens = content.split(",");
        if (commatokens.length != 2) {
            throw new ParseException("Not valid content", 0);
        }
        String[] tokens = commatokens[0].split("\\s");
        if (tokens.length != 2) {
            throw new ParseException("Too much or less coordinates", 0);
        }
        try {
            centerLatitude = Double.parseDouble(tokens[0]);
        } catch (NumberFormatException e) {
            throw new ParseException(tokens[0] + " is not a double", 0);
        }
        try {
            centerLongitude = Double.parseDouble(tokens[1]);
        } catch (NumberFormatException e) {
            throw new ParseException(tokens[1] + " is not a double", 0);
        }
        try {
            radius = Double.parseDouble(commatokens[1]);
        } catch (NumberFormatException e) {
            throw new ParseException(commatokens[1] + " is not a double", 0);
        }
    }
}
