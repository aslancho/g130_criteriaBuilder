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

    @Column(name = "TITLE", nullable = false)
    private String title;

    @JoinColumn(name = "BRAND_ID")
    @ManyToOne
    private Brand brand;

    @Column(name = "MEMORY")
    private Integer memory;

    @Column(name = "RAM")
    private Integer ram;

    @Column(name = "PRICE")
    private Double price;

    @Transient
    private Double minPrice;

    @Transient
    private Double maxPrice;
}
