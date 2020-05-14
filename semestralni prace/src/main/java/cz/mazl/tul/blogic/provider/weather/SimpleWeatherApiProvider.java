package cz.mazl.tul.blogic.provider.weather;

import cz.mazl.tul.blogic.exception.ServiceException;
import cz.mazl.tul.blogic.provider.weather.current.WeatherData;
import cz.mazl.tul.blogic.provider.weather.historical.HistoricalWeatherData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Service
public class SimpleWeatherApiProvider implements WeatherApiProvider {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleWeatherApiProvider.class);

    private WeatherProviderProps weatherProviderProps;

    @Autowired
    public SimpleWeatherApiProvider(WeatherProviderProps weatherProviderProps) {
        this.weatherProviderProps = weatherProviderProps;
    }

    @Override
    public WeatherData currentWeather(String country, String city) {
        RestTemplate restTemplate = new RestTemplate();
        String urlAndQuery = prepareUrlAndQuery(country, city);
        LOG.debug("Getting response from url {}", urlAndQuery);
        try {
            ResponseEntity<WeatherData> response
                    = restTemplate.getForEntity(urlAndQuery, WeatherData.class);
            return response.getBody();
        } catch (HttpClientErrorException clientErrorException) {
            throw new ServiceException("City " + city + "have not been found for country " + country);
        }
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
            return encodedCity + "," + encodedCountry;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public HistoricalWeatherData historicalData(String country, String city, String type, Date start, Date end, String count) {
        RestTemplate restTemplate = new RestTemplate();
        String urlAndQuery = prepareUrlAndQueryForHistoricalData(country, city, type, start, end, count);
        LOG.debug("Getting response from url {}", urlAndQuery);
        ResponseEntity<HistoricalWeatherData> response
                = restTemplate.getForEntity(urlAndQuery, HistoricalWeatherData.class);
        return response.getBody();
    }

    private String prepareUrlAndQueryForHistoricalData(String country, String city, String type, Date start, Date end, String count) {
        String query = prepareQuery(country, city);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherProviderProps.getHistoricalWeatherUrl())
                .queryParam("q", query)
                .queryParam("start", start.getTime())
                .queryParam("end", end.getTime())
                .queryParam("appid", weatherProviderProps.getApiKey());
        if(type != null) {
            builder.queryParam("type", type);
        }
        if(count != null) {
            builder.queryParam("count", count);
        }
        return builder.toUriString();
    }

}
