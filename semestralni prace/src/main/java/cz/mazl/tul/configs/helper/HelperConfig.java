package cz.mazl.tul.configs.helper;

import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class HelperConfig {

    @Bean
    @Autowired
    @Profile({"prod", "test"})
    public CityServiceHelper cityServiceHelper(TemperatureRepository temperatureRepository) {
        return new CityServiceHelper(temperatureRepository);
    }
}
