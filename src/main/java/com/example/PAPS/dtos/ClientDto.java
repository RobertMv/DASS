package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String fio;
    private String passport;
    private String address;
}
