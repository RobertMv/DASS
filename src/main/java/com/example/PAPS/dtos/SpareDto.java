package com.example.PAPS.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpareDto {

    private Long id, vendorCode;
    private String name, code, supplier;
    private Integer amount;
}
