package kz.bitlab.springboot.g130criteriabuilder.service;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import kz.bitlab.springboot.g130criteriabuilder.repo.SmartphoneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SmartphoneService {

    private final SmartphoneRepo smartphoneRepo;

    public Page<Smartphone> dynamicSearch(Smartphone filteredSmartphone, Pageable pageable) {
        return smartphoneRepo.dynamicSearch(filteredSmartphone, pageable);
    }

    public Set<Integer> getAllSmartphoneMemories() {
        return smartphoneRepo.findAll().stream().map(Smartphone::getMemory).sorted().collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Integer> getAllSmartphoneRams() {
        return smartphoneRepo.findAll().stream().map(Smartphone::getRam).sorted().collect(Collectors.toCollection(TreeSet::new));
    }

    // Пагинация
    public Page<Smartphone> getSmartphonesPage(Smartphone filteredSmartphone, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return smartphoneRepo.dynamicSearch(filteredSmartphone, pageable);
    }

}
