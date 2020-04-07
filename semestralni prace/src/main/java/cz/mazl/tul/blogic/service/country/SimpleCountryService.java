package cz.mazl.tul.blogic.service.country;

import cz.mazl.tul.dto.in.CreateCountryDTO;
import cz.mazl.tul.dto.in.DeleteCountryDTO;
import cz.mazl.tul.dto.in.UpdateCountryDTO;
import cz.mazl.tul.dto.out.CountryDataDTO;
import cz.mazl.tul.entity.db.CityEntity;
import cz.mazl.tul.entity.db.CountryEntity;
import cz.mazl.tul.repository.CountryRepository;
import cz.mazl.tul.repository.mongo.TemperatureRepository;

import java.util.ArrayList;
import java.util.List;

public class SimpleCountryService implements CountryService {

    private CountryRepository countryRepository;
    private TemperatureRepository temperatureRepository;

    public SimpleCountryService(CountryRepository countryRepository, TemperatureRepository temperatureRepository) {
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public long createCountry(CreateCountryDTO countryDTO) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setIso(countryDTO.getIso());
        countryEntity.setName(countryDTO.getCountryName());

        List<CityEntity> cityEntityList = prepareCityEntity(countryDTO.getCityDTOList(), countryEntity);
        countryEntity.setCityList(cityEntityList);
        countryEntity = countryRepository.save(countryEntity);
        return countryEntity.getId();
    }

    private List<CityEntity> prepareCityEntity(List<String> cityList, CountryEntity countryEntity) {
        List<CityEntity> cityEntities = new ArrayList<>();
        if (cityList != null) {
            for (String str : cityList) {
                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(str);
                cityEntity.setCountry(countryEntity);
                cityEntities.add(cityEntity);
            }
        }
        return cityEntities;
    }

    @Override
    public void deleteCountry(DeleteCountryDTO deleteCountryDTO) {
        this.countryRepository.deleteByNameOrIso(deleteCountryDTO.getCountryName(), deleteCountryDTO.getIso());
    }

    @Override
    public CountryDataDTO readCountry(String iso) {
        CountryEntity countryEntity = countryRepository.findByIso(iso);
        return prepareCountryDataFromEntity(countryEntity);
    }

    @Override
    public List<CountryDataDTO> readAll() {
        Iterable<CountryEntity> countryEntities = countryRepository.findAll();
        List<CountryDataDTO> countryDataDTOS = new ArrayList<>();
        for (CountryEntity countryEntity : countryEntities) {
            CountryDataDTO countryDataDTO = prepareCountryDataFromEntity(countryEntity);
            countryDataDTOS.add(countryDataDTO);
        }
        return countryDataDTOS;
    }

    private CountryDataDTO prepareCountryDataFromEntity(CountryEntity countryEntity) {
        CountryDataDTO countryDataDTO = new CountryDataDTO();
        countryDataDTO.setIso(countryEntity.getIso());
        countryDataDTO.setName(countryEntity.getName());
        List<CityEntity> cityEntities = countryEntity.getCityList();

        return countryDataDTO;
    }

    @Override
    public void updateCountry(UpdateCountryDTO updateCountryDTO) {

    }
}
