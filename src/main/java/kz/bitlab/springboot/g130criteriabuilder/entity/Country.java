package kz.bitlab.springboot.g130criteriabuilder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COUNTRIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CONTINENT")
    private String continent;

    @Column(name = "POPULATION")
    private Long population;

    @Column(name = "ISO_CODE", nullable = false, unique = true, length = 3)
    private String isoCode;
}
