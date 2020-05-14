package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
import cz.mazl.tul.dto.out.TemperatureDTO;
import org.springframework.web.multipart.MultipartFile;

public interface TemperatureService {

    /**
     * Parses given multipartFile from format CSV. When validation error occurs then will be throw validation error exception.
     * Otherwise it will import csv data into application. Before importing is checked if country and city exists.
     * If country does not exist, then CityNotFoundException will be thrown. If city does not exist then will be created.
     *
     * @param multipartFile
     * @param countryIso
     * @param cityName
     * @throws FileValidationException
     * @throws CountryNotFoundException
     */
    void importTemperatureFromFile(MultipartFile multipartFile, String countryIso, String cityName)
            throws FileValidationException, CountryNotFoundException;

    /**
     * Exports measured temperatures for given country and city. If city not exist, then is thrown CountryNotFoundException.
     * If city is not found then will be thrown CityNotFoundException
     *
     * @param countryIso
     * @param cityName
     * @return
     */

    String exportData(String countryIso, String cityName) throws CountryNotFoundException, CityNotFoundException;

    /**
     * Update current weather for given country identified by countryIso and city
     *
     * @param isoCountry country ISO
     * @param city       city name
     */
    void downloadAndUpdateTemperatureData(String isoCountry, String city);

    /**
     * Update current weather for given country and city entity
     *
     * @param cityEntity    city entity
     * @param countryEntity country entity
     */
    void downloadAndUpdateTemperatureData(CityEntity cityEntity, CountryEntity countryEntity);

    /**
     * Updates temperature record by id
     *
     * @param id
     * @param value
     */
    void updateTemperature(String id, int value);

    /**
     * Removes temperatures record recognised by id.
     *
     * @param id
     */
    void deleteTemperature(String id);

    /**
     * Read temperature value by id
     *
     * @param id
     * @return temperaturDTO
     */
    TemperatureDTO readTemperature(String id);
}
