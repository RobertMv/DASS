package com.example.PAPS.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum Role {
    DIRECTOR("DIRECTOR"), //директор
    SERVICE_MANAGER("SERVICE_MANAGER"), //менеджер тех центра
    AUTO_SELLING_MANAGER("AUTO_SELLING_MANAGER"), //менеджер продаж машин (таких, как я)
    PARTS_SELLING_MANAGER("PARTS_SELLING_MANAGER"), //менеджер продаж запчастей и овощей
    HR("HR"), //ну тут понятно, симпатичная девочка-кадровик
    MAINTENANCE_WORKER("MAINTENANCE_WORKER"), // сотрудник тех центра
    SUPPLIER_D("SUPPLIER_D"); //отдел поставщиков

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
