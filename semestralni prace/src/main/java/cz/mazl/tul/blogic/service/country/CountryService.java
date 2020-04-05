package cz.mazl.tul.blogic.service.country;

import cz.mazl.tul.dto.in.CreateCountryDTO;
import cz.mazl.tul.dto.in.DeleteCountryDTO;

public interface CountryService {

    /**
     * Method creates a new country and returns ID of created country
     *
     * @param createCountryDTO
     * @return id
     */
    long createCountry(CreateCountryDTO createCountryDTO);

    void deleteCountry(DeleteCountryDTO deleteCountryDTO);
}
