package com.example.PAPS.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    private String patronymic;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private Long passportID;

    @Column(nullable = false, unique = true)
    private Long INN;

    @Column(nullable = false, unique = true)
    private Long SNILS;

    @Column(nullable = false)
    private Date dateOfEmployment;

    @Column(nullable = false)
    private Date dateOfDismissal;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String departmentCode;

    @Column(nullable = false)
    private String positionCode;

}
