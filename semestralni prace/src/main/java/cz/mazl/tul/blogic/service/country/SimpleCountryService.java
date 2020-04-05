package cz.mazl.tul.blogic.service.country;

import cz.mazl.tul.dto.in.CreateCountryDTO;
import cz.mazl.tul.dto.in.DeleteCountryDTO;
import cz.mazl.tul.entity.CityEntity;
import cz.mazl.tul.entity.CountryEntity;
import cz.mazl.tul.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleCountryService implements CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public SimpleCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public long createCountry(CreateCountryDTO countryDTO) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setIso(countryDTO.getIso());
        countryEntity.setName(countryDTO.getCountryName());

        List<CityEntity> cityEntityList = prepareCityEntity(countryDTO.getCityDTOList(), countryEntity);
        countryEntity.setCityList(cityEntityList);
        countryEntity = countryRepository.save(countryEntity);
        return countryEntity.getId();
    }

    private List<CityEntity> prepareCityEntity(List<String> cityList, CountryEntity countryEntity) {
        List<CityEntity> cityEntities = new ArrayList<>();
        for (String str : cityList) {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(str);
            cityEntity.setCountry(countryEntity);
            cityEntities.add(cityEntity);
        }
        return cityEntities;
    }

    @Override
    public void deleteCountry(DeleteCountryDTO deleteCountryDTO) {
        this.countryRepository.deleteByNameOrIso(deleteCountryDTO.getCountryName(), deleteCountryDTO.getIso());
    }
}
