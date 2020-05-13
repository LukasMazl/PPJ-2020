package cz.mazl.tul.blogic.service.country;


import cz.mazl.tul.blogic.exception.ReadOnlyModeException;
import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.in.country.UpdateCountryDTO;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;

public class ReadOnlyCountryService extends SimpleCountryService {

    public ReadOnlyCountryService(CountryRepository countryRepository, TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper) {
        super(countryRepository, temperatureRepository, cityServiceHelper);
    }

    @Override
    public long createCountry(CreateCountryDTO createCountryDTO) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void deleteCountry(DeleteCountryDTO deleteCountryDTO) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

    @Override
    public void updateCountry(UpdateCountryDTO updateCountryDTO) {
        throw new ReadOnlyModeException("Application running in readOnly mode. This method is not allowed.");
    }

}
