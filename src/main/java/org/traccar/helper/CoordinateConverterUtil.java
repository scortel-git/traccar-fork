package org.traccar.helper;
import jakarta.inject.Inject;

import java.util.Objects;

public class CoordinateConverterUtil {

    @Inject
    public CoordinateConverterUtil() {}
    private static final long serialVersionUID = -4336524405835332577L;
    public static final String[] ORIENTATIONS = "N/S/E/W".split("/");

    public String decimalToDMS(double coord, String type) {

        double mod = coord % 1;
        int intPart = (int) coord;

        String degrees = String.valueOf(intPart);

        coord = mod * 60;
        mod = coord % 1;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String minutes = String.valueOf(intPart);

        coord = mod * 60;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String seconds = String.valueOf(intPart);
        String output = Math.abs(Integer.parseInt(degrees)) + "° " + minutes + "' " + seconds + "\"";
        String direction = null;
        if (Objects.equals(type, "latitude")) {
            direction = coord > 0 ? CoordinateConverterUtil.ORIENTATIONS[0] : CoordinateConverterUtil.ORIENTATIONS[1];
        } else if (Objects.equals(type, "longitude")) {
            direction = coord > 0 ? CoordinateConverterUtil.ORIENTATIONS[2] : CoordinateConverterUtil.ORIENTATIONS[3];
        }
        assert direction != null;
        return output.concat(" ").concat(direction);
    }



}
