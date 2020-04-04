package cz.mazl.tul.blogic.provider;

import cz.mazl.tul.blogic.provider.weather.HistoricalWeatherData;
import cz.mazl.tul.blogic.provider.weather.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class SimpleWeatherApiProvider implements WeatherApiProvider {

    private WeatherProviderProps weatherProviderProps;

    @Autowired
    public SimpleWeatherApiProvider(WeatherProviderProps weatherProviderProps) {
        this.weatherProviderProps = weatherProviderProps;
    }

    @Override
    public WeatherData currentWeather(String country, String city) {
        RestTemplate restTemplate = new RestTemplate();
        String urlAndQuery = prepareUrlAndQuery(country, city);
        ResponseEntity<WeatherData> response
                = restTemplate.getForEntity(urlAndQuery, WeatherData.class);
        return response.getBody();
    }

    private String prepareUrlAndQuery(String country, String city) {
        String query = prepareQuery(country, city);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherProviderProps.getCurrentWeatherUrl())
                .queryParam("q", query)
                .queryParam("appid", weatherProviderProps.getApiKey());

        return builder.toUriString();

    }

    private String prepareQuery(String country, String city) {
        String encodedCountry;
        String encodedCity;
        try {
            encodedCountry = URLEncoder.encode(country, "UTF-8");
            encodedCity = URLEncoder.encode(city, "UTF-8");
            return encodedCity + "," +encodedCountry;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public HistoricalWeatherData historicalData() {
        return null;
    }
}
