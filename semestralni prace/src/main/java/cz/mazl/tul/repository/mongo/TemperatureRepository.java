package cz.mazl.tul.repository.mongo;

import cz.mazl.tul.entity.mongo.TemperatureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TemperatureRepository extends MongoRepository<TemperatureEntity, String> {
    List<TemperatureEntity> findAllByCountryIsoAndCity(String countryIso, String city);

    TemperatureEntity findTopByCountryIsoAndCityOrderByDay(String countryIso, String city);
}
