package kz.bitlab.springboot.g130criteriabuilder.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortOption {
    private String field;      // техническое значение
    private String label;      // человекочитаемое название
}