package kz.bitlab.springboot.g130criteriabuilder.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SmartphoneCustomRepoImpl implements SmartphoneCustomRepo {

    private final EntityManager entityManager;

    @Override
    public Page<Smartphone> dynamicSearch(Smartphone filteredSmartphone, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Smartphone> cq = cb.createQuery(Smartphone.class);
        Root<Smartphone> root = cq.from(Smartphone.class); // FROM smartphones

        List<Predicate> predicates = new ArrayList<>();

        if (filteredSmartphone.getTitle() != null && !filteredSmartphone.getTitle().isEmpty()) {
            String[] words = filteredSmartphone.getTitle().toLowerCase().split("\\s+"); // Разделяем на слова
            for (String word : words) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + word + "%"));
            }
        }

        if (filteredSmartphone.getBrand() != null) {
            Join<Smartphone, Brand> brandJoin = root.join("brand");
            predicates.add(cb.equal(brandJoin.get("name"), filteredSmartphone.getBrand().getName()));
        }

        if (filteredSmartphone.getMemory() != null) {
            predicates.add(cb.equal(root.get("memory"), filteredSmartphone.getMemory()));
        }

        if (filteredSmartphone.getRam() != null) {
            predicates.add(cb.equal(root.get("ram"), filteredSmartphone.getRam()));
        }

        if (filteredSmartphone.getMinPrice() != null && filteredSmartphone.getMaxPrice() != null) {
            predicates.add(
                    cb.between(root.get("price"),
                            filteredSmartphone.getMinPrice(),
                            filteredSmartphone.getMaxPrice())
            );
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Smartphone> query = entityManager.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        long totalRecords = getTotalRecords(filteredSmartphone);
        return new PageImpl<>(query.getResultList(), pageable, totalRecords);
    }

    private long getTotalRecords(Smartphone filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Smartphone> root = countQuery.from(Smartphone.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
            String[] words = filter.getTitle().toLowerCase().split("\\s+");
            for (String word : words) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + word + "%"));
            }
        }

        if (filter.getBrand() != null) {
            Join<Smartphone, Brand> brandJoin = root.join("brand");
            predicates.add(cb.equal(brandJoin.get("name"), filter.getBrand().getName()));
        }

        if (filter.getMemory() != null) {
            predicates.add(cb.equal(root.get("memory"), filter.getMemory()));
        }

        if (filter.getRam() != null) {
            predicates.add(cb.equal(root.get("ram"), filter.getRam()));
        }

        if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
            predicates.add(cb.between(root.get("price"),
                    filter.getMinPrice(),
                    filter.getMaxPrice()));
        }

        countQuery.select(cb.count(root));
        countQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
