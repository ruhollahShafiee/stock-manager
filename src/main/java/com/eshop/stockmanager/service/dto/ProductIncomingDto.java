package com.eshop.stockmanager.service.dto;

import lombok.Data;

@Data
public class ProductIncomingDto {

    private String code;
    private String name;
    private Integer inStock;
    private Double retailPrice;
    private Double wholesalePrice;

    public ProductIncomingDto(){}

    public ProductIncomingDto(String code,String name, Integer inStock, Double retailPrice ,Double wholesalePrice){
        this.code=code;
        this.name=name;
        this.inStock=inStock;
        this.retailPrice=retailPrice;
        this.wholesalePrice=wholesalePrice;

    }

}
