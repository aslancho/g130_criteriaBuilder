package kz.bitlab.springboot.g130criteriabuilder.service;

import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import kz.bitlab.springboot.g130criteriabuilder.repo.BrandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepo brandRepo;

    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    public Set<String> getAllBrandNames() {
        return brandRepo.findAll().stream().map(Brand::getName).collect(Collectors.toCollection(TreeSet::new));
    }

    public Brand getBrandByName(String name) {
        return brandRepo.findByName(name);
    }
}
