package kz.bitlab.springboot.g130criteriabuilder.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SmartphoneCustomRepoImpl implements SmartphoneCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Smartphone> dynamicSearch(Smartphone smartphone) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Smartphone> cq = cb.createQuery(Smartphone.class);
        Root<Smartphone> root = cq.from(Smartphone.class); // FROM smartphones

        List<Predicate> predicates = new ArrayList<>();

        if (smartphone.getTitle() != null && !smartphone.getTitle().isEmpty()) {
            String[] words = smartphone.getTitle().toLowerCase().split("\\s+"); // Разделяем на слова
            for (String word : words) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + word + "%"));
            }
        }

        if (smartphone.getBrand() != null) {
            Join<Smartphone, Brand> brandJoin = root.join("brand");
            predicates.add(cb.equal(brandJoin.get("name"), smartphone.getBrand().getName()));
        }

        if (smartphone.getMemory() != null) {
            predicates.add(cb.equal(root.get("memory"), smartphone.getMemory()));
        }

        if (smartphone.getRam() != null) {
            predicates.add(cb.equal(root.get("ram"), smartphone.getRam()));
        }

        if (smartphone.getMinPrice() != null && smartphone.getMaxPrice() != null) {
            predicates.add(
                    cb.between(root.get("price"),
                            smartphone.getMinPrice(),
                            smartphone.getMaxPrice())
            );
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
