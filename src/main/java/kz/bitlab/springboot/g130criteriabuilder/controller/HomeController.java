package kz.bitlab.springboot.g130criteriabuilder.controller;

import kz.bitlab.springboot.g130criteriabuilder.entity.Smartphone;
import kz.bitlab.springboot.g130criteriabuilder.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SmartphoneService smartphoneService;

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortDirection,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer memory,
            @RequestParam(required = false) Integer ram,
            @RequestParam(required = false) String priceRange,
            Model model
    ) {
        // Создаем объект фильтра
        Smartphone filteredSmartphone = new Smartphone();
        filteredSmartphone.setTitle(title);
        filteredSmartphone.setBrand(brand);
        filteredSmartphone.setMemory(memory);
        filteredSmartphone.setRam(ram);

        // Парсим диапазон цен
        if (priceRange != null && !priceRange.isEmpty()) {
            String[] range = priceRange.split("-");
            if (range.length == 2) {
                try {
                    filteredSmartphone.setMinPrice(Double.parseDouble(range[0]));
                    filteredSmartphone.setMaxPrice(Double.parseDouble(range[1]));
                } catch (NumberFormatException e) {
                    // Логирование или обработка ошибки некорректного диапазона цен
                }
            }
        }

        // Получаем отфильтрованные смартфоны, используя CriteriaBuilder
        List<Smartphone> smartphones = smartphoneService.dynamicSearch(filteredSmartphone);

        // Получаем данные для фильтров
        Set<String> allSmartphoneBrands = smartphoneService.getAllSmartphoneBrands();
        Set<Integer> allSmartphoneMemories = smartphoneService.getAllSmartphoneMemories();
        Set<Integer> allSmartphoneRams = smartphoneService.getAllSmartphoneRams();

        // Добавляем данные в модель
        model.addAttribute("allSmartphoneBrands", allSmartphoneBrands);
        model.addAttribute("allSmartphoneMemories", allSmartphoneMemories);
        model.addAttribute("allSmartphoneRams", allSmartphoneRams);

        model.addAttribute("smartphones", smartphones);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedMemory", memory);
        model.addAttribute("selectedRam", ram);
        model.addAttribute("selectedTitle", title);
        model.addAttribute("selectedPriceRange", priceRange);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        return "home";
    }
}
