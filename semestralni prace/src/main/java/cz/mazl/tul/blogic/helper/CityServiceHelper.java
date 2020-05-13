package cz.mazl.tul.blogic.helper;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.dto.out.CityDTO;
import cz.mazl.tul.dto.out.CountryDataDTO;
import cz.mazl.tul.dto.out.TemperatureDTO;

import java.util.ArrayList;
import java.util.List;

import static cz.mazl.tul.blogic.utils.TemperatureUtils.kelvinToCelsius;

public class CityServiceHelper {

    private TemperatureRepository temperatureRepository;

    public CityServiceHelper(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public CountryDataDTO prepareCountryDataFromEntity(CountryEntity countryEntity) {
        CountryDataDTO countryDataDTO = new CountryDataDTO();
        countryDataDTO.setIso(countryEntity.getIso());
        countryDataDTO.setName(countryEntity.getName());
        List<CityEntity> cityEntities = countryEntity.getCityList();
        List<CityDTO> cityDTOS = new ArrayList<>();
        for (CityEntity cityEntity : cityEntities) {
            CityDTO cityDTO = prepareCityDataFromEntity(cityEntity, countryEntity.getIso());
            cityDTOS.add(cityDTO);
        }
        countryDataDTO.setCityDTOList(cityDTOS);
        return countryDataDTO;
    }

    public CityDTO prepareCityDataFromEntity(CityEntity cityEntity, String countryIso) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(cityEntity.getName());
        cityDTO.setCreated(cityEntity.getCreated());
        cityDTO.setLastTemperatureUpdate(cityEntity.getLastTemperatureUpdate());
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryIsoAndCity(countryIso, cityEntity.getName());
        List<TemperatureDTO> temperatureDTOs = new ArrayList<>();
        for (TemperatureEntity temperatureEntity : temperatureEntities) {
            TemperatureDTO temperatureDTO = new TemperatureDTO();
            temperatureDTO.setDate(temperatureEntity.getDay());
            temperatureDTO.setTemperature((int) kelvinToCelsius((double) temperatureEntity.getTemp()));
            temperatureDTOs.add(temperatureDTO);
        }
        cityDTO.setTemperatureDTOList(temperatureDTOs);
        return cityDTO;
    }
}
