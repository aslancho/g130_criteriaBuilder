package kz.bitlab.springboot.g130criteriabuilder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SMARTPHONES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Smartphone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String brand;

    private Integer memory;

    private Integer ram;

    private Double price;

    @Transient
    private Double minPrice;

    @Transient
    private Double maxPrice;

}
