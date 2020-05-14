package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.exception.*;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.blogic.service.country.ReadOnlyCountryService;
import cz.mazl.tul.dto.out.TemperatureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class ReadOnlyTemperatureService extends SimpleTemperatureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyTemperatureService.class);

    public ReadOnlyTemperatureService(TemperatureRepository temperatureRepository) {
        super(temperatureRepository);
    }

    @Override
    public void importTemperatureFromFile(MultipartFile multipartFile, String countryIso, String cityName) throws FileValidationException, CountryNotFoundException {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public String exportData(String countryIso, String cityName) throws CountryNotFoundException, CityNotFoundException {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void downloadAndUpdateTemperatureData(String isoCountry, String city) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void downloadAndUpdateTemperatureData(CityEntity cityEntity, CountryEntity countryEntity) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void updateTemperature(String id, int value) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void deleteTemperature(String id) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

}
