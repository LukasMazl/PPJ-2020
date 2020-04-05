package cz.mazl.tul.controller.rest;

import cz.mazl.tul.dto.CountryDataDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class IndexDataController {

    @RequestMapping(path = "/api/getData/{countryCode}", method = RequestMethod.POST)
    public List<CountryDataDTO> getCountryData(@PathVariable String countryCode) {
        CountryDataDTO countryDataDTO = new CountryDataDTO();
        countryDataDTO.setCityName(countryCode);
        countryDataDTO.setTemp(55);
        return Arrays.asList(countryDataDTO, countryDataDTO, countryDataDTO);
    }

}
