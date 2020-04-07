package cz.mazl.tul.blogic.service.index;

import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.dto.out.index.IndexCountryDataDTO;
import cz.mazl.tul.dto.out.index.IndexDataDTO;
import cz.mazl.tul.entity.db.CityEntity;
import cz.mazl.tul.entity.db.CountryEntity;
import cz.mazl.tul.entity.mongo.TemperatureEntity;
import cz.mazl.tul.repository.CountryRepository;
import cz.mazl.tul.repository.mongo.TemperatureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePrepareIndexDataService implements PrepareIndexDataService {

    private CountryRepository countryRepository;
    private TemperatureRepository temperatureRepository;

    public SimplePrepareIndexDataService(CountryRepository countryRepository, TemperatureRepository temperatureRepository) {
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public IndexDataDTO prepare() {
        IndexDataDTO indexDataDTO = new IndexDataDTO();
        Iterable<CountryEntity> countryEntities = countryRepository.findAll();
        Map<String, String> data = new HashMap<>();
        countryEntities.forEach((country) ->
                data.put(country.getIso(), country.getName())
        );

        String selectedCountry = "";
        List<IndexCountryDataDTO> indexCountryDataDTOS;
        if (data.size() > 0) {
            CountryEntity countryEntity = countryEntities.iterator().next();
            selectedCountry = countryEntity.getIso();

            //Add fetching from mongoDB
            List<CityEntity> cityEntityList = countryEntity.getCityList();
            indexCountryDataDTOS = getDataForCities(cityEntityList, countryEntity);
        } else {
            indexCountryDataDTOS = new ArrayList<>();
        }

        indexDataDTO.setCountryCodes(data);
        indexDataDTO.setCodeSelectedCountry(selectedCountry);
        indexDataDTO.setIndexCountryDataDTOList(indexCountryDataDTOS);
        return indexDataDTO;
    }

    private List<IndexCountryDataDTO> getDataForCities(List<CityEntity> cityEntities, CountryEntity countryEntity) {
        List<IndexCountryDataDTO> indexCountryDataDTOS = new ArrayList<>();

        for (CityEntity cityEntity : cityEntities) {
            IndexCountryDataDTO indexCountryDataDTO = new IndexCountryDataDTO();
            indexCountryDataDTO.setCityName(cityEntity.getName());
            TemperatureEntity temperatureEntity = temperatureRepository.findTopByCountryIsoAndCityOrderByDay(countryEntity.getIso(), cityEntity.getName());
            indexCountryDataDTO.setTemp(temperatureEntity.getTemp());
            indexCountryDataDTOS.add(indexCountryDataDTO);
        }
        return indexCountryDataDTOS;
    }

    @Override
    public List<IndexCountryDataDTO> getDataForCountry(String countryCode) {
        if (countryCode == null) {
            throw new IllegalArgumentException("Country code could not be null.");
        }
        CountryEntity countryEntity = countryRepository.findByIso(countryCode);
        if (countryEntity == null) {
            throw new CountryNotFoundException("Country with iso " + countryCode + " was not found.");
        }
        List<CityEntity> cityEntities = countryEntity.getCityList();
        return getDataForCities(cityEntities, countryEntity);
    }
}
