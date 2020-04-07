package cz.mazl.tul.blogic.service.temperature;

import cz.mazl.tul.blogic.exception.CityNotFoundException;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
import cz.mazl.tul.blogic.service.mongo.SequenceGenerator;
import cz.mazl.tul.entity.db.CityEntity;
import cz.mazl.tul.entity.db.CountryEntity;
import cz.mazl.tul.entity.mongo.TemperatureEntity;
import cz.mazl.tul.repository.CountryRepository;
import cz.mazl.tul.repository.mongo.TemperatureRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SimpleTemperatureService implements TemperatureService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy");

    private TemperatureRepository temperatureRepository;
    private CountryRepository countryRepository;
    private SequenceGenerator sequenceGeneratorService;

    public SimpleTemperatureService(TemperatureRepository temperatureRepository, CountryRepository countryRepository, SequenceGenerator sequenceGeneratorService) {
        this.temperatureRepository = temperatureRepository;
        this.countryRepository = countryRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    public void importTemperatureFromFile(MultipartFile multipartFile, String countryIso, String cityName) throws CountryNotFoundException, FileValidationException {
        if(multipartFile == null) {
            throw new FileValidationException("File count not be null!");
        }

        CountryEntity countryEntity = countryRepository.findByIso(countryIso);
        if (countryEntity == null) {
            throw new CountryNotFoundException("Country with iso " + countryIso + " does not exist.");
        }

        CityEntity cityEntity = findCityEntityInList(countryEntity, cityName);
        if (cityEntity == null) {
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
                temperatureEntity.setId(sequenceGeneratorService.generateSequence(TemperatureEntity.SEQUENCE_NAME));
                temperatureRepository.save(temperatureEntity);
            }

        } catch (IOException e) {
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
        stringBuilder.append(getCSVHeader());
        for (TemperatureEntity temperatureEntity : temperatureEntities) {
            stringBuilder.append(DATE_FORMAT.format(temperatureEntity.getDay()));
            stringBuilder.append(";");
            stringBuilder.append(temperatureEntity.getTemp());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    //FIXME make dynamic
    private String getCSVHeader() {
        return "DATE;TEMP\n";
    }

    private List<TemperatureCsvRow> parseCsvFileContent(String csvFileContent) {
        String[] lines = csvFileContent.split("\n");
        List<TemperatureCsvRow> temperatureCsvRows = new ArrayList<>();
        for (String line : lines) {
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
            System.out.println(values[1]);
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
}
