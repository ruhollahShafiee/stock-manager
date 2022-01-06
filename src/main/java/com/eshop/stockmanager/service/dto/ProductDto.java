package com.eshop.stockmanager.service.dto;


import lombok.Data;

@Data
public class ProductDto {

    private Integer productId;
    private String code;
    private String name;
    private Integer inStock;
    private Double retailPrice;
    private Double wholesalePrice;
    private Double totalPrice;

    public ProductDto(){}

    public ProductDto(Integer productId,String name,Integer inStock,Double retailPrice,Double wholesalePrice,Double totalPrice){
        this.productId=productId;
        this.name=name;
        this.inStock=inStock;
        this.retailPrice=retailPrice;
        this.wholesalePrice=wholesalePrice;
        this.totalPrice=totalPrice;
    }
}
