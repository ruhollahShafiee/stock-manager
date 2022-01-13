package com.eshop.stockmanager.service.dto.mapper;

import com.eshop.stockmanager.repository.model.Product;
import com.eshop.stockmanager.service.dto.ProductDto;
import com.eshop.stockmanager.service.dto.ProductIncomingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "productId", source = "id")
    ProductDto productToProductDto(Product product);

    List<ProductDto> productsToProductDtos(List<Product> products);

    Product productIncomingDtoToProduct(ProductIncomingDto productIncomingDto);

    List<Product> productsIncomingDtoToProducts(List<ProductIncomingDto> productIncomingDtos);

}
