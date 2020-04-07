package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
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
}