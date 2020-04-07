package cz.mazl.tul.repository;

import cz.mazl.tul.entity.db.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    Long deleteByNameOrIso(String name, String iso);

    CountryEntity findByIso(String iso);
}
