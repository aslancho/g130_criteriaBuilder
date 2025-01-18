package kz.bitlab.springboot.g130criteriabuilder.service;

import kz.bitlab.springboot.g130criteriabuilder.entity.Country;
import kz.bitlab.springboot.g130criteriabuilder.repo.CountryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepo countryRepo;

    List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

}
