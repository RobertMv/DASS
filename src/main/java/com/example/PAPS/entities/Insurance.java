package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String series;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String kind;

    @OneToOne
    private Car car;
}
