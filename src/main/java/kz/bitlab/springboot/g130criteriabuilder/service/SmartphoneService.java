package kz.bitlab.springboot.g130criteriabuilder.service;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import kz.bitlab.springboot.g130criteriabuilder.repo.SmartphoneRepo;
import lombok.RequiredArgsConstructor;
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

    public List<Smartphone> dynamicSearch(Smartphone smartphone) {
        return smartphoneRepo.dynamicSearch(smartphone);
    }

    public Set<Integer> getAllSmartphoneMemories() {
        return smartphoneRepo.findAll().stream().map(Smartphone::getMemory).sorted().collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Integer> getAllSmartphoneRams() {
        return smartphoneRepo.findAll().stream().map(Smartphone::getRam).sorted().collect(Collectors.toCollection(TreeSet::new));
    }
}
