package cz.mazl.tul.controller.rest;

import cz.mazl.tul.blogic.service.country.CountryService;
import cz.mazl.tul.blogic.service.mongo.SequenceGeneratorService;
import cz.mazl.tul.dto.in.CreateCountryDTO;
import cz.mazl.tul.dto.in.DeleteCountryDTO;
import cz.mazl.tul.dto.in.UpdateCountryDTO;
import cz.mazl.tul.entity.mongo.TemperatureEntity;
import cz.mazl.tul.repository.mongo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryRestController {

    private CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(path = "/api/country/create", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> createCountry(@RequestBody CreateCountryDTO createCountryDTO) {
        long id = countryService.createCountry(createCountryDTO);
        return ResponseEntity.ok("Country has been created with id " + id);
    }

    @RequestMapping(path = "/api/country/delete", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> deleteCountry(@RequestBody DeleteCountryDTO deleteCountryDTO) {
        countryService.deleteCountry(deleteCountryDTO);
        return ResponseEntity.ok("Country has been deleted");
    }

    @RequestMapping(path = "/api/country/update", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> updateCountry(@RequestBody UpdateCountryDTO deleteCountryDTO) {
        countryService.updateCountry(deleteCountryDTO);
        return ResponseEntity.ok("Country has been deleted");
    }

}
