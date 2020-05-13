package cz.mazl.tul.blogic.service.city;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.exception.CityAlreadyExistException;
import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.helper.CityServiceHelper;
import cz.mazl.tul.blogic.provider.weather.WeatherApiProvider;
import cz.mazl.tul.blogic.provider.weather.current.WeatherData;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.blogic.service.mongo.SequenceGenerator;
import cz.mazl.tul.dto.out.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleCityService implements CityService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleCityService.class);

    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private TemperatureRepository temperatureRepository;
    private CityServiceHelper cityServiceHelper;
    private WeatherApiProvider weatherApiProvider;
    private SequenceGenerator sequenceGenerator;

    public SimpleCityService(CityRepository cityRepository, CountryRepository countryRepository,
                             TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper,
                             WeatherApiProvider weatherApiProvider, SequenceGenerator sequenceGenerator) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
        this.cityServiceHelper = cityServiceHelper;
        this.weatherApiProvider = weatherApiProvider;
        this.sequenceGenerator = sequenceGenerator;
    }

    public SimpleCityService(CityRepository cityRepository, CountryRepository countryRepository,
                             TemperatureRepository temperatureRepository, CityServiceHelper cityServiceHelper) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.temperatureRepository = temperatureRepository;
        this.cityServiceHelper = cityServiceHelper;
    }

    @Override
    public void createCity(City city) {
        LOG.trace("Creating city {}", city);
        CountryEntity countryEntity = countryRepository.findByIso(city.countryIso());
        if (countryEntity == null) {
            LOG.error("County with iso code {} does not exists.", city.countryIso());
            throw new CountryNotFoundException("Country with iso " + city.countryIso() + " does not exist.");
        }

        CityEntity cityEntity = cityRepository.findByNameAndCountry(city.getName(), countryEntity);
        if (cityEntity != null) {
            LOG.error("City with name {} already exists.", city.getName());
            throw new CityAlreadyExistException("City with name " + city.getName() + " already exists.");
        }
        cityEntity = new CityEntity();
        cityEntity.setName(city.getName());
        cityEntity.setCountry(countryEntity);
        cityEntity.setLastTemperatureUpdate(new Date());
        cityEntity.setCreated(new Date());
        cityRepository.save(cityEntity);

        WeatherData weatherData = weatherApiProvider.currentWeather(countryEntity.getIso(), cityEntity.getName());
        TemperatureEntity temperatureEntity = new TemperatureEntity();
        temperatureEntity.setId(sequenceGenerator.generateSequence(TemperatureEntity.SEQUENCE_NAME));
        temperatureEntity.setCity(cityEntity.getName());
        temperatureEntity.setTemp(weatherData.getMain().getTemp());
        temperatureEntity.setDay(new Date());
        temperatureEntity.setCountryIso(countryEntity.getIso());
        temperatureRepository.save(temperatureEntity);
    }

    @Override
    public void updateCity(City city) {
        LOG.trace("Updating city {}", city);
        CountryEntity countryEntity = countryRepository.findByIso(city.countryIso());
        if (countryEntity == null) {
            LOG.error("County with isoCode {} does not exist.", city.countryIso());
            throw new CountryNotFoundException("Country with iso " + city.countryIso() + " does not exist.");
        }

        CityEntity entity = cityRepository.findByNameAndCountry(city.getName(), countryEntity);
        if (entity == null) {
            LOG.error("City with name {} does not exist.", city.getName());
            throw new CityNotFoundException("City with name " + city.getName() + " already exists.");
        }

        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryIsoAndCity(countryEntity.getIso(), entity.getName());
        temperatureEntities.stream().forEach((temperatureEntity) -> temperatureEntity.setCity(city.getName()));
        temperatureRepository.saveAll(temperatureEntities);

        entity.setName(city.getName());
        cityRepository.save(entity);
    }

    @Override
    public CityDTO readCity(City city) {
        LOG.trace("Reading city {}.", city);
        CountryEntity countryEntity = countryRepository.findByIso(city.countryIso());
        if (countryEntity == null) {
            LOG.error("Country with iso {} not found.", city.countryIso());
            throw new CountryNotFoundException("Country with iso " + city.countryIso() + " does not exist.");
        }

        CityEntity entity = cityRepository.findByNameAndCountry(city.getName(), countryEntity);
        if (entity == null) {
            LOG.error("City with name {} does not exist.", city.getName());
            throw new CityNotFoundException("City with name \'" + city.getName() + "\' not found.");
        }
        return cityServiceHelper.prepareCityDataFromEntity(entity, countryEntity.getIso());
    }

    @Override
    public void deleteCity(City city) {
        LOG.trace("Deleting city {}.", city);
        CountryEntity countryEntity = countryRepository.findByIso(city.countryIso());
        if (countryEntity == null) {
            LOG.error("Country {} does not exist.", city.countryIso());
            throw new CountryNotFoundException("Country with iso " + city.countryIso() + " does not exist.");
        }
        cityRepository.deleteByNameAndCountry(city.getName(), countryEntity);
        temperatureRepository.deleteByCountryIsoAndCity(countryEntity.getIso(), city.getName());
    }

    @Override
    public List<CityDTO> readAllFromCountry(String isoCode) {
        LOG.trace("Reading all cities from {}", isoCode);
        CountryEntity countryEntity = countryRepository.findByIso(isoCode);
        if (countryEntity == null) {
            LOG.error("Country {} does not exist.", isoCode);
            throw new CountryNotFoundException("Country with iso " + isoCode + " does not exist.");
        }
        List<CityEntity> entities = cityRepository.findAllByCountry(countryEntity);
        List<CityDTO> cityDTOS = new ArrayList<>();
        entities.stream().forEach(cityEntity -> cityDTOS.add(cityServiceHelper.prepareCityDataFromEntity(cityEntity, isoCode)));
        return cityDTOS;
    }
}
