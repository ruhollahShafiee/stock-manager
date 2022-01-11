package com.eshop.stockmanager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIncomingDto {

    private String code;
    private String name;
    private Integer inStock;
    private Double retailPrice;
    private Double wholesalePrice;

}
