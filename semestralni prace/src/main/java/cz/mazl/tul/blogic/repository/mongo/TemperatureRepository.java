package cz.mazl.tul.blogic.repository.mongo;

import cz.mazl.tul.blogic.entity.mongo.TemperatureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemperatureRepository extends MongoRepository<TemperatureEntity, String> {
    List<TemperatureEntity> findAllByCountryIsoAndCity(String countryIso, String city);

    TemperatureEntity findTopByCountryIsoAndCityOrderByDay(String countryIso, String city);

    List<TemperatureEntity> findAllByCountryIso(String countryIso);

    void deleteByCountryIso(String countryIso);

    void deleteByCountryIsoAndCity(String countryIso, String city);

    TemperatureEntity findTopByCountryIsoAndCityOrderByTempAsc(String countryIso, String city);

    TemperatureEntity findTopByCountryIsoAndCityOrderByTempDesc(String countryIso, String city);

}
