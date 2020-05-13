package cz.mazl.tul.blogic.utils;

public class TemperatureUtils {

    public static final double KELVIN_CONST = 273.0;

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - KELVIN_CONST;
    }
}
