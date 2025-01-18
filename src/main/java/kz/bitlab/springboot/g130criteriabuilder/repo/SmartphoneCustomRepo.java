package kz.bitlab.springboot.g130criteriabuilder.repo;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;

import java.util.List;

public interface SmartphoneCustomRepo {

    List<Smartphone> dynamicSearch(Smartphone smartphone);

}
