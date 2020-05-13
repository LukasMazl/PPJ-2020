package cz.mazl.tul.blogic.service.country;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.exception.CountryAlreadyExistsException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.in.country.UpdateCountryDTO;
import cz.mazl.tul.dto.out.CountryDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SimpleCountryService implements CountryService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCountryService.class);

    private CountryRepository countryRepository;
    private TemperatureRepository temperatureRepository;
    private CityServiceHelper cityServiceHelper;

    public SimpleCountryService(CountryRepository countryRepository, TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper) {
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
        this.cityServiceHelper = cityServiceHelper;
    }

    @Override
    public long createCountry(CreateCountryDTO countryDTO) {
        LOG.trace("Creating country with {}.", countryDTO);
        CountryEntity countryEntity = countryRepository.findByIso(countryDTO.getIso());
        if (countryEntity != null) {
            LOG.error("County with iso code {} does not exists.", countryDTO.getIso());
            throw new CountryAlreadyExistsException("Country " + countryDTO.getIso() + " already exists.");
        }

        countryEntity = new CountryEntity();
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
        LOG.trace("Deleting country with {}", deleteCountryDTO);
        countryRepository.deleteByNameOrIso(deleteCountryDTO.getCountryName(), deleteCountryDTO.getIso());
        temperatureRepository.deleteByCountryIso(deleteCountryDTO.getIso());
    }

    @Override
    public CountryDataDTO readCountry(String iso) {
        LOG.trace("Reading country with iso {}.", iso);
        CountryEntity countryEntity = countryRepository.findByIso(iso);
        if (countryEntity == null) {
            LOG.error("County with iso code {} does not exists.", iso);
            throw new CountryNotFoundException("Country with iso " + iso + " not exist.");
        }
        return cityServiceHelper.prepareCountryDataFromEntity(countryEntity);
    }

    @Override
    public List<CountryDataDTO> readAll() {
        LOG.trace("Reading all countries.");
        Iterable<CountryEntity> countryEntities = countryRepository.findAll();
        List<CountryDataDTO> countryDataDTOS = new ArrayList<>();
        for (CountryEntity countryEntity : countryEntities) {
            CountryDataDTO countryDataDTO = cityServiceHelper.prepareCountryDataFromEntity(countryEntity);
            countryDataDTOS.add(countryDataDTO);
        }
        return countryDataDTOS;
    }


    @Override
    public void updateCountry(UpdateCountryDTO updateCountryDTO) {
        LOG.trace("Updating country with params {}.", updateCountryDTO);
        CountryEntity countryEntity = countryRepository.findByIso(updateCountryDTO.getOriginIso());
        if (countryEntity == null) {
            LOG.error("County with iso code {} does not exists.", updateCountryDTO.getOriginIso());
            throw new CountryNotFoundException("Country with iso " + updateCountryDTO.getIso() + " not exist.");
        }
        countryEntity.setIso(updateCountryDTO.getIso());
        countryEntity.setName(updateCountryDTO.getName());
        countryRepository.save(countryEntity);
        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryIso(updateCountryDTO.getIso());
        temperatureEntities.forEach((temperatureEntity -> temperatureEntity.setCountryIso(updateCountryDTO.getIso())));
        temperatureRepository.saveAll(temperatureEntities);
    }
}
