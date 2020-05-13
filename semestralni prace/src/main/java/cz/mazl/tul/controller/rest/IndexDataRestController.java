package cz.mazl.tul.controller.rest;

import cz.mazl.tul.blogic.service.index.PrepareIndexDataService;
import cz.mazl.tul.dto.out.index.IndexCountryDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexDataRestController {

    private PrepareIndexDataService prepareIndexDataService;

    @Autowired
    public IndexDataRestController(PrepareIndexDataService prepareIndexDataService) {
        this.prepareIndexDataService = prepareIndexDataService;
    }

    @RequestMapping(path = "/api/getData/{countryCode}", method = RequestMethod.POST)
    public List<IndexCountryDataDTO> getCountryData(@PathVariable String countryCode) {
        List<IndexCountryDataDTO> indexCountryDataDTO = prepareIndexDataService.getDataForCountry(countryCode);
        return indexCountryDataDTO;
    }

}
