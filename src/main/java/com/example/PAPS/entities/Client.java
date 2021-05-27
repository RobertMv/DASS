package com.example.PAPS.entities;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fio", nullable = false)
    private String FIO;

    @Column(name = "passport_id", nullable = false, unique = true)
    private Integer passportID;

    @Column(name = "address", nullable = false)
    private String address;
}
