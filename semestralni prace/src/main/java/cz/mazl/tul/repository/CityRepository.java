package cz.mazl.tul.repository;

import cz.mazl.tul.entity.db.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {
}
