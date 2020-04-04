package cz.mazl.tul.blogic.provider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cz.mazl.tul.weather.provider")
public class WeatherProviderProps {

    private String apiKey;
    private String currentWeatherUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCurrentWeatherUrl() {
        return currentWeatherUrl;
    }

    public void setCurrentWeatherUrl(String currentWeatherUrl) {
        this.currentWeatherUrl = currentWeatherUrl;
    }
}
