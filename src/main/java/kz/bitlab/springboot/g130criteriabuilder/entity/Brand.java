package kz.bitlab.springboot.g130criteriabuilder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BRANDS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @JoinColumn(name = "COUNTRY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country originCountry;

    @Column(name = "FOUNDATION_YEAR")
    private Integer foundationYear;
}
