package kz.bitlab.springboot.g130criteriabuilder.controller;

import jakarta.validation.Valid;
import kz.bitlab.springboot.g130criteriabuilder.dto.PageRequestDTO;
import kz.bitlab.springboot.g130criteriabuilder.dto.SmartphoneFilterDTO;
import kz.bitlab.springboot.g130criteriabuilder.entity.Brand;
import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import kz.bitlab.springboot.g130criteriabuilder.model.SortOption;
import kz.bitlab.springboot.g130criteriabuilder.service.BrandService;
import kz.bitlab.springboot.g130criteriabuilder.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Validated
public class HomeController {

    private final SmartphoneService smartphoneService;
    private final BrandService brandService;

    @GetMapping("/")
    public String home(@ModelAttribute @Valid PageRequestDTO pageRequest,
                       @ModelAttribute SmartphoneFilterDTO filter,
                       Model model) {

        Smartphone filteredSmartphone = createFilteredSmartphone(filter);
        addAttributesToModel(model, filter, pageRequest);

        Page<Smartphone> smartphonePage = smartphoneService
                .getSmartphonesPage(filteredSmartphone, pageRequest.toPageRequest());
        model.addAttribute("smartphonePage", smartphonePage);

        return "home";
    }

    private Smartphone createFilteredSmartphone(SmartphoneFilterDTO filter) {
        Smartphone filteredSmartphone = new Smartphone();
        filteredSmartphone.setTitle(filter.getTitle());
        filteredSmartphone.setMemory(filter.getMemory());
        filteredSmartphone.setRam(filter.getRam());

        if (filter.getBrandName() != null && !filter.getBrandName().isEmpty()) {
            Brand brand = brandService.getBrandByName(filter.getBrandName());
            filteredSmartphone.setBrand(brand);
        }

        setPriceRange(filteredSmartphone, filter.getPriceRange());

        return filteredSmartphone;
    }

    private void setPriceRange(Smartphone smartphone, String priceRange) {
        if (priceRange != null && !priceRange.isEmpty()) {
            String[] range = priceRange.split("-");
            if (range.length == 2) {
                try {
                    smartphone.setMinPrice(Double.parseDouble(range[0]));
                    smartphone.setMaxPrice(Double.parseDouble(range[1]));
                } catch (NumberFormatException e) {
                    // Логирование
                }
            }
        }
    }

    private void addAttributesToModel(Model model, SmartphoneFilterDTO filter, PageRequestDTO pageRequest) {
        model.addAttribute("allSmartphoneBrandNames", brandService.getAllBrandNames());
        model.addAttribute("allSmartphoneMemories", smartphoneService.getAllSmartphoneMemories());
        model.addAttribute("allSmartphoneRams", smartphoneService.getAllSmartphoneRams());

        model.addAttribute("selectedBrand", filter.getBrandName());
        model.addAttribute("selectedMemory", filter.getMemory());
        model.addAttribute("selectedRam", filter.getRam());
        model.addAttribute("selectedTitle", filter.getTitle());
        model.addAttribute("selectedPriceRange", filter.getPriceRange());

        model.addAttribute("selectedSortField", pageRequest.getSortField());
        model.addAttribute("selectedSortDirection", pageRequest.getSortDirection());

        // Заменяем простой список полей на список опций
        List<SortOption> sortOptions = List.of(
                new SortOption("title", "По названию"),
                new SortOption("price", "По цене"),
                new SortOption("memory", "По памяти"),
                new SortOption("ram", "По оперативной памяти")
        );
        model.addAttribute("sortOptions", sortOptions);

        // Для направления сортировки используем Map
        Map<String, String> sortDirections = Map.of(
                "ASC", "По возрастанию",
                "DESC", "По убыванию"
        );
        model.addAttribute("sortDirections", sortDirections);

    }
}
