package kz.bitlab.springboot.g130criteriabuilder.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class PageRequestDTO {
    @Min(0)
    private int page = 0;

    @Min(1) @Max(100)
    private int size = 5;

    public PageRequest toPageRequest() {
        return PageRequest.of(page, size);
    }
}