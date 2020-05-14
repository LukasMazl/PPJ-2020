package cz.mazl.tul.blogic.provider.weather;

import cz.mazl.tul.blogic.provider.weather.historical.HistoricalWeatherData;
import cz.mazl.tul.blogic.provider.weather.current.WeatherData;

import java.util.Date;

public interface WeatherApiProvider {
    /**
     * Returns current weather in given country and city
     *
     * @param country
     * @param city
     * @return
     */
    WeatherData currentWeather(String country, String city);


    /**
     * For using this is need premium account
     *
     * @param country
     * @param city
     * @param type
     * @param start
     * @param end
     * @param count
     * @return
     */
    @Deprecated
    HistoricalWeatherData historicalData(String country, String city, String type, Date start, Date end, String count);
}
