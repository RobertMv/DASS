package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class CarSell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Car car;

    @OneToOne
    private Client buyer;
}
