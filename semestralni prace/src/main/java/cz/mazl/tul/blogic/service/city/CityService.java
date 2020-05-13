package cz.mazl.tul.blogic.service.city;

import cz.mazl.tul.dto.out.CityDTO;

import java.util.List;

public interface CityService {
    void createCity(City city);

    void updateCity(City city);

    CityDTO readCity(City city);

    void deleteCity(City city);

    List<CityDTO> readAllFromCountry(String isoCode);
}
