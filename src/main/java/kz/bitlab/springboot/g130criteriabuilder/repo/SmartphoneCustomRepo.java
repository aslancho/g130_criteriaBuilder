package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import org.springframework.data.domain.*;

public interface SmartphoneCustomRepo {

    Page<Smartphone> dynamicSearch(Smartphone filteredSmartphone, Pageable pageable);

}
