package cz.mazl.tul.blogic.service.index;

import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.repository.mongo.TempAggregationTemplate;
import cz.mazl.tul.blogic.utils.TemperatureUtils;
import cz.mazl.tul.dto.out.index.IndexCountryDataDTO;
import cz.mazl.tul.dto.out.index.IndexDataDTO;
import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePrepareIndexDataService implements PrepareIndexDataService {

    private CountryRepository countryRepository;
    private TemperatureRepository temperatureRepository;
    private TempAggregationTemplate tempAggregationTemplate;

    public SimplePrepareIndexDataService(CountryRepository countryRepository, TemperatureRepository temperatureRepository,
                                         TempAggregationTemplate tempAggregationTemplate) {
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
        this.tempAggregationTemplate = tempAggregationTemplate;
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
            indexCountryDataDTO.setLastUpdate(cityEntity.getLastTemperatureUpdate());
            TemperatureEntity temperatureEntity = temperatureRepository.findTopByCountryIsoAndCityOrderByDay(countryEntity.getIso(), cityEntity.getName());
            indexCountryDataDTO.setTemp((int) TemperatureUtils.kelvinToCelsius(temperatureEntity.getTemp()));
            indexCountryDataDTO.setAvgTemp(getAvgTemperature(cityEntity.getName(), countryEntity.getIso()));
            setMaxTemperature(indexCountryDataDTO, cityEntity, countryEntity);
            setMinTemperature(indexCountryDataDTO, cityEntity, countryEntity);
            indexCountryDataDTOS.add(indexCountryDataDTO);
        }
        return indexCountryDataDTOS;
    }

    private void setMinTemperature(IndexCountryDataDTO indexCountryDataDTO, CityEntity cityEntity, CountryEntity countryEntity) {
        TemperatureEntity temperatureEntity = temperatureRepository.findTopByCountryIsoAndCityOrderByTempAsc(countryEntity.getIso(), cityEntity.getName());
        indexCountryDataDTO.setMinDate(temperatureEntity.getDay());
        indexCountryDataDTO.setMinTemp((int)TemperatureUtils.kelvinToCelsius(temperatureEntity.getTemp()));
    }

    private void setMaxTemperature(IndexCountryDataDTO indexCountryDataDTO, CityEntity cityEntity, CountryEntity countryEntity) {
        TemperatureEntity temperatureEntity = temperatureRepository.findTopByCountryIsoAndCityOrderByTempDesc(countryEntity.getIso(), cityEntity.getName());
        indexCountryDataDTO.setMaxDate(temperatureEntity.getDay());
        indexCountryDataDTO.setMaxTemp((int)TemperatureUtils.kelvinToCelsius(temperatureEntity.getTemp()));
    }

    private int getAvgTemperature(String city, String country) {
        return (int) TemperatureUtils.kelvinToCelsius(tempAggregationTemplate.getAvgTempleForCity(city, country));
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
