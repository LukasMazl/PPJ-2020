package cz.mazl.tul.blogic.repository;

import cz.mazl.tul.blogic.entity.db.CityEntity;
import cz.mazl.tul.blogic.entity.db.CountryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {
    CityEntity findByNameAndCountry(String name, CountryEntity countryEntity);

    void deleteByNameAndCountry(String name, CountryEntity countryEntity);

    List<CityEntity> findAllByCountry(CountryEntity countryEntity);

    List<CityEntity> findAllByOrderByLastTemperatureUpdate(Pageable pageable);
}
