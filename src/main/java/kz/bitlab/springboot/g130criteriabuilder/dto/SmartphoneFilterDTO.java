package kz.bitlab.springboot.g130criteriabuilder.dto;

import lombok.Data;

@Data
public class SmartphoneFilterDTO {
    private String title;
    private String brandName;
    private Integer memory;
    private Integer ram;
    private String priceRange;
}