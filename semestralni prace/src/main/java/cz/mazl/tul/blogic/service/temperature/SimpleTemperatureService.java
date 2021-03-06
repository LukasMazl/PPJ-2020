package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
import cz.mazl.tul.blogic.exception.RecordDataEntityNotFoundException;
import cz.mazl.tul.blogic.provider.weather.WeatherApiProvider;
import cz.mazl.tul.blogic.provider.weather.current.WeatherData;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.blogic.service.mongo.SequenceGenerator;
import cz.mazl.tul.blogic.utils.TemperatureUtils;
import cz.mazl.tul.dto.out.TemperatureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SimpleTemperatureService implements TemperatureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyTemperatureService.class);

    private static final String CSV_HEADER = "DATE;TEMP\n";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private TemperatureRepository temperatureRepository;
    private CountryRepository countryRepository;
    private SequenceGenerator sequenceGeneratorService;
    private CityRepository cityRepository;
    private WeatherApiProvider weatherApiProvider;

    public SimpleTemperatureService(TemperatureRepository temperatureRepository, CountryRepository countryRepository,
                                    SequenceGenerator sequenceGeneratorService, WeatherApiProvider weatherApiProvider,
                                    CityRepository cityRepository) {
        this.temperatureRepository = temperatureRepository;
        this.countryRepository = countryRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.weatherApiProvider = weatherApiProvider;
        this.cityRepository = cityRepository;
    }

    public SimpleTemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public void importTemperatureFromFile(MultipartFile multipartFile, String countryIso, String cityName) throws CountryNotFoundException, FileValidationException {
        if (multipartFile == null) {
            LOG.error("File for country {} and city {} is not valid", countryIso, cityName);
            throw new FileValidationException("File count not be null!");
        }

        CountryEntity countryEntity = countryRepository.findByIso(countryIso);
        if (countryEntity == null) {
            LOG.error("Country not found with isoCode {}.", countryIso);
            throw new CountryNotFoundException("Country with iso " + countryIso + " does not exist.");
        }

        CityEntity cityEntity = findCityEntityInList(countryEntity, cityName);
        if (cityEntity == null) {
            //If city does not exist create it.
            createNewCity(cityName, countryEntity);
        }

        try {
            String csvFileContent = new String(multipartFile.getBytes());
            List<TemperatureCsvRow> temperatureCsvRows = parseCsvFileContent(csvFileContent);

            for (TemperatureCsvRow temperatureCsvRow : temperatureCsvRows) {
                TemperatureEntity temperatureEntity = new TemperatureEntity();
                temperatureEntity.setCity(cityName);
                temperatureEntity.setCountryIso(countryIso);
                temperatureEntity.setDay(temperatureCsvRow.getDate());
                temperatureEntity.setTemp(temperatureCsvRow.getValue());
                temperatureEntity.setId(Long.toString(sequenceGeneratorService.generateSequence(TemperatureEntity.SEQUENCE_NAME)));
                temperatureRepository.save(temperatureEntity);
            }

        } catch (IOException e) {
            LOG.error("Error processing file with isoCode {} and city {}", countryIso, cityName);
            throw new FileValidationException("Given bytes are not valid.");
        }
    }

    @Override
    public String exportData(String countryIso, String cityName) throws CountryNotFoundException, CityNotFoundException {
        CountryEntity countryEntity = countryRepository.findByIso(countryIso);
        if (countryEntity == null) {
            throw new CountryNotFoundException("Country with iso " + countryIso + " does not exist.");
        }

        CityEntity cityEntity = findCityEntityInList(countryEntity, cityName);
        if (cityEntity == null) {
            throw new CityNotFoundException("City with name " + cityName + " was not found in country " + countryEntity.getName());
        }

        List<TemperatureEntity> temperatureEntities = temperatureRepository.findAllByCountryIsoAndCity(countryIso, cityName);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CSV_HEADER);
        for (TemperatureEntity temperatureEntity : temperatureEntities) {
            stringBuilder.append(DATE_FORMAT.format(temperatureEntity.getDay()));
            stringBuilder.append(";");
            stringBuilder.append(temperatureEntity.getTemp());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    private List<TemperatureCsvRow> parseCsvFileContent(String csvFileContent) {
        String[] lines = csvFileContent.split("\n");
        List<TemperatureCsvRow> temperatureCsvRows = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            TemperatureCsvRow temperatureCsvRow = parseLineFromCSV(line);
            temperatureCsvRows.add(temperatureCsvRow);
        }
        return temperatureCsvRows;
    }

    private TemperatureCsvRow parseLineFromCSV(String line) {
        String[] values = line.split(";");
        TemperatureCsvRow temperatureCsvRow = new TemperatureCsvRow();
        if (values.length != 2) {
            throw new FileValidationException("Invalid count of row values.");
        }

        try {
            temperatureCsvRow.setDate(DATE_FORMAT.parse(values[0]));
            temperatureCsvRow.setValue(Integer.parseInt(values[1].trim()));
            return temperatureCsvRow;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void createNewCity(String city, CountryEntity countryEntity) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(city);
        cityEntity.setCountry(countryEntity);
        cityEntity.setCreated(new Date());
        cityEntity.setLastTemperatureUpdate(new Date());
        countryEntity.getCityList().add(cityEntity);
        countryRepository.save(countryEntity);
    }

    private CityEntity findCityEntityInList(CountryEntity countryEntity, String city) {
        for (CityEntity cityEntity : countryEntity.getCityList()) {
            if (cityEntity.getName().compareTo(city) == 0) {
                return cityEntity;
            }
        }
        return null;
    }

    @Override
    public void downloadAndUpdateTemperatureData(String isoCountry, String city) {
        CountryEntity countryEntity = countryRepository.findByIso(isoCountry);
        if (countryEntity == null) {
            throw new CountryNotFoundException("Country with iso " + isoCountry + " does not exist.");
        }

        CityEntity cityEntity = cityRepository.findByNameAndCountry(city, countryEntity);
        if (cityEntity == null) {
            throw new CityNotFoundException("City with name " + isoCountry + " was not found in country " + countryEntity.getName());
        }

        downloadAndUpdateTemperatureData(cityEntity, countryEntity);
    }

    @Override
    public void downloadAndUpdateTemperatureData(CityEntity cityEntity, CountryEntity countryEntity) {
        Date now = new Date();
        cityEntity.setLastTemperatureUpdate(now);
        cityRepository.save(cityEntity);

        WeatherData weatherData = weatherApiProvider.currentWeather(countryEntity.getIso(), cityEntity.getName());
        TemperatureEntity temperatureEntity = new TemperatureEntity();
        temperatureEntity.setId(Long.toString(sequenceGeneratorService.generateSequence(TemperatureEntity.SEQUENCE_NAME)));
        temperatureEntity.setCountryIso(countryEntity.getIso());
        temperatureEntity.setTemp(weatherData.getMain().getTemp());
        temperatureEntity.setDay(now);
        temperatureEntity.setCity(cityEntity.getName());
        temperatureRepository.save(temperatureEntity);
    }

    @Override
    public void updateTemperature(String id, int value) {
        Optional<TemperatureEntity> temperatureEntityOptional = temperatureRepository.findById(id);
        LOG.debug("Returns {} entities.", temperatureEntityOptional.toString());
        if (!temperatureEntityOptional.isPresent()) {
            throw new RecordDataEntityNotFoundException("Record has not been found for id " + id);
        }
        TemperatureEntity temperatureEntity = temperatureEntityOptional.get();

        temperatureEntity.setTemp(TemperatureUtils.celsiusToKelvin(value));
        temperatureRepository.save(temperatureEntity);
    }

    @Override
    public void deleteTemperature(String id) {
        temperatureRepository.deleteById(id);
    }

    @Override
    public TemperatureDTO readTemperature(String id) {
        Optional<TemperatureEntity> temperatureEntityOptional = temperatureRepository.findById(id);
        LOG.debug("Returns {} entities.", temperatureEntityOptional.toString());
        if (!temperatureEntityOptional.isPresent()) {
            throw new RecordDataEntityNotFoundException("Record has not been found for id " + id);
        }
        TemperatureEntity temperatureEntity = temperatureEntityOptional.get();
        TemperatureDTO temperatureDTO = new TemperatureDTO();
        temperatureDTO.setDate(temperatureEntity.getDay());
        temperatureDTO.setTempId(temperatureEntity.getId());
        temperatureDTO.setTemperature((int) TemperatureUtils.kelvinToCelsius(temperatureEntity.getTemp()));
        return temperatureDTO;
    }
}
