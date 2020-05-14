package cz.mazl.tul.configs.service;

import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.provider.weather.WeatherApiProvider;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TempAggregationTemplate;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.blogic.service.city.CityService;
import cz.mazl.tul.blogic.service.city.ReadOnlyCityService;
import cz.mazl.tul.blogic.service.city.SimpleCityService;
import cz.mazl.tul.blogic.service.country.CountryService;
import cz.mazl.tul.blogic.service.country.ReadOnlyCountryService;
import cz.mazl.tul.blogic.service.country.SimpleCountryService;
import cz.mazl.tul.blogic.service.index.PrepareIndexDataService;
import cz.mazl.tul.blogic.service.index.SimplePrepareIndexDataService;
import cz.mazl.tul.blogic.service.mongo.SequenceGenerator;
import cz.mazl.tul.blogic.service.mongo.SequenceGeneratorService;
import cz.mazl.tul.blogic.service.temperature.ReadOnlyTemperatureService;
import cz.mazl.tul.blogic.service.temperature.SimpleTemperatureService;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;

@Configuration
public class ServiceConfig {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityServiceHelper cityServiceHelper;

    @Bean
    @Profile({"prod", "test"})
    @Autowired
    public TemperatureService temperatureService(MongoOperations mongoOperations, WeatherApiProvider weatherApiProvider) {
        return new SimpleTemperatureService(temperatureRepository, countryRepository,
                sequenceGeneratorService(mongoOperations), weatherApiProvider, cityRepository);
    }

    @Bean
    @Profile("read-only")
    @Autowired
    public TemperatureService temperatureServiceReadOnly(TemperatureRepository temperatureRepository) {
        return new ReadOnlyTemperatureService(temperatureRepository);
    }

    @Bean
    @Autowired
    @Profile({"read-only", "test", "prod"})
    public PrepareIndexDataService prepareIndexDataService(TempAggregationTemplate tempAggregationTemplate) {
        return new SimplePrepareIndexDataService(countryRepository, temperatureRepository, tempAggregationTemplate);
    }

    @Bean
    @Profile({"prod", "test"})
    public CountryService countryService() {
        return new SimpleCountryService(countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Bean
    @Profile("read-only")
    public CountryService countryServiceReadOnly() {
        return new ReadOnlyCountryService(countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Bean
    @Profile({"prod", "test"})
    @Autowired
    public CityService cityService(MongoOperations mongoOperations, WeatherApiProvider weatherApiProvider) {
        return new SimpleCityService(cityRepository, countryRepository, temperatureRepository,
                cityServiceHelper, weatherApiProvider, sequenceGeneratorService(mongoOperations));
    }

    @Bean
    @Profile("read-only")
    public CityService readOnlyCityService() {
        return new ReadOnlyCityService(cityRepository, countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Bean
    @Profile({"prod", "test"})
    public SequenceGenerator sequenceGeneratorService(MongoOperations mongoOperations) {
        return new SequenceGeneratorService(mongoOperations);
    }

}
