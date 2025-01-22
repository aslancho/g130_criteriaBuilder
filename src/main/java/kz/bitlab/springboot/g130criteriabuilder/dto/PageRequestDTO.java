package kz.bitlab.springboot.g130criteriabuilder.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class PageRequestDTO {
    @Min(0)
    private int page = 0;

    @Min(1) @Max(100)
    private int size = 5;

    private String sortField = "title";

    private String sortDirection = "ASC";

    public PageRequest toPageRequest() {

        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);

        return PageRequest.of(page, size, sort);
    }
}