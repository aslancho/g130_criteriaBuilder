package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
}
