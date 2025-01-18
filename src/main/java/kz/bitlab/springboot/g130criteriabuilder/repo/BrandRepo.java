package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}
