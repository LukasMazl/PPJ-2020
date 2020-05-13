package cz.mazl.tul.configs.provider;

import cz.mazl.tul.blogic.provider.weather.SimpleWeatherApiProvider;
import cz.mazl.tul.blogic.provider.weather.WeatherApiProvider;
import cz.mazl.tul.blogic.provider.weather.WeatherProviderProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProviderConfig {

    @Bean
    @Autowired
    public WeatherApiProvider weatherApiProvider(WeatherProviderProps weatherProviderProps) {
        return new SimpleWeatherApiProvider(weatherProviderProps);
    }
}
