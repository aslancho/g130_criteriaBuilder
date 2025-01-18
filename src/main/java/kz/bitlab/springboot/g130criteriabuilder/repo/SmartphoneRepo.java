package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepo extends JpaRepository<Smartphone, Long>, SmartphoneCustomRepo {
}
