package cz.mazl.tul.blogic.service.country;


import cz.mazl.tul.blogic.exception.ReadOnlyModeException;
import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.in.country.UpdateCountryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadOnlyCountryService extends SimpleCountryService {

    private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyCountryService.class);

    public ReadOnlyCountryService(CountryRepository countryRepository, TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper) {
        super(countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Override
    public long createCountry(CreateCountryDTO createCountryDTO) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void deleteCountry(DeleteCountryDTO deleteCountryDTO) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void updateCountry(UpdateCountryDTO updateCountryDTO) {
        LOG.error("Application running in readOnly mode. This method is not allowed.");
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

}
