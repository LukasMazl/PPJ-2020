package cz.mazl.tul.configs.servise;

import cz.mazl.tul.repository.CityRepository;
import cz.mazl.tul.repository.CountryRepository;
import cz.mazl.tul.repository.mongo.TemperatureRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("devel")
@Configuration
public class RepositoryConfig {

    @MockBean
    private TemperatureRepository temperatureRepository;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CountryRepository countryRepository;
}
