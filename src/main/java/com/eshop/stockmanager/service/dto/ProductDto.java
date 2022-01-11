package com.eshop.stockmanager.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;
    private String code;
    private String name;
    private Integer inStock;
    private Double retailPrice;
    private Double wholesalePrice;
    private Double totalPrice;




}
