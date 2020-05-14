package cz.lukas.mazl.test;

import cz.mazl.tul.Application;
import cz.mazl.tul.blogic.service.country.CountryService;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.out.CountryDataDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
@Order(1)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void createService() {
        CreateCountryDTO createCountryDTO = new CreateCountryDTO();
        createCountryDTO.setIso("cz");
        createCountryDTO.setCountryName("Czech republic");

        countryService.createCountry(createCountryDTO);
    }

    @Test
    public void deleteService() {
        DeleteCountryDTO deleteCountryDTO = new DeleteCountryDTO();
        deleteCountryDTO.setIso("cz");
        deleteCountryDTO.setCountryName("Czech republic");

        countryService.deleteCountry(deleteCountryDTO);
    }

    @Test
    public void readAll() {
        List<CountryDataDTO> countryDataDTOList = countryService.readAll();
        assert countryDataDTOList.size() > 0;
    }
}
