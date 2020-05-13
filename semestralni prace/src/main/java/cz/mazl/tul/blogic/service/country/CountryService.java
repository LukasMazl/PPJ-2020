package cz.mazl.tul.blogic.service.country;

import cz.mazl.tul.dto.out.CountryDataDTO;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.in.country.UpdateCountryDTO;

import java.util.List;

public interface CountryService {

    /**
     * Method creates a new country and returns ID of created country
     *
     * @param createCountryDTO
     * @return id
     */
    long createCountry(CreateCountryDTO createCountryDTO);

    /**
     * Deletes certain country based on iso or country name
     *
     * @param deleteCountryDTO
     */
    void deleteCountry(DeleteCountryDTO deleteCountryDTO);


    /**
     * Updates country based on originIso in UpdateCountryDTO
     *
     * @param updateCountryDTO
     */
    void updateCountry(UpdateCountryDTO updateCountryDTO);

    /**
     * This method returns all countries persisted in DB
     *
     * @return
     */
    List<CountryDataDTO> readAll();

    /**
     * Method returns country based on iso country code
     *
     * @param iso country code
     * @return
     */
    CountryDataDTO readCountry(String iso);
}
