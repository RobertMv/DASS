package com.example.PAPS.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Employee employee;

    @Column(nullable = false)
    @JoinColumn(name = "email", table = "employee")
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
