package cz.mazl.tul.configs.servise;

import cz.mazl.tul.blogic.service.country.CountryService;
import cz.mazl.tul.blogic.service.country.SimpleCountryService;
import cz.mazl.tul.blogic.service.index.PrepareIndexDataService;
import cz.mazl.tul.blogic.service.index.SimplePrepareIndexDataService;
import cz.mazl.tul.blogic.service.mongo.DevelSequenceGenerator;
import cz.mazl.tul.blogic.service.mongo.SequenceGenerator;
import cz.mazl.tul.blogic.service.mongo.SequenceGeneratorService;
import cz.mazl.tul.blogic.service.temperature.SimpleTemperatureService;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import cz.mazl.tul.repository.CityRepository;
import cz.mazl.tul.repository.CountryRepository;
import cz.mazl.tul.repository.mongo.TemperatureRepository;
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

    @Bean
    @Profile("prod")
    @Autowired
    public TemperatureService temperatureService(MongoOperations mongoOperations) {
        return new SimpleTemperatureService(temperatureRepository, countryRepository, sequenceGeneratorService(mongoOperations));
    }

    @Bean
    public PrepareIndexDataService prepareIndexDataService() {
        return new SimplePrepareIndexDataService(countryRepository, temperatureRepository);
    }

    @Bean
    @Profile("devel")
    public TemperatureService temperatureServiceDevel() {
        return new SimpleTemperatureService(temperatureRepository, countryRepository, sequenceGeneratorServiceDevel());
    }

    @Bean
    public CountryService countryService() {
        return new SimpleCountryService(countryRepository, temperatureRepository);
    }

    @Autowired
    @Profile("prod")
    @Bean
    public SequenceGenerator sequenceGeneratorService(MongoOperations mongoOperations) {
        return new SequenceGeneratorService(mongoOperations);
    }

    @Profile("devel")
    @Bean
    public SequenceGenerator sequenceGeneratorServiceDevel() {
        return new DevelSequenceGenerator();
    }
}
