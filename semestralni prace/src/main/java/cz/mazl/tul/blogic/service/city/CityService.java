package cz.mazl.tul.blogic.service.city;

import cz.mazl.tul.dto.out.CityDTO;

import java.util.List;

/**
 * This interface serve like CRUD interface for City entity.
 */
public interface CityService {

    /**
     * Method for creating new city entity
     *
     * @param city
     */
    void createCity(City city);

    /**
     * Method for updating city entity
     *
     * @param city
     * @param originName
     */
    void updateCity(String originName, City city);

    /**
     * Reads all data about city with temperature from Mongodb
     *
     * @param city name
     * @return CityDTO
     */
    CityDTO readCity(City city);

    /**
     * Deletes city identifinted by name and iso of country
     *
     * @param city
     */
    void deleteCity(City city);

    /**
     * Read All cities from given countrz represented by isoCode
     *
     * @param isoCode
     * @return CityDTO
     */
    List<CityDTO> readAllFromCountry(String isoCode);
}
