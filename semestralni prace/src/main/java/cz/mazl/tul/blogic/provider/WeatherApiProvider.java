package cz.mazl.tul.blogic.provider;

import cz.mazl.tul.blogic.provider.weather.HistoricalWeatherData;
import cz.mazl.tul.blogic.provider.weather.WeatherData;

public interface WeatherApiProvider {
    /**
     * Returns current weather in given country and city
     *
     * @param country
     * @param city
     * @return
     */
    WeatherData currentWeather(String country, String city);

    HistoricalWeatherData historicalData();
}
