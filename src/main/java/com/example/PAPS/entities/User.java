package com.example.PAPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    @JoinColumn(name = "employee", referencedColumnName = "email")
    private String username;

    @OneToOne
    private Employee employee;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
