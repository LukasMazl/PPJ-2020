package cz.lukas.mazl.test;

import cz.mazl.tul.Application;
import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.ServiceException;
import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.CountryRepository;
import cz.mazl.tul.blogic.service.city.City;
import cz.mazl.tul.blogic.service.city.CityService;
import cz.mazl.tul.dto.in.city.CreateCityDTO;
import cz.mazl.tul.dto.out.CityDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
@Order(3)
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Before
    @Transactional
    public void initBefore() {
        countryRepository.deleteAll();
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName("Republika kongo");
        countryEntity.setIso("kg");

        CountryEntity countryEntity1 = new CountryEntity();
        countryEntity1.setName("Slovakia");
        countryEntity1.setIso("sk");

        countryRepository.save(countryEntity);
        countryRepository.save(countryEntity1);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName("Kongopolis");
        cityEntity.setCountry(countryEntity);
        cityEntity.setCreated(new Date());
        cityEntity.setLastTemperatureUpdate(new Date());
        cityRepository.save(cityEntity);
    }

    @Order(1)
    @Test(expected = CountryNotFoundException.class)
    public void createCity() {
        CreateCityDTO city = new CreateCityDTO();
        city.setCountryIso("ls");
        city.setName("Bratislava");
        cityService.createCity(city);
    }


    @Order(2)
    @Test(expected = ServiceException.class)
    public void createCity2() {
        CreateCityDTO city = new CreateCityDTO();
        city.setCountryIso("kg");
        city.setName("Bratislava");
        cityService.createCity(city);
    }

    @Test
    public void createCity3() {
        CreateCityDTO city = new CreateCityDTO();
        city.setCountryIso("sk");
        city.setName("Bratislava");
        cityService.createCity(city);
    }

    @Order(3)
    @Test
    public void readCity() {
        CityDTO cityDTO = cityService.readCity(new City() {
            @Override
            public String getName() {
                return "Kongopolis";
            }

            @Override
            public String countryIso() {
                return "kg";
            }
        });
        assert cityDTO != null;
    }
}
