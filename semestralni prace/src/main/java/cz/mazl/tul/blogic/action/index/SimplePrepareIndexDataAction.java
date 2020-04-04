package cz.mazl.tul.blogic.action.index;

import cz.mazl.tul.dto.CountryDataDTO;
import cz.mazl.tul.dto.IndexDataDTO;
import cz.mazl.tul.entity.CityEntity;
import cz.mazl.tul.entity.CountryEntity;
import cz.mazl.tul.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimplePrepareIndexDataAction implements PrepareIndexDataAction {

    private CountryRepository countryRepository;

    @Autowired
    public SimplePrepareIndexDataAction(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public IndexDataDTO prepare() {
        IndexDataDTO indexDataDTO = new IndexDataDTO();
        Iterable<CountryEntity> countryEntities = countryRepository.findAll();
        Map<String, String> data = new HashMap<>();
        countryEntities.forEach((country) ->
                data.put(country.getIso(), country.getName())
        );

        String selectedCountry = "";
        List<CountryDataDTO> countryDataDTOS;
        if(data.size() > 0) {
            CountryEntity countryEntity = countryEntities.iterator().next();
            selectedCountry = countryEntity.getIso();

            //Add fetching from mongoDB
            List<CityEntity> cityEntityList = countryEntity.getCityList();
            countryDataDTOS = new ArrayList<>();

            CountryDataDTO countryDataDTO = new CountryDataDTO();
            countryDataDTO.setCityName("Loucen");
            countryDataDTO.setTemp(38);
            countryDataDTOS.add(countryDataDTO);
        } else {
            countryDataDTOS = new ArrayList<>();
        }

        indexDataDTO.setCountryCodes(data);
        indexDataDTO.setCodeSelectedCountry(selectedCountry);
        indexDataDTO.setCountryDataDTOList(countryDataDTOS);
        return indexDataDTO;
    }
}
