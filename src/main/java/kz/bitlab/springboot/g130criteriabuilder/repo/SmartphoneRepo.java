package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepo extends JpaRepository<Smartphone, Long>, SmartphoneCustomRepo {

    @Query("SELECT COUNT(s) FROM Smartphone s WHERE " +
            "(:title IS NULL OR s.title LIKE %:title%) AND " +
            "(:brand IS NULL OR s.brand = :brand) AND " +
            "(:memory IS NULL OR s.memory = :memory) AND " +
            "(:ram IS NULL OR s.ram = :ram) AND " +
            "(:minPrice IS NULL OR :maxPrice IS NULL OR s.price BETWEEN :minPrice AND :maxPrice)")
    long countFilteredSmartphones(
            @Param("title") String title,
            @Param("brand") Brand brand,
            @Param("memory") Integer memory,
            @Param("ram") Integer ram,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );

}
