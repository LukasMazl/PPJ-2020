package cz.mazl.tul.blogic.service.city;

import cz.mazl.tul.blogic.exception.ReadOnlyModeException;
import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadOnlyCityService extends SimpleCityService {

    private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyCityService.class);

    public ReadOnlyCityService(CityRepository cityRepository, CountryRepository countryRepository, TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper) {
        super(cityRepository, countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Override
    public void createCity(City city) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void updateCity(String originName, City city) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void deleteCity(City city) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }
}
