package cz.mazl.tul.controller.rest;

import cz.mazl.tul.blogic.service.city.CityService;
import cz.mazl.tul.dto.in.city.CreateCityDTO;
import cz.mazl.tul.dto.in.city.DeleteCityDTO;
import cz.mazl.tul.dto.in.city.ReadCityDTO;
import cz.mazl.tul.dto.in.city.UpdateCityDTO;
import cz.mazl.tul.dto.out.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CountryRestController.class);

    private CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(path = "/api/city/readAllFromCountry/{isoCode}", method = RequestMethod.POST)
    public List<CityDTO> readAllCity(@PathVariable String isoCode) {
        return cityService.readAllFromCountry(isoCode);
    }

    @RequestMapping(path = "/api/city/read")
    public CityDTO readCity(@RequestBody ReadCityDTO readCityDTO){
        return cityService.readCity(readCityDTO);
    }

    @RequestMapping(path = "/api/city/create", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> createCity(@RequestBody CreateCityDTO createCityDTO) {
        LOG.trace("Create city invoked with {}.", createCityDTO.toString());
        cityService.createCity(createCityDTO);
        return ResponseEntity.ok("City has been created");
    }

    @RequestMapping(path = "/api/city/delete", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> deleteCity(@RequestBody DeleteCityDTO deleteCityDTO) {
        LOG.trace("Delete city invoked with {}.", deleteCityDTO.toString());
        cityService.deleteCity(deleteCityDTO);
        return ResponseEntity.ok("City has been deleted");
    }

    @RequestMapping(path = "/api/city/update", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<String> updateCity(@RequestBody UpdateCityDTO updateCityDTO) {
        LOG.trace("Update city invoked with {}.", updateCityDTO.toString());
        cityService.updateCity(updateCityDTO);
        return ResponseEntity.ok("City has been updated");
    }
}
