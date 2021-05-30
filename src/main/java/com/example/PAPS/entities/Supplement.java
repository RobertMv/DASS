package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateOfSupply;

    @OneToOne
    private Supplier supplier;

    @ElementCollection
    @MapKeyColumn(name = "spare")
    @Column(name = "supplied_spares")
    @CollectionTable(name = "spare_supplement_mapping", joinColumns = @JoinColumn(name = "supplement_id", referencedColumnName = "id"))
    private Map<String, Integer> suppliedSpares;
}
