package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Column(name = "passport_id", nullable = false, unique = true)
    private String passport;

    @Column(name = "address", nullable = false)
    private String address;
}
