package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
import cz.mazl.tul.blogic.exception.ReadOnlyModeException;
import cz.mazl.tul.blogic.service.country.ReadOnlyCountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ReadOnlyTemperatureService implements TemperatureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyTemperatureService.class);

    @Override
    public void importTemperatureFromFile(MultipartFile multipartFile, String countryIso, String cityName) throws FileValidationException, CountryNotFoundException {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public String exportData(String countryIso, String cityName) throws CountryNotFoundException, CityNotFoundException {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void downloadAndUpdateTemperatureData(String isoCountry, String city) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void downloadAndUpdateTemperatureData(CityEntity cityEntity, CountryEntity countryEntity) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void updateTemperature(String id, int value) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void deleteTemperature(String id) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }
}
