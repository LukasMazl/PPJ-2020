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
}
