package cz.mazl.tul.jobs;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SimpleUpdateTemperature implements UpdateTemperature {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleUpdateTemperature.class);

    private CityRepository cityRepository;
    private TemperatureService temperatureService;

    public SimpleUpdateTemperature(CityRepository cityRepository, TemperatureService temperatureService) {
        this.cityRepository = cityRepository;
        this.temperatureService = temperatureService;
    }

    @Override
    public void updateTemperature(int limit) {
        LOG.trace("Begin update of weathers for cities");
        Pageable pageable = PageRequest.of(0, limit);
        List<CityEntity> cityEntities = cityRepository.findAllByOrderByLastTemperatureUpdate(pageable);
        LOG.info("About {} cities will be updated.", cityEntities.size());
        cityEntities.stream().forEach((cityEntity -> {
            CountryEntity countryEntity = cityEntity.getCountry();
            temperatureService.downloadAndUpdateTemperatureData(cityEntity, countryEntity);
        }));
    }
}
