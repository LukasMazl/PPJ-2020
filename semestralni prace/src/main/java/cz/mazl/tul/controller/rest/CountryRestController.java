package cz.mazl.tul.controller.rest;

import cz.mazl.tul.blogic.service.country.CountryService;
import cz.mazl.tul.dto.in.country.CreateCountryDTO;
import cz.mazl.tul.dto.in.country.DeleteCountryDTO;
import cz.mazl.tul.dto.in.country.UpdateCountryDTO;
import cz.mazl.tul.dto.out.CountryDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CountryRestController.class);

    private CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(path = "/api/country/create")
    public ResponseEntity<String> createCountry(@RequestBody CreateCountryDTO createCountryDTO) {
        LOG.trace("Create country invoked with {}.", createCountryDTO.toString());
        long id = countryService.createCountry(createCountryDTO);
        LOG.info("Country {} has been created with id {}.", createCountryDTO.getCountryName(), id);
        return ResponseEntity.ok("Country has been created with id " + id);
    }

    @PostMapping(path = "/api/country/delete")
    @Transactional
    public ResponseEntity<String> deleteCountry(@RequestBody DeleteCountryDTO deleteCountryDTO) {
        LOG.trace("Delete country invoked with {}.", deleteCountryDTO.toString());
        countryService.deleteCountry(deleteCountryDTO);
        return ResponseEntity.ok("Country has been deleted");
    }

    @PostMapping(path = "/api/country/update/")
    @Transactional
    public ResponseEntity<String> updateCountry(@RequestBody UpdateCountryDTO updateCountryDTO) {
        LOG.trace("update country invoked with {}.", updateCountryDTO.toString());
        countryService.updateCountry(updateCountryDTO);
        return ResponseEntity.ok("Country has been updated");
    }

    @PostMapping(path = "/api/country/read/{iso}")
    public CountryDataDTO readCountry(@PathVariable String iso) {
        LOG.trace("Read country with iso {}.", iso);
        return countryService.readCountry(iso);
    }

    @PostMapping(path = "/api/country/readAll")
    public List<CountryDataDTO> readAllCountry() {
        LOG.trace("Read all countries.");
        return countryService.readAll();
    }

}
