package cz.mazl.tul.controller.rest;

import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureRestController {
    private static final Logger LOG = LoggerFactory.getLogger(TemperatureRestController.class);

    private TemperatureService temperatureService;

    public TemperatureRestController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @RequestMapping(path = "/api/temp/download/{country}/{city}")
    public HttpEntity<String> downloadTemperature(@PathVariable String country, @PathVariable String city) {
        LOG.trace("Updating temperature for country {} and city {}", country, city);
        temperatureService.downloadAndUpdateTemperatureData(country, city);
        return ResponseEntity.ok("Temperature has been updated.");
    }

    @RequestMapping(path = "/api/temp/update/{id}/{value}")
    public HttpEntity<String> updateTemperature(@PathVariable String id, @PathVariable Integer value) {
        LOG.trace("Updating temperature by id {} on {}.", id, value);
        temperatureService.updateTemperature(id, value);
        return ResponseEntity.ok("Temperature has been updated.");
    }

    @RequestMapping(path = "/api/temp/delete/{id}")
    public HttpEntity<String> deleteTemperature(@PathVariable String id) {
        LOG.trace("Deleting temperature by id {}", id);
        temperatureService.deleteTemperature(id);
        return ResponseEntity.ok("Temperature has been deleted.");
    }
}
